package com.keights.vikran.Extras;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.room.Room;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.keights.vikran.LoginActivity;
import com.keights.vikran.Network.UserDatabase;
import com.keights.vikran.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Constants
{


    public static final String DATABASE_NAME = "UserDB";
    public static void Alert(Context context,String message) {
         new MaterialAlertDialogBuilder(context, R.style.AlertDiloge)
                 .setTitle("Alert")
                 .setMessage(message)
                 .setPositiveButton("Ok",null)
                 .show();

    }

    public static String longToDate(String format,Long rawDate){
        Date date=new Date(rawDate);
        SimpleDateFormat df2 = new SimpleDateFormat(format);
        return df2.format(date);
    }
    public static String Date(String time)  {
        if (time != null) {
            String df = "yyyy-MM-dd hh:mm:ss";
            String resultFormat = "dd/MM/yyyy";
            Date date = null;
            try {
                date = new SimpleDateFormat(df).parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new SimpleDateFormat("dd MMM yyyy").format(date);
        }else {
            return "unknown";
        }
    }

    public static String capitalize(String str)
    {
        if(str == null) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


    public static void deleteUser(final Activity context) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void[] objects) {
                UserDatabase userDatabase = Room.databaseBuilder(context, UserDatabase.class, Constants.DATABASE_NAME).
                        fallbackToDestructiveMigration().build();
                userDatabase.dbAccess().deleteUser(LoginActivity.USER);
                return null;
            }
        }.execute();
        new SessionManager(context).setLogin(false);
        context.finish();
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public static void sessionExpired(final Activity activity , String message) {
        new MaterialAlertDialogBuilder(activity,R.style.AlertDiloge)
                .setTitle("Session Expire")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteUser(activity);
                    }
                })
                .show();

    }

    public static void showFormValidationError(Activity activity , EditText editText ,String errorMessage){
        Alert(activity, errorMessage);
        editText.setError(errorMessage);
    }

    public static boolean validate(Activity activity,List<TextInputEditText> validationList) {
        for(TextInputEditText e : validationList) {
            if(!isValid(e)) {
                //display your message here
                showFormValidationError(activity, e, e.getTag().toString() + " Field Required");
                return false;
            }
        }
        return true;
        //Of course if you want to display message for all EditTexts validation faults just wait with returning "false".
    }

    public static boolean isValid(TextInputEditText et) {
        if (et.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

package com.keights.vikran.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.keights.vikran.Extras.AndroidMultiPartEntity;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.LoginActivity;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;

public class ImageUploadJMC extends AppCompatActivity {
    String DTCPAth ;
    ImageView dtcimageView;
    private static final String TAG = "ImageUploadJMC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload_j_m_c);
        dtcimageView = findViewById(R.id.imageView);

        findViewById(R.id.openDTC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                startActivityForResult(intent, 1213);
            }
        });

        findViewById(R.id.UploadImages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DTCPAth == null || DTCPAth.isEmpty() ){
                    Constants.Alert(ImageUploadJMC.this, "Please Select Images");
                }else {
                    //  postFile();
                    new ImageUploadJMC.ImagesUploadExeCutionToServer().execute();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1213 && resultCode == Activity.RESULT_OK) {
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
            dtcimageView.setImageBitmap(selectedImage);
            DTCPAth = filePath;
        }
    }



    private class ImagesUploadExeCutionToServer extends AsyncTask<String, Integer, String> {
        ProgressDialog progressBar;
        private long totalSize;

        @Override
        protected void onPreExecute() {
            progressBar = new ProgressDialog(ImageUploadJMC.this);
            progressBar.setCancelable(false);
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.setMessage("Uploading data to server (0 %)");
            progressBar.show();
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            progressBar.setMessage("Uploading data to server ("+progress[0]+" %)");
        }

        @Override
        protected String doInBackground(String... params) {
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(RetrofitClient.BASE_URL + "jmc_upload");

            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                        new AndroidMultiPartEntity.ProgressListener() {
                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });

                entity.addPart("userId",new StringBody(LoginActivity.USER.getUserId()));
                entity.addPart("division", new StringBody(LoginActivity.USER.getDivision()));
                entity.addPart("consumer_no", new StringBody(getIntent().getStringExtra("consumer_no")));


                File sourceFile1 = new File(DTCPAth);
                if (sourceFile1.exists()) {
                    entity.addPart("jmc_file", new FileBody(sourceFile1));
                }


                totalSize = entity.getContentLength();
                httppost.setEntity(entity);

                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    responseString = EntityUtils.toString(r_entity);
                } else {
                    responseString = "Error occurred! Http Status Code: "
                            + statusCode;
                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
            } catch (IOException e) {
                responseString = e.toString();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: "+s);
            progressBar.dismiss();
            if (s != null) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if (!jsonObject.getBoolean("logged_in")) {
                        Constants.Alert(ImageUploadJMC.this, jsonObject.getString("errormsg"));
                    } else {
                        new MaterialAlertDialogBuilder(ImageUploadJMC.this)
                                .setTitle("Alert")
                                .setMessage(jsonObject.getString("msg"))
                                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
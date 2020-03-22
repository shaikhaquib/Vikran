package com.keights.vikran.ResponseModel;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class UserInfo{

	@SerializedName("division")
	private String division;

	@NonNull
	@PrimaryKey
	@SerializedName("user_id")
	private String userId;

	@SerializedName("login_type")
	private String loginType;

	@SerializedName("logged_in")
	private boolean loggedIn;

	@SerializedName("msg")
	private String successMsg;

	@SerializedName("reporting_id")
	private String reportingId;

	@SerializedName("mobile_number")
	private String mobileNumber;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setDivision(String division){
		this.division = division;
	}

	public String getDivision(){
		return division;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setLoginType(String loginType){
		this.loginType = loginType;
	}

	public String getLoginType(){
		return loginType;
	}

	public void setLoggedIn(boolean loggedIn){
		this.loggedIn = loggedIn;
	}

	public boolean isLoggedIn(){
		return loggedIn;
	}

	public void setSuccessMsg(String successMsg){
		this.successMsg = successMsg;
	}

	public String getSuccessMsg(){
		return successMsg;
	}

	public void setReportingId(String reportingId){
		this.reportingId = reportingId;
	}

	public String getReportingId(){
		return reportingId;
	}

	public void setMobileNumber(String mobileNumber){
		this.mobileNumber = mobileNumber;
	}

	public String getMobileNumber(){
		return mobileNumber;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"UserInfo{" + 
			"division = '" + division + '\'' + 
			",user_id = '" + userId + '\'' + 
			",login_type = '" + loginType + '\'' + 
			",logged_in = '" + loggedIn + '\'' + 
			",msg = '" + successMsg + '\'' +
			",reporting_id = '" + reportingId + '\'' + 
			",mobile_number = '" + mobileNumber + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
package com.keights.vikran.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class RTCResponse {

	@SerializedName("msg")
	private String msg;

	@SerializedName("logged_in")
	private boolean loggedIn;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setLoggedIn(boolean loggedIn){
		this.loggedIn = loggedIn;
	}

	public boolean isLoggedIn(){
		return loggedIn;
	}

	@Override
 	public String toString(){
		return 
			"RTCResponse{" +
			"msg = '" + msg + '\'' + 
			",logged_in = '" + loggedIn + '\'' + 
			"}";
		}
}
package com.keights.vikran.ResponseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SearchConsumerResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("logged_in")
	private boolean loggedIn;

	@SerializedName("consumer_details")
	private List<ConsumerDetailsItem> consumerDetails;

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

	public void setConsumerDetails(List<ConsumerDetailsItem> consumerDetails){
		this.consumerDetails = consumerDetails;
	}

	public List<ConsumerDetailsItem> getConsumerDetails(){
		return consumerDetails;
	}

	@Override
 	public String toString(){
		return 
			"SearchConsumerResponse{" + 
			"msg = '" + msg + '\'' + 
			",logged_in = '" + loggedIn + '\'' + 
			",consumer_details = '" + consumerDetails + '\'' + 
			"}";
		}
}
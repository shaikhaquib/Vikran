package com.keights.vikran.ResponseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AssignExecutionResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("logged_in")
	private boolean loggedIn;

	@SerializedName("assign_consumer")
	private List<AssignConsumerItem> assignConsumer;

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

	public void setAssignConsumer(List<AssignConsumerItem> assignConsumer){
		this.assignConsumer = assignConsumer;
	}

	public List<AssignConsumerItem> getAssignConsumer(){
		return assignConsumer;
	}

	@Override
 	public String toString(){
		return 
			"AssignExecutionResponse{" + 
			"msg = '" + msg + '\'' + 
			",logged_in = '" + loggedIn + '\'' + 
			",assign_consumer = '" + assignConsumer + '\'' + 
			"}";
		}
}
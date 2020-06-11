package com.keights.vikran.ResponseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PendingExecution{

	@SerializedName("msg")
	private String msg;

	@SerializedName("logged_in")
	private boolean loggedIn;

	@SerializedName("Pending_Execution")
	private List<PendingExecutionItem> pendingExecution;

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

	public void setPendingExecution(List<PendingExecutionItem> pendingExecution){
		this.pendingExecution = pendingExecution;
	}

	public List<PendingExecutionItem> getPendingExecution(){
		return pendingExecution;
	}

	@Override
 	public String toString(){
		return 
			"PendingExecution{" + 
			"msg = '" + msg + '\'' + 
			",logged_in = '" + loggedIn + '\'' + 
			",pending_Execution = '" + pendingExecution + '\'' + 
			"}";
		}
}
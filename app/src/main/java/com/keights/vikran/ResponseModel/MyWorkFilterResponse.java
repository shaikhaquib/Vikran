package com.keights.vikran.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class MyWorkFilterResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("logged_in")
	private boolean loggedIn;

	@SerializedName("filter_data")
	private FilterData filterData;

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

	public void setFilterData(FilterData filterData){
		this.filterData = filterData;
	}

	public FilterData getFilterData(){
		return filterData;
	}

	@Override
 	public String toString(){
		return 
			"MyWorkFilterResponse{" + 
			"msg = '" + msg + '\'' + 
			",logged_in = '" + loggedIn + '\'' + 
			",filter_data = '" + filterData + '\'' + 
			"}";
		}
}
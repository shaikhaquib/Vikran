package com.keights.vikran.ResponseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MapResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("logged_in")
	private boolean loggedIn;

	@SerializedName("division_survay_details")
	private List<DivisionSurvayDetailsItem> divisionSurvayDetails;

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

	public void setDivisionSurvayDetails(List<DivisionSurvayDetailsItem> divisionSurvayDetails){
		this.divisionSurvayDetails = divisionSurvayDetails;
	}

	public List<DivisionSurvayDetailsItem> getDivisionSurvayDetails(){
		return divisionSurvayDetails;
	}
}
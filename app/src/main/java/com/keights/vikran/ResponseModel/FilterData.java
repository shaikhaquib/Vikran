package com.keights.vikran.ResponseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FilterData{

	@SerializedName("my_execution")
	private List<MyExecutionItem> myExecution;

	@SerializedName("my_jmc_e")
	private List<MyJmcEItem> myJmcE;

	@SerializedName("my_jmc_d")
	private List<MyJmcDItem> myJmcD;

	@SerializedName("my_jmc_c")
	private List<MyJmcCItem> myJmcC;

	@SerializedName("my_jmc_b")
	private List<MyJmcBItem> myJmcB;

	@SerializedName("my_survey")
	private List<MySurveyItem> mySurvey;

	@SerializedName("my_rtc")
	private List<MyRtcItem> myRtc;

	@SerializedName("my_jmc_a")
	private List<MyJmcAItem> myJmcA;

	@SerializedName("my_billing")
	private List<MyBillingItem> myBilling;

	@SerializedName("my_per_com")
	private List<MyPerComItem> myPerCom;

	public void setMyExecution(List<MyExecutionItem> myExecution){
		this.myExecution = myExecution;
	}

	public List<MyExecutionItem> getMyExecution(){
		return myExecution;
	}

	public void setMyJmcE(List<MyJmcEItem> myJmcE){
		this.myJmcE = myJmcE;
	}

	public List<MyJmcEItem> getMyJmcE(){
		return myJmcE;
	}

	public void setMyJmcD(List<MyJmcDItem> myJmcD){
		this.myJmcD = myJmcD;
	}

	public List<MyJmcDItem> getMyJmcD(){
		return myJmcD;
	}

	public void setMyJmcC(List<MyJmcCItem> myJmcC){
		this.myJmcC = myJmcC;
	}

	public List<MyJmcCItem> getMyJmcC(){
		return myJmcC;
	}

	public void setMyJmcB(List<MyJmcBItem> myJmcB){
		this.myJmcB = myJmcB;
	}

	public List<MyJmcBItem> getMyJmcB(){
		return myJmcB;
	}

	public void setMySurvey(List<MySurveyItem> mySurvey){
		this.mySurvey = mySurvey;
	}

	public List<MySurveyItem> getMySurvey(){
		return mySurvey;
	}

	public void setMyRtc(List<MyRtcItem> myRtc){
		this.myRtc = myRtc;
	}

	public List<MyRtcItem> getMyRtc(){
		return myRtc;
	}

	public void setMyJmcA(List<MyJmcAItem> myJmcA){
		this.myJmcA = myJmcA;
	}

	public List<MyJmcAItem> getMyJmcA(){
		return myJmcA;
	}

	public void setMyBilling(List<MyBillingItem> myBilling){
		this.myBilling = myBilling;
	}

	public List<MyBillingItem> getMyBilling(){
		return myBilling;
	}

	public void setMyPerCom(List<MyPerComItem> myPerCom){
		this.myPerCom = myPerCom;
	}

	public List<MyPerComItem> getMyPerCom(){
		return myPerCom;
	}

	@Override
 	public String toString(){
		return 
			"FilterData{" + 
			"my_execution = '" + myExecution + '\'' + 
			",my_jmc_e = '" + myJmcE + '\'' + 
			",my_jmc_d = '" + myJmcD + '\'' + 
			",my_jmc_c = '" + myJmcC + '\'' + 
			",my_jmc_b = '" + myJmcB + '\'' + 
			",my_survey = '" + mySurvey + '\'' + 
			",my_rtc = '" + myRtc + '\'' + 
			",my_jmc_a = '" + myJmcA + '\'' + 
			",my_billing = '" + myBilling + '\'' + 
			",my_per_com = '" + myPerCom + '\'' + 
			"}";
		}
}
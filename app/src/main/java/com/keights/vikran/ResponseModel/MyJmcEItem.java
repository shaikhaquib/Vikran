package com.keights.vikran.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class MyJmcEItem{

	@SerializedName("division")
	private String division;

	@SerializedName("consumer_name")
	private String consumerName;

	@SerializedName("consumer_no")
	private String consumerNo;

	public void setDivision(String division){
		this.division = division;
	}

	public String getDivision(){
		return division;
	}

	public void setConsumerName(String consumerName){
		this.consumerName = consumerName;
	}

	public String getConsumerName(){
		return consumerName;
	}

	public void setConsumerNo(String consumerNo){
		this.consumerNo = consumerNo;
	}

	public String getConsumerNo(){
		return consumerNo;
	}

	@Override
 	public String toString(){
		return 
			"MyJmcEItem{" + 
			"division = '" + division + '\'' + 
			",consumer_name = '" + consumerName + '\'' + 
			",consumer_no = '" + consumerNo + '\'' + 
			"}";
		}
}
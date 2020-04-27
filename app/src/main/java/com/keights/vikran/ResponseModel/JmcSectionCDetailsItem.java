package com.keights.vikran.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class JmcSectionCDetailsItem{

	@SerializedName("new_pole")
	private String newPole;

	@SerializedName("created_id")
	private String createdId;

	@SerializedName("span_in_mtr")
	private String spanInMtr;

	@SerializedName("existing_pole")
	private String existingPole;

	@SerializedName("jmc_id")
	private String jmcId;

	@SerializedName("section")
	private String section;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("stay")
	private String stay;

	public void setNewPole(String newPole){
		this.newPole = newPole;
	}

	public String getNewPole(){
		return newPole;
	}

	public void setCreatedId(String createdId){
		this.createdId = createdId;
	}

	public String getCreatedId(){
		return createdId;
	}

	public void setSpanInMtr(String spanInMtr){
		this.spanInMtr = spanInMtr;
	}

	public String getSpanInMtr(){
		return spanInMtr;
	}

	public void setExistingPole(String existingPole){
		this.existingPole = existingPole;
	}

	public String getExistingPole(){
		return existingPole;
	}

	public void setJmcId(String jmcId){
		this.jmcId = jmcId;
	}

	public String getJmcId(){
		return jmcId;
	}

	public void setSection(String section){
		this.section = section;
	}

	public String getSection(){
		return section;
	}

	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public void setStay(String stay){
		this.stay = stay;
	}

	public String getStay(){
		return stay;
	}

	@Override
 	public String toString(){
		return 
			"JmcSectionCDetailsItem{" + 
			"new_pole = '" + newPole + '\'' + 
			",created_id = '" + createdId + '\'' + 
			",span_in_mtr = '" + spanInMtr + '\'' + 
			",existing_pole = '" + existingPole + '\'' + 
			",jmc_id = '" + jmcId + '\'' + 
			",section = '" + section + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",stay = '" + stay + '\'' + 
			"}";
		}
}
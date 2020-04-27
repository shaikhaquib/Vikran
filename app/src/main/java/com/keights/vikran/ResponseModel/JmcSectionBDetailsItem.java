package com.keights.vikran.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class JmcSectionBDetailsItem{

	@SerializedName("created_id")
	private String createdId;

	@SerializedName("voltage_type")
	private String voltageType;

	@SerializedName("twentyfive_kva_dtr")
	private String twentyfiveKvaDtr;

	@SerializedName("jmc_id")
	private String jmcId;

	@SerializedName("section")
	private String section;

	@SerializedName("ten_kva_dtr")
	private String tenKvaDtr;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("sixteen_kva_dtr")
	private String sixteenKvaDtr;

	public void setCreatedId(String createdId){
		this.createdId = createdId;
	}

	public String getCreatedId(){
		return createdId;
	}

	public void setVoltageType(String voltageType){
		this.voltageType = voltageType;
	}

	public String getVoltageType(){
		return voltageType;
	}

	public void setTwentyfiveKvaDtr(String twentyfiveKvaDtr){
		this.twentyfiveKvaDtr = twentyfiveKvaDtr;
	}

	public String getTwentyfiveKvaDtr(){
		return twentyfiveKvaDtr;
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

	public void setTenKvaDtr(String tenKvaDtr){
		this.tenKvaDtr = tenKvaDtr;
	}

	public String getTenKvaDtr(){
		return tenKvaDtr;
	}

	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public void setSixteenKvaDtr(String sixteenKvaDtr){
		this.sixteenKvaDtr = sixteenKvaDtr;
	}

	public String getSixteenKvaDtr(){
		return sixteenKvaDtr;
	}

	@Override
 	public String toString(){
		return 
			"JmcSectionBDetailsItem{" + 
			"created_id = '" + createdId + '\'' + 
			",voltage_type = '" + voltageType + '\'' + 
			",twentyfive_kva_dtr = '" + twentyfiveKvaDtr + '\'' + 
			",jmc_id = '" + jmcId + '\'' + 
			",section = '" + section + '\'' + 
			",ten_kva_dtr = '" + tenKvaDtr + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",sixteen_kva_dtr = '" + sixteenKvaDtr + '\'' + 
			"}";
		}
}
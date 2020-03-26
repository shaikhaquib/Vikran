package com.keights.vikran.ResponseModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FilterList implements Serializable {

	@SerializedName("created_name")
	private String createdName;

	@SerializedName("sanction_load")
	private String sanctionLoad;

	@SerializedName("section")
	private String section;

	@SerializedName("voltage_level")
	private String voltageLevel;

	@SerializedName("modified_date")
	private String modifiedDate;

	@SerializedName("division")
	private String division;

	@SerializedName("consumer_name")
	private String consumerName;

	@SerializedName("taluka")
	private String taluka;

	@SerializedName("dtc_code")
	private String dtcCode;

	@SerializedName("survey")
	private String survey;

	@SerializedName("connection")
	private String connection;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("customer_id")
	private String customerId;

	@SerializedName("village")
	private String village;

	@SerializedName("sub_division")
	private String subDivision;

	@SerializedName("consumer_no")
	private String consumerNo;

	@SerializedName("status")
	private String status;

	public void setCreatedName(String createdName){
		this.createdName = createdName;
	}

	public String getCreatedName(){
		return createdName;
	}

	public void setSanctionLoad(String sanctionLoad){
		this.sanctionLoad = sanctionLoad;
	}

	public String getSanctionLoad(){
		return sanctionLoad;
	}

	public void setSection(String section){
		this.section = section;
	}

	public String getSection(){
		return section;
	}

	public void setVoltageLevel(String voltageLevel){
		this.voltageLevel = voltageLevel;
	}

	public String getVoltageLevel(){
		return voltageLevel;
	}

	public void setModifiedDate(String modifiedDate){
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedDate(){
		return modifiedDate;
	}

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

	public void setTaluka(String taluka){
		this.taluka = taluka;
	}

	public String getTaluka(){
		return taluka;
	}

	public void setDtcCode(String dtcCode){
		this.dtcCode = dtcCode;
	}

	public String getDtcCode(){
		return dtcCode;
	}

	public void setSurvey(String survey){
		this.survey = survey;
	}

	public String getSurvey(){
		return survey;
	}

	public void setConnection(String connection){
		this.connection = connection;
	}

	public String getConnection(){
		return connection;
	}

	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setVillage(String village){
		this.village = village;
	}

	public String getVillage(){
		return village;
	}

	public void setSubDivision(String subDivision){
		this.subDivision = subDivision;
	}

	public String getSubDivision(){
		return subDivision;
	}

	public void setConsumerNo(String consumerNo){
		this.consumerNo = consumerNo;
	}

	public String getConsumerNo(){
		return consumerNo;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"WeekListItem{" + 
			"created_name = '" + createdName + '\'' + 
			",sanction_load = '" + sanctionLoad + '\'' + 
			",section = '" + section + '\'' + 
			",voltage_level = '" + voltageLevel + '\'' + 
			",modified_date = '" + modifiedDate + '\'' + 
			",division = '" + division + '\'' + 
			",consumer_name = '" + consumerName + '\'' + 
			",taluka = '" + taluka + '\'' + 
			",dtc_code = '" + dtcCode + '\'' + 
			",survey = '" + survey + '\'' + 
			",connection = '" + connection + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",customer_id = '" + customerId + '\'' + 
			",village = '" + village + '\'' + 
			",sub_division = '" + subDivision + '\'' + 
			",consumer_no = '" + consumerNo + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
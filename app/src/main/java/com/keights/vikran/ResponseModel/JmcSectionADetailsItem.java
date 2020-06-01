package com.keights.vikran.ResponseModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JmcSectionADetailsItem implements Serializable {

	@SerializedName("span_in_mtr")
	private String spanInMtr;

	@SerializedName("new_tapping")
	private String newTapping;

	@SerializedName("existing_tapping")
	private String existingTapping;

	@SerializedName("single_cutpoint_pole")
	private String singleCutpointPole;

	@SerializedName("jmc_id")
	private String jmcId;

	@SerializedName("stay_set")
	private String staySet;

	@SerializedName("section")
	private String section;

	@SerializedName("gaurding_span")
	private String gaurdingSpan;

	@SerializedName("i_type_dtc_inline")
	private String iTypeDtcInline;

	@SerializedName("tapping_from_existing_dtc")
	private String tappingFromExistingDtc;

	@SerializedName("no_of_guarding")
	private String noOfGuarding;

	@SerializedName("created_id")
	private String createdId;

	@SerializedName("voltage_type")
	private String voltageType;

	@SerializedName("double_cutpoint_pole")
	private String doubleCutpointPole;

	@SerializedName("stud_pole")
	private String studPole;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("i_type_dtc")
	private String iTypeDtc;

	@SerializedName("pole_type")
	private String poleType;

	@SerializedName("consumer_no")
	private String consumerNo;

	public void setSpanInMtr(String spanInMtr){
		this.spanInMtr = spanInMtr;
	}

	public String getSpanInMtr(){
		return spanInMtr;
	}

	public void setNewTapping(String newTapping){
		this.newTapping = newTapping;
	}

	public String getNewTapping(){
		return newTapping;
	}

	public void setExistingTapping(String existingTapping){
		this.existingTapping = existingTapping;
	}

	public String getExistingTapping(){
		return existingTapping;
	}

	public void setSingleCutpointPole(String singleCutpointPole){
		this.singleCutpointPole = singleCutpointPole;
	}

	public String getSingleCutpointPole(){
		return singleCutpointPole;
	}

	public void setJmcId(String jmcId){
		this.jmcId = jmcId;
	}

	public String getJmcId(){
		return jmcId;
	}

	public void setStaySet(String staySet){
		this.staySet = staySet;
	}

	public String getStaySet(){
		return staySet;
	}

	public void setSection(String section){
		this.section = section;
	}

	public String getSection(){
		return section;
	}

	public void setGaurdingSpan(String gaurdingSpan){
		this.gaurdingSpan = gaurdingSpan;
	}

	public String getGaurdingSpan(){
		return gaurdingSpan;
	}

	public void setITypeDtcInline(String iTypeDtcInline){
		this.iTypeDtcInline = iTypeDtcInline;
	}

	public String getITypeDtcInline(){
		return iTypeDtcInline;
	}

	public void setTappingFromExistingDtc(String tappingFromExistingDtc){
		this.tappingFromExistingDtc = tappingFromExistingDtc;
	}

	public String getTappingFromExistingDtc(){
		return tappingFromExistingDtc;
	}

	public void setNoOfGuarding(String noOfGuarding){
		this.noOfGuarding = noOfGuarding;
	}

	public String getNoOfGuarding(){
		return noOfGuarding;
	}

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

	public void setDoubleCutpointPole(String doubleCutpointPole){
		this.doubleCutpointPole = doubleCutpointPole;
	}

	public String getDoubleCutpointPole(){
		return doubleCutpointPole;
	}

	public void setStudPole(String studPole){
		this.studPole = studPole;
	}

	public String getStudPole(){
		return studPole;
	}

	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public void setITypeDtc(String iTypeDtc){
		this.iTypeDtc = iTypeDtc;
	}

	public String getITypeDtc(){
		return iTypeDtc;
	}

	public void setPoleType(String poleType){
		this.poleType = poleType;
	}

	public String getPoleType(){
		return poleType;
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
			"JmcSectionADetailsItem{" + 
			"span_in_mtr = '" + spanInMtr + '\'' + 
			",new_tapping = '" + newTapping + '\'' + 
			",existing_tapping = '" + existingTapping + '\'' + 
			",single_cutpoint_pole = '" + singleCutpointPole + '\'' + 
			",jmc_id = '" + jmcId + '\'' + 
			",stay_set = '" + staySet + '\'' + 
			",section = '" + section + '\'' + 
			",gaurding_span = '" + gaurdingSpan + '\'' + 
			",i_type_dtc_inline = '" + iTypeDtcInline + '\'' + 
			",tapping_from_existing_dtc = '" + tappingFromExistingDtc + '\'' + 
			",no_of_guarding = '" + noOfGuarding + '\'' + 
			",created_id = '" + createdId + '\'' + 
			",voltage_type = '" + voltageType + '\'' + 
			",double_cutpoint_pole = '" + doubleCutpointPole + '\'' + 
			",stud_pole = '" + studPole + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",i_type_dtc = '" + iTypeDtc + '\'' + 
			",pole_type = '" + poleType + '\'' + 
			",consumer_no = '" + consumerNo + '\'' + 
			"}";
		}
}
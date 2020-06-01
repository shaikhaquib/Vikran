package com.keights.vikran.ResponseModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExecutionDetailsItem implements Serializable {

	@SerializedName("dtr_erection")
	private String dtrErection;

	@SerializedName("pole_shifting")
	private String poleShifting;

	@SerializedName("mtr9_psc")
	private String mtr9Psc;

	@SerializedName("modified_id")
	private String modifiedId;

	@SerializedName("finishing_per_location")
	private String finishingPerLocation;

	@SerializedName("approved_date")
	private String approvedDate;

	@SerializedName("modified_date")
	private String modifiedDate;

	@SerializedName("execution_id")
	private String executionId;

	@SerializedName("created_id")
	private String createdId;

	@SerializedName("approved_by")
	private String approvedBy;

	@SerializedName("mtr9_rsj")
	private String mtr9Rsj;

	@SerializedName("pole_erection")
	private String poleErection;

	@SerializedName("execution_status")
	private String executionStatus;

	@SerializedName("string_per_location")
	private String stringPerLocation;

	@SerializedName("transformer_make")
	private String transformerMake;

	@SerializedName("meter_no")
	private String meterNo;

	@SerializedName("mtr11_rsj")
	private String mtr11Rsj;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("meter_make")
	private String meterMake;

	@SerializedName("tf_sl_no")
	private String tfSlNo;

	@SerializedName("tf_capacity_in_kva")
	private String tfCapacityInKva;

	@SerializedName("num_of_pole")
	private String numOfPole;

	@SerializedName("consumer_no")
	private String consumerNo;

	@SerializedName("str_fitting")
	private String strFitting;

	public void setDtrErection(String dtrErection){
		this.dtrErection = dtrErection;
	}

	public String getDtrErection(){
		return dtrErection;
	}

	public void setPoleShifting(String poleShifting){
		this.poleShifting = poleShifting;
	}

	public String getPoleShifting(){
		return poleShifting;
	}

	public void setMtr9Psc(String mtr9Psc){
		this.mtr9Psc = mtr9Psc;
	}

	public String getMtr9Psc(){
		return mtr9Psc;
	}

	public void setModifiedId(String modifiedId){
		this.modifiedId = modifiedId;
	}

	public String getModifiedId(){
		return modifiedId;
	}

	public void setFinishingPerLocation(String finishingPerLocation){
		this.finishingPerLocation = finishingPerLocation;
	}

	public String getFinishingPerLocation(){
		return finishingPerLocation;
	}

	public void setApprovedDate(String approvedDate){
		this.approvedDate = approvedDate;
	}

	public String getApprovedDate(){
		return approvedDate;
	}

	public void setModifiedDate(String modifiedDate){
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedDate(){
		return modifiedDate;
	}

	public void setExecutionId(String executionId){
		this.executionId = executionId;
	}

	public String getExecutionId(){
		return executionId;
	}

	public void setCreatedId(String createdId){
		this.createdId = createdId;
	}

	public String getCreatedId(){
		return createdId;
	}

	public void setApprovedBy(String approvedBy){
		this.approvedBy = approvedBy;
	}

	public String getApprovedBy(){
		return approvedBy;
	}

	public void setMtr9Rsj(String mtr9Rsj){
		this.mtr9Rsj = mtr9Rsj;
	}

	public String getMtr9Rsj(){
		return mtr9Rsj;
	}

	public void setPoleErection(String poleErection){
		this.poleErection = poleErection;
	}

	public String getPoleErection(){
		return poleErection;
	}

	public void setExecutionStatus(String executionStatus){
		this.executionStatus = executionStatus;
	}

	public String getExecutionStatus(){
		return executionStatus;
	}

	public void setStringPerLocation(String stringPerLocation){
		this.stringPerLocation = stringPerLocation;
	}

	public String getStringPerLocation(){
		return stringPerLocation;
	}

	public void setTransformerMake(String transformerMake){
		this.transformerMake = transformerMake;
	}

	public String getTransformerMake(){
		return transformerMake;
	}

	public void setMeterNo(String meterNo){
		this.meterNo = meterNo;
	}

	public String getMeterNo(){
		return meterNo;
	}

	public void setMtr11Rsj(String mtr11Rsj){
		this.mtr11Rsj = mtr11Rsj;
	}

	public String getMtr11Rsj(){
		return mtr11Rsj;
	}

	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public void setMeterMake(String meterMake){
		this.meterMake = meterMake;
	}

	public String getMeterMake(){
		return meterMake;
	}

	public void setTfSlNo(String tfSlNo){
		this.tfSlNo = tfSlNo;
	}

	public String getTfSlNo(){
		return tfSlNo;
	}

	public void setTfCapacityInKva(String tfCapacityInKva){
		this.tfCapacityInKva = tfCapacityInKva;
	}

	public String getTfCapacityInKva(){
		return tfCapacityInKva;
	}

	public void setNumOfPole(String numOfPole){
		this.numOfPole = numOfPole;
	}

	public String getNumOfPole(){
		return numOfPole;
	}

	public void setConsumerNo(String consumerNo){
		this.consumerNo = consumerNo;
	}

	public String getConsumerNo(){
		return consumerNo;
	}

	public void setStrFitting(String strFitting){
		this.strFitting = strFitting;
	}

	public String getStrFitting(){
		return strFitting;
	}

	@Override
 	public String toString(){
		return 
			"ExecutionDetailsItem{" + 
			"dtr_erection = '" + dtrErection + '\'' + 
			",pole_shifting = '" + poleShifting + '\'' + 
			",mtr9_psc = '" + mtr9Psc + '\'' + 
			",modified_id = '" + modifiedId + '\'' + 
			",finishing_per_location = '" + finishingPerLocation + '\'' + 
			",approved_date = '" + approvedDate + '\'' + 
			",modified_date = '" + modifiedDate + '\'' + 
			",execution_id = '" + executionId + '\'' + 
			",created_id = '" + createdId + '\'' + 
			",approved_by = '" + approvedBy + '\'' + 
			",mtr9_rsj = '" + mtr9Rsj + '\'' + 
			",pole_erection = '" + poleErection + '\'' + 
			",execution_status = '" + executionStatus + '\'' + 
			",string_per_location = '" + stringPerLocation + '\'' + 
			",transformer_make = '" + transformerMake + '\'' + 
			",meter_no = '" + meterNo + '\'' + 
			",mtr11_rsj = '" + mtr11Rsj + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",meter_make = '" + meterMake + '\'' + 
			",tf_sl_no = '" + tfSlNo + '\'' + 
			",tf_capacity_in_kva = '" + tfCapacityInKva + '\'' + 
			",num_of_pole = '" + numOfPole + '\'' + 
			",consumer_no = '" + consumerNo + '\'' + 
			",str_fitting = '" + strFitting + '\'' + 
			"}";
		}
}
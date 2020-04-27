package com.keights.vikran.ResponseModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PerComDetailsItem implements Serializable {

	@SerializedName("created_id")
	private String createdId;

	@SerializedName("modified_id")
	private String modifiedId;

	@SerializedName("received_e1_permission")
	private String receivedE1Permission;

	@SerializedName("percom_id")
	private String percomId;

	@SerializedName("applied_commission")
	private String appliedCommission;

	@SerializedName("remark")
	private String remark;

	@SerializedName("applied_e1_permission")
	private String appliedE1Permission;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("modified_date")
	private String modifiedDate;

	@SerializedName("consumer_no")
	private String consumerNo;

	public void setCreatedId(String createdId){
		this.createdId = createdId;
	}

	public String getCreatedId(){
		return createdId;
	}

	public void setModifiedId(String modifiedId){
		this.modifiedId = modifiedId;
	}

	public String getModifiedId(){
		return modifiedId;
	}

	public void setReceivedE1Permission(String receivedE1Permission){
		this.receivedE1Permission = receivedE1Permission;
	}

	public String getReceivedE1Permission(){
		return receivedE1Permission;
	}

	public void setPercomId(String percomId){
		this.percomId = percomId;
	}

	public String getPercomId(){
		return percomId;
	}

	public void setAppliedCommission(String appliedCommission){
		this.appliedCommission = appliedCommission;
	}

	public String getAppliedCommission(){
		return appliedCommission;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return remark;
	}

	public void setAppliedE1Permission(String appliedE1Permission){
		this.appliedE1Permission = appliedE1Permission;
	}

	public String getAppliedE1Permission(){
		return appliedE1Permission;
	}

	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public void setModifiedDate(String modifiedDate){
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedDate(){
		return modifiedDate;
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
			"PerComDetailsItem{" + 
			"created_id = '" + createdId + '\'' + 
			",modified_id = '" + modifiedId + '\'' + 
			",received_e1_permission = '" + receivedE1Permission + '\'' + 
			",percom_id = '" + percomId + '\'' + 
			",applied_commission = '" + appliedCommission + '\'' + 
			",remark = '" + remark + '\'' + 
			",applied_e1_permission = '" + appliedE1Permission + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",modified_date = '" + modifiedDate + '\'' + 
			",consumer_no = '" + consumerNo + '\'' + 
			"}";
		}
}
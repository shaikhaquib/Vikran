package com.keights.vikran.ResponseModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BillingDetailsItem implements Serializable {

	@SerializedName("created_id")
	private String createdId;

	@SerializedName("modified_id")
	private String modifiedId;

	@SerializedName("billing_id")
	private String billingId;

	@SerializedName("billing_status")
	private String billingStatus;

	@SerializedName("remark")
	private String remark;

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

	public void setBillingId(String billingId){
		this.billingId = billingId;
	}

	public String getBillingId(){
		return billingId;
	}

	public void setBillingStatus(String billingStatus){
		this.billingStatus = billingStatus;
	}

	public String getBillingStatus(){
		return billingStatus;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return remark;
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
			"BillingDetailsItem{" + 
			"created_id = '" + createdId + '\'' + 
			",modified_id = '" + modifiedId + '\'' + 
			",billing_id = '" + billingId + '\'' + 
			",billing_status = '" + billingStatus + '\'' + 
			",remark = '" + remark + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",modified_date = '" + modifiedDate + '\'' + 
			",consumer_no = '" + consumerNo + '\'' + 
			"}";
		}
}
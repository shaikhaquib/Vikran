package com.keights.vikran.ResponseModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RtcDetailsItem implements Serializable {

	@SerializedName("rtc_id")
	private String rtcId;

	@SerializedName("created_id")
	private String createdId;

	@SerializedName("modified_id")
	private String modifiedId;

	@SerializedName("rtc_status")
	private String rtcStatus;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("modified_date")
	private String modifiedDate;

	@SerializedName("consumer_no")
	private String consumerNo;

	public void setRtcId(String rtcId){
		this.rtcId = rtcId;
	}

	public String getRtcId(){
		return rtcId;
	}

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

	public void setRtcStatus(String rtcStatus){
		this.rtcStatus = rtcStatus;
	}

	public String getRtcStatus(){
		return rtcStatus;
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
			"RtcDetailsItem{" + 
			"rtc_id = '" + rtcId + '\'' + 
			",created_id = '" + createdId + '\'' + 
			",modified_id = '" + modifiedId + '\'' + 
			",rtc_status = '" + rtcStatus + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",modified_date = '" + modifiedDate + '\'' + 
			",consumer_no = '" + consumerNo + '\'' + 
			"}";
		}
}
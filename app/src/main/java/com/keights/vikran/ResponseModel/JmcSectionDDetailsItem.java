package com.keights.vikran.ResponseModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JmcSectionDDetailsItem implements Serializable {

	@SerializedName("created_id")
	private String createdId;

	@SerializedName("service_connection")
	private String serviceConnection;

	@SerializedName("jmc_id")
	private String jmcId;

	@SerializedName("section")
	private String section;

	@SerializedName("created_date")
	private String createdDate;

	public void setCreatedId(String createdId){
		this.createdId = createdId;
	}

	public String getCreatedId(){
		return createdId;
	}

	public void setServiceConnection(String serviceConnection){
		this.serviceConnection = serviceConnection;
	}

	public String getServiceConnection(){
		return serviceConnection;
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

	@Override
 	public String toString(){
		return 
			"JmcSectionDDetailsItem{" + 
			"created_id = '" + createdId + '\'' + 
			",service_connection = '" + serviceConnection + '\'' + 
			",jmc_id = '" + jmcId + '\'' + 
			",section = '" + section + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			"}";
		}
}
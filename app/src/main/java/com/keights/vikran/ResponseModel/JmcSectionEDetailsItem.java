package com.keights.vikran.ResponseModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JmcSectionEDetailsItem implements Serializable {

	@SerializedName("dtr_code")
	private String dtrCode;

	@SerializedName("gps_live_north")
	private String gpsLiveNorth;

	@SerializedName("gps_live_east")
	private String gpsLiveEast;

	@SerializedName("created_id")
	private String createdId;

	@SerializedName("gps_tapping_east")
	private String gpsTappingEast;

	@SerializedName("gps_dtr_north")
	private String gpsDtrNorth;

	@SerializedName("jmc_id")
	private String jmcId;

	@SerializedName("gps_dtr_east")
	private String gpsDtrEast;

	@SerializedName("section")
	private String section;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("feeder_note")
	private String feederNote;

	@SerializedName("gps_tapping_north")
	private String gpsTappingNorth;

	public void setDtrCode(String dtrCode){
		this.dtrCode = dtrCode;
	}

	public String getDtrCode(){
		return dtrCode;
	}

	public void setGpsLiveNorth(String gpsLiveNorth){
		this.gpsLiveNorth = gpsLiveNorth;
	}

	public String getGpsLiveNorth(){
		return gpsLiveNorth;
	}

	public void setGpsLiveEast(String gpsLiveEast){
		this.gpsLiveEast = gpsLiveEast;
	}

	public String getGpsLiveEast(){
		return gpsLiveEast;
	}

	public void setCreatedId(String createdId){
		this.createdId = createdId;
	}

	public String getCreatedId(){
		return createdId;
	}

	public void setGpsTappingEast(String gpsTappingEast){
		this.gpsTappingEast = gpsTappingEast;
	}

	public String getGpsTappingEast(){
		return gpsTappingEast;
	}

	public void setGpsDtrNorth(String gpsDtrNorth){
		this.gpsDtrNorth = gpsDtrNorth;
	}

	public String getGpsDtrNorth(){
		return gpsDtrNorth;
	}

	public void setJmcId(String jmcId){
		this.jmcId = jmcId;
	}

	public String getJmcId(){
		return jmcId;
	}

	public void setGpsDtrEast(String gpsDtrEast){
		this.gpsDtrEast = gpsDtrEast;
	}

	public String getGpsDtrEast(){
		return gpsDtrEast;
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

	public void setFeederNote(String feederNote){
		this.feederNote = feederNote;
	}

	public String getFeederNote(){
		return feederNote;
	}

	public void setGpsTappingNorth(String gpsTappingNorth){
		this.gpsTappingNorth = gpsTappingNorth;
	}

	public String getGpsTappingNorth(){
		return gpsTappingNorth;
	}

	@Override
 	public String toString(){
		return 
			"JmcSectionEDetailsItem{" + 
			"dtr_code = '" + dtrCode + '\'' + 
			",gps_live_north = '" + gpsLiveNorth + '\'' + 
			",gps_live_east = '" + gpsLiveEast + '\'' + 
			",created_id = '" + createdId + '\'' + 
			",gps_tapping_east = '" + gpsTappingEast + '\'' + 
			",gps_dtr_north = '" + gpsDtrNorth + '\'' + 
			",jmc_id = '" + jmcId + '\'' + 
			",gps_dtr_east = '" + gpsDtrEast + '\'' + 
			",section = '" + section + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",feeder_note = '" + feederNote + '\'' + 
			",gps_tapping_north = '" + gpsTappingNorth + '\'' + 
			"}";
		}
}
package com.keights.vikran.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class AssignConsumerItem{

	@SerializedName("section")
	private String section;

	@SerializedName("rjs_pole_old_freezing")
	private String rjsPoleOldFreezing;

	@SerializedName("survey_pairing")
	private String surveyPairing;

	@SerializedName("date_of_survey")
	private String dateOfSurvey;

	@SerializedName("survey_by")
	private String surveyBy;

	@SerializedName("division")
	private String division;

	@SerializedName("consumer_name")
	private String consumerName;

	@SerializedName("ht_line_in_km")
	private String htLineInKm;

	@SerializedName("soil_strata")
	private String soilStrata;

	@SerializedName("execution_status")
	private String executionStatus;

	@SerializedName("approach_road")
	private String approachRoad;

	@SerializedName("connection")
	private String connection;

	@SerializedName("row")
	private String row;

	@SerializedName("village")
	private String village;

	@SerializedName("sub_division")
	private String subDivision;

	@SerializedName("sanction_load")
	private String sanctionLoad;

	@SerializedName("tree_cutting")
	private String treeCutting;

	@SerializedName("voltage_level")
	private String voltageLevel;

	@SerializedName("survey_id")
	private String surveyId;

	@SerializedName("taluka")
	private String taluka;

	@SerializedName("dtc_code")
	private String dtcCode;

	@SerializedName("survey")
	private String survey;

	@SerializedName("location")
	private String location;

	@SerializedName("customer_id")
	private String customerId;

	@SerializedName("remarks")
	private String remarks;

	@SerializedName("consumer_no")
	private String consumerNo;

	public void setSection(String section){
		this.section = section;
	}

	public String getSection(){
		return section;
	}

	public void setRjsPoleOldFreezing(String rjsPoleOldFreezing){
		this.rjsPoleOldFreezing = rjsPoleOldFreezing;
	}

	public String getRjsPoleOldFreezing(){
		return rjsPoleOldFreezing;
	}

	public void setSurveyPairing(String surveyPairing){
		this.surveyPairing = surveyPairing;
	}

	public String getSurveyPairing(){
		return surveyPairing;
	}

	public void setDateOfSurvey(String dateOfSurvey){
		this.dateOfSurvey = dateOfSurvey;
	}

	public String getDateOfSurvey(){
		return dateOfSurvey;
	}

	public void setSurveyBy(String surveyBy){
		this.surveyBy = surveyBy;
	}

	public String getSurveyBy(){
		return surveyBy;
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

	public void setHtLineInKm(String htLineInKm){
		this.htLineInKm = htLineInKm;
	}

	public String getHtLineInKm(){
		return htLineInKm;
	}

	public void setSoilStrata(String soilStrata){
		this.soilStrata = soilStrata;
	}

	public String getSoilStrata(){
		return soilStrata;
	}

	public void setExecutionStatus(String executionStatus){
		this.executionStatus = executionStatus;
	}

	public String getExecutionStatus(){
		return executionStatus;
	}

	public void setApproachRoad(String approachRoad){
		this.approachRoad = approachRoad;
	}

	public String getApproachRoad(){
		return approachRoad;
	}

	public void setConnection(String connection){
		this.connection = connection;
	}

	public String getConnection(){
		return connection;
	}

	public void setRow(String row){
		this.row = row;
	}

	public String getRow(){
		return row;
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

	public void setSanctionLoad(String sanctionLoad){
		this.sanctionLoad = sanctionLoad;
	}

	public String getSanctionLoad(){
		return sanctionLoad;
	}

	public void setTreeCutting(String treeCutting){
		this.treeCutting = treeCutting;
	}

	public String getTreeCutting(){
		return treeCutting;
	}

	public void setVoltageLevel(String voltageLevel){
		this.voltageLevel = voltageLevel;
	}

	public String getVoltageLevel(){
		return voltageLevel;
	}

	public void setSurveyId(String surveyId){
		this.surveyId = surveyId;
	}

	public String getSurveyId(){
		return surveyId;
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

	public void setLocation(String location){
		this.location = location;
	}

	public String getLocation(){
		return location;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}

	public String getRemarks(){
		return remarks;
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
			"AssignConsumerItem{" + 
			"section = '" + section + '\'' + 
			",rjs_pole_old_freezing = '" + rjsPoleOldFreezing + '\'' + 
			",survey_pairing = '" + surveyPairing + '\'' + 
			",date_of_survey = '" + dateOfSurvey + '\'' + 
			",survey_by = '" + surveyBy + '\'' + 
			",division = '" + division + '\'' + 
			",consumer_name = '" + consumerName + '\'' + 
			",ht_line_in_km = '" + htLineInKm + '\'' + 
			",soil_strata = '" + soilStrata + '\'' + 
			",execution_status = '" + executionStatus + '\'' + 
			",approach_road = '" + approachRoad + '\'' + 
			",connection = '" + connection + '\'' + 
			",row = '" + row + '\'' + 
			",village = '" + village + '\'' + 
			",sub_division = '" + subDivision + '\'' + 
			",sanction_load = '" + sanctionLoad + '\'' + 
			",tree_cutting = '" + treeCutting + '\'' + 
			",voltage_level = '" + voltageLevel + '\'' + 
			",survey_id = '" + surveyId + '\'' + 
			",taluka = '" + taluka + '\'' + 
			",dtc_code = '" + dtcCode + '\'' + 
			",survey = '" + survey + '\'' + 
			",location = '" + location + '\'' + 
			",customer_id = '" + customerId + '\'' + 
			",remarks = '" + remarks + '\'' + 
			",consumer_no = '" + consumerNo + '\'' + 
			"}";
		}
}
package com.keights.vikran.ResponseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ConsumerDetails{

	@SerializedName("consumer_details")
	private List<ConsumerDetailsItem> consumerDetails;

	@SerializedName("survey_details")
	private List<SurveyDetailsItem> surveyDetails;

	@SerializedName("execution_details")
	private List<ExecutionDetailsItem> executionDetails;

	public void setConsumerDetails(List<ConsumerDetailsItem> consumerDetails){
		this.consumerDetails = consumerDetails;
	}

	public List<ConsumerDetailsItem> getConsumerDetails(){
		return consumerDetails;
	}

	public void setSurveyDetails(List<SurveyDetailsItem> surveyDetails){
		this.surveyDetails = surveyDetails;
	}

	public List<SurveyDetailsItem> getSurveyDetails(){
		return surveyDetails;
	}

	public void setExecutionDetails(List<ExecutionDetailsItem> executionDetails){
		this.executionDetails = executionDetails;
	}

	public List<ExecutionDetailsItem> getExecutionDetails(){
		return executionDetails;
	}

	@Override
 	public String toString(){
		return 
			"ConsumerDetails{" + 
			"consumer_details = '" + consumerDetails + '\'' + 
			",survey_details = '" + surveyDetails + '\'' + 
			",execution_details = '" + executionDetails + '\'' + 
			"}";
		}
}
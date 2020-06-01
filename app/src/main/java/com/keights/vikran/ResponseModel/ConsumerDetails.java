package com.keights.vikran.ResponseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ConsumerDetails{

	@SerializedName("jmc_section_a_details")
	private List<JmcSectionADetailsItem> jmcSectionADetails;

	@SerializedName("jmc_section_b_details")
	private List<JmcSectionBDetailsItem> jmcSectionBDetails;

	@SerializedName("billing_details")
	private List<BillingDetailsItem> billingDetails;

	@SerializedName("jmc_section_e_details")
	private List<JmcSectionEDetailsItem> jmcSectionEDetails;

	@SerializedName("consumer_details")
	private List<ConsumerDetailsItem> consumerDetails;

	@SerializedName("survey_details")
	private List<SurveyDetailsItem> surveyDetails;

	@SerializedName("jmc_section_d_details")
	private List<JmcSectionDDetailsItem> jmcSectionDDetails;

	@SerializedName("rtc_details")
	private List<RtcDetailsItem> rtcDetails;

	@SerializedName("execution_details")
	private List<ExecutionDetailsItem> executionDetails;

	@SerializedName("per_com_details")
	private List<PerComDetailsItem> perComDetails;

	@SerializedName("jmc_section_c_details")
	private List<JmcSectionCDetailsItem> jmcSectionCDetails;

	public void setJmcSectionADetails(List<JmcSectionADetailsItem> jmcSectionADetails){
		this.jmcSectionADetails = jmcSectionADetails;
	}

	public List<JmcSectionADetailsItem> getJmcSectionADetails(){
		return jmcSectionADetails;
	}

	public void setJmcSectionBDetails(List<JmcSectionBDetailsItem> jmcSectionBDetails){
		this.jmcSectionBDetails = jmcSectionBDetails;
	}

	public List<JmcSectionBDetailsItem> getJmcSectionBDetails(){
		return jmcSectionBDetails;
	}

	public void setBillingDetails(List<BillingDetailsItem> billingDetails){
		this.billingDetails = billingDetails;
	}

	public List<BillingDetailsItem> getBillingDetails(){
		return billingDetails;
	}

	public void setJmcSectionEDetails(List<JmcSectionEDetailsItem> jmcSectionEDetails){
		this.jmcSectionEDetails = jmcSectionEDetails;
	}

	public List<JmcSectionEDetailsItem> getJmcSectionEDetails(){
		return jmcSectionEDetails;
	}

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

	public void setJmcSectionDDetails(List<JmcSectionDDetailsItem> jmcSectionDDetails){
		this.jmcSectionDDetails = jmcSectionDDetails;
	}

	public List<JmcSectionDDetailsItem> getJmcSectionDDetails(){
		return jmcSectionDDetails;
	}

	public void setRtcDetails(List<RtcDetailsItem> rtcDetails){
		this.rtcDetails = rtcDetails;
	}

	public List<RtcDetailsItem> getRtcDetails(){
		return rtcDetails;
	}

	public void setExecutionDetails(List<ExecutionDetailsItem> executionDetails){
		this.executionDetails = executionDetails;
	}

	public List<ExecutionDetailsItem> getExecutionDetails(){
		return executionDetails;
	}

	public void setPerComDetails(List<PerComDetailsItem> perComDetails){
		this.perComDetails = perComDetails;
	}

	public List<PerComDetailsItem> getPerComDetails(){
		return perComDetails;
	}

	public void setJmcSectionCDetails(List<JmcSectionCDetailsItem> jmcSectionCDetails){
		this.jmcSectionCDetails = jmcSectionCDetails;
	}

	public List<JmcSectionCDetailsItem> getJmcSectionCDetails(){
		return jmcSectionCDetails;
	}

	@Override
 	public String toString(){
		return 
			"ConsumerDetails{" + 
			"jmc_section_a_details = '" + jmcSectionADetails + '\'' + 
			",jmc_section_b_details = '" + jmcSectionBDetails + '\'' + 
			",billing_details = '" + billingDetails + '\'' + 
			",jmc_section_e_details = '" + jmcSectionEDetails + '\'' + 
			",consumer_details = '" + consumerDetails + '\'' + 
			",survey_details = '" + surveyDetails + '\'' + 
			",jmc_section_d_details = '" + jmcSectionDDetails + '\'' + 
			",rtc_details = '" + rtcDetails + '\'' + 
			",execution_details = '" + executionDetails + '\'' + 
			",per_com_details = '" + perComDetails + '\'' + 
			",jmc_section_c_details = '" + jmcSectionCDetails + '\'' + 
			"}";
		}
}
package com.keights.vikran.ResponseModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyWorkModel implements Serializable {

    private String division;
    private String consumerName;
    private String consumerNo;
    private Boolean isSection;
    private String SectionName;



    public Boolean getSection() {
        return isSection;
    }

    public void setSection(Boolean section) {
        isSection = section;
    }
    public String getSectionName() {
        return SectionName;
    }

    public void setSectionName(String sectionName) {
        SectionName = sectionName;
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

    public void setConsumerNo(String consumerNo){
        this.consumerNo = consumerNo;
    }

    public String getConsumerNo(){
        return consumerNo;
    }
}

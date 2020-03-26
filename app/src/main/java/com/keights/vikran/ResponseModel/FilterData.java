package com.keights.vikran.ResponseModel;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FilterData implements Serializable {

	@SerializedName("list")
	private List<FilterList> weekList;

	public void setFilterList(List<FilterList> weekList){
		this.weekList = weekList;
	}

	public List<FilterList> getFilterList(){
		return weekList;
	}

	@Override
 	public String toString(){
		return 
			"FilterData{" + 
			"list = '" + weekList + '\'' +
			"}";
		}
}
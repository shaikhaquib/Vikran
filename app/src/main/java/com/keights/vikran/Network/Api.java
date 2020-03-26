package com.keights.vikran.Network;

import com.keights.vikran.ResponseModel.AddSurveyDetailsResponse;
import com.keights.vikran.ResponseModel.DaysFilterResponse;
import com.keights.vikran.ResponseModel.SearchConsumerResponse;
import com.keights.vikran.ResponseModel.UserInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("user_login")
    Call<UserInfo> user_login(@Field("username") String username ,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("search_consumer")
    Call<SearchConsumerResponse> search_consumer(@Field("reporting_id") String reporting_id ,
                                                 @Field("user_id") String user_id ,
                                                 @Field("consumer_no") String consumer_no);

    @FormUrlEncoded
    @POST("add_survey_details")
    Call<AddSurveyDetailsResponse> add_survey_details(@Field("reporting_id") String reporting_id ,
                                                      @Field("user_id") String user_id ,
                                                      @Field("consumer_no") String consumer_no,
                                                      @Field("survey_pairing") String survey_pairing ,
                                                      @Field("ht_line_in_km") String ht_line_in_km ,
                                                      @Field("rjs_pole_old_freezing") String rjs_pole_old_freezing,
                                                      @Field("username") String username ,
                                                      @Field("approach_road") String approach_road ,
                                                      @Field("soil_strata") String soil_strata,
                                                      @Field("row") String row ,
                                                      @Field("tree_cutting") String tree_cutting ,
                                                      @Field("remarks") String remarks,
                                                      @Field("live_location") String live_location
                                                    );
    @FormUrlEncoded
    @POST("days_filter")
    Call<DaysFilterResponse> days_filter(@Field("reporting_id") String reporting_id ,
                                         @Field("user_id") String user_id ,
                                         @Field("division") String division,
                                         @Field("filter_name") String filter_name);

}

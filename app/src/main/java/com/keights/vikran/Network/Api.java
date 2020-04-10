package com.keights.vikran.Network;

import com.keights.vikran.ResponseModel.AddExecutionResponse;
import com.keights.vikran.ResponseModel.AddSurveyDetailsResponse;
import com.keights.vikran.ResponseModel.DaysFilterResponse;
import com.keights.vikran.ResponseModel.JMCResponse;
import com.keights.vikran.ResponseModel.SearchConsumerResponse;
import com.keights.vikran.ResponseModel.UserInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("user_login")
    Call<UserInfo> user_login(
            @Field("username") String username,
            @Field("password") String password,
            @Field("device_token") String device_token);

    @FormUrlEncoded
    @POST("search_consumer")
    Call<SearchConsumerResponse> search_consumer(
            @Field("reporting_id") String reporting_id,
            @Field("user_id") String user_id,
            @Field("consumer_no") String consumer_no,
            @Field("division") String division);

    @FormUrlEncoded
    @POST("add_survey_details")
    Call<AddSurveyDetailsResponse> add_survey_details(
            @Field("reporting_id") String reporting_id,
            @Field("user_id") String user_id,
            @Field("consumer_no") String consumer_no,
            @Field("survey_pairing") String survey_pairing,
            @Field("ht_line_in_km") String ht_line_in_km,
            @Field("rjs_pole_old_freezing") String rjs_pole_old_freezing,
            @Field("username") String username,
            @Field("approach_road") String approach_road,
            @Field("soil_strata") String soil_strata,
            @Field("row") String row,
            @Field("tree_cutting") String tree_cutting,
            @Field("remarks") String remarks,
            @Field("live_location") String live_location,
            @Field("division") String division
    );

    @FormUrlEncoded
    @POST("days_filter")
    Call<DaysFilterResponse> days_filter(
            @Field("reporting_id") String reporting_id,
            @Field("user_id") String user_id,
            @Field("division") String division,
            @Field("filter_name") String filter_name);

    @FormUrlEncoded
    @POST("add_execution_details")
    Call<AddExecutionResponse> add_execution_details(
            @Field("reporting_i") String reporting_i,
            @Field("user_id") String user_id,
            @Field("consumer_no") String consumer_no,
            @Field("division") String division,
            @Field("survey_id") String survey_id,
            @Field("name_of_feeder") String name_of_feeder,
            @Field("tf_make_sl_no") String tf_make_sl_no,
            @Field("tf_capacity_in_kva") String tf_capacity_in_kva,
            @Field("dtr_code") String dtr_code,
            @Field("meter_no") String meter_no,
            @Field("gps_cordinat_dtr_north") String gps_cordinat_dtr_north,
            @Field("gps_cordinat_dtr_east") String gps_cordinat_dtr_east,
            @Field("gps_cordinat_tapping_north") String gps_cordinat_tapping_north,
            @Field("gps_cordinat_tapping_east") String gps_cordinat_tapping_east,
            @Field("feeder_node") String feeder_node,
            @Field("10_kva_dtr") String _10_kva_dtr,
            @Field("16_kva_dtc") String _16_kva_dtc,
            @Field("25_kva_dtc") String _25_kva_dtc,
            @Field("ht_stringing_mtr") String ht_stringing_mtr,
            @Field("inline_pole") String inline_pole,
            @Field("cut_point_pole_s1") String cut_point_pole_s1,
            @Field("cut_point_pole_s2") String cut_point_pole_s2,
            @Field("cut_point_pole_dbl") String cut_point_pole_dbl,
            @Field("tapping_pole") String tapping_pole,
            @Field("guarding") String guarding,
            @Field("guarding_len_mtr") String guarding_len_mtr,
            @Field("stay_set") String stay_set,
            @Field("11_mtr_rjs_pole") String _11_mtr_rjs_pole,
            @Field("9_mtr_stud_pole ") String _9_mtr_stud_pole,
            @Field("lt_ab_cable_mtr") String lt_ab_cable_mtr,
            @Field("sub_station_name") String sub_station_name,
            @Field("gps_cordinat_consu_east") String gps_cordinat_consu_east,
            @Field("gps_cordinat_consu_north") String gps_cordinat_consu_north,
            @Field("11_kv_ab_switch") String _11_kv_ab_switch,
            @Field("22_kv_ab_switch") String _22_kv_ab_switch);


    @FormUrlEncoded
    @POST("add_jmc_details")
    Call<JMCResponse> add_jmc_details(
            @Field("reporting_id") String reporting_id,
            @Field("user_id") String user_id,
            @Field("consumer_no") String consumer_no,
            @Field("division") String division,
            @Field("voltage_type") String voltage_type,
            @Field("pole_type") String pole_type,
            @Field("inline_pole") String inline_pole,
            @Field("single_cutpoint_pole") String single_cutpoint_pole,
            @Field("double_cutpoint_pole") String double_cutpoint_pole,
            @Field("new_tapping") String new_tapping,
            @Field("existing_tapping") String existing_tapping,
            @Field("stud_pole") String stud_pole,
            @Field("no_of_guarding") String no_of_guarding,
            @Field("gaurding_span") String gaurding_span,
            @Field("stay_set") String stay_set,
            @Field("i_type_dtc") String i_type_dtc,
            @Field("span_in_mtr") String span_in_mtr,
            @Field("tapping_from_existing_dtc") String tapping_from_existing_dtc,
            @Field("remarks") String remarks);


}

package com.keights.vikran.Network;

import com.keights.vikran.ResponseModel.AddExecutionResponse;
import com.keights.vikran.ResponseModel.AddSurveyDetailsResponse;
import com.keights.vikran.ResponseModel.AssignExecutionResponse;
import com.keights.vikran.ResponseModel.BillingResponse;
import com.keights.vikran.ResponseModel.DaysFilterResponse;
import com.keights.vikran.ResponseModel.JMCResponse;
import com.keights.vikran.ResponseModel.MyWorkFilterResponse;
import com.keights.vikran.ResponseModel.PermcommResponse;
import com.keights.vikran.ResponseModel.RTCResponse;
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
    @POST("days_my_work")
    Call<MyWorkFilterResponse> days_filter(
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
            @Field("num_of_pole") String num_of_pole,
            @Field("pole_shifting") String pole_shifting,
            @Field("pole_erection") String pole_erection,
            @Field("str_fitting") String str_fitting,
            @Field("string_per_location") String string_per_location,
            @Field("dtr_erection") String dtr_erection,
            @Field("finishing_per_location") String finishing_per_location,
            @Field("transformer_make") String transformer_make,
            @Field("tf_sl_no") String tf_sl_no,
            @Field("tf_capacity_in_kva") String tf_capacity_in_kva,
            @Field("meter_make") String meter_make,
            @Field("meter_no") String meter_no,
            @Field("mtr9_psc") String mtr9_psc,
            @Field("mtr9_rsj") String mtr9_rsj,
            @Field("mtr11_rsj") String mtr11_rsj
          );


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
            @Field("i_type_dtc_inline") String i_type_dtc_inline,
            @Field("span_in_mtr") String span_in_mtr,
            @Field("tapping_from_existing_dtc") String tapping_from_existing_dtc,
            @Field("remarks") String remarks);

    @FormUrlEncoded
    @POST("add_jmc_b_details")
    Call<JMCResponse> add_jmc_b_details(
            @Field("reporting_id") String reporting_id,
            @Field("user_id") String user_id,
            @Field("consumer_no") String consumer_no,
            @Field("division") String division,
            @Field("voltage_type") String voltage_type,
            @Field("10_kva_dtr") String _10_kva_dtr,
            @Field("16_kva_dtr") String _16_kva_dtr,
            @Field("25_kva_dtr") String _25_kva_dtr
            );

    @FormUrlEncoded
    @POST("add_jmc_c_details")
    Call<JMCResponse> add_jmc_c_details(
            @Field("reporting_id") String reporting_id,
            @Field("user_id") String user_id,
            @Field("consumer_no") String consumer_no,
            @Field("division") String division,
            @Field("new_pole")      String new_pole,
            @Field("existing_pole") String existing_pole,
            @Field("stay")          String stay,
            @Field("span_in_mtr")   String span_in_mtr
            );

 @FormUrlEncoded
    @POST("add_jmc_d_details")
    Call<JMCResponse> add_jmc_d_details(
            @Field("reporting_id") String reporting_id,
            @Field("user_id") String user_id,
            @Field("consumer_no") String consumer_no,
            @Field("division") String division,
            @Field("service_connection") String service_connection
            );

    @FormUrlEncoded
    @POST("add_jmc_e_details")
    Call<JMCResponse> add_jmc_e_details(
            @Field("reporting_id") String reporting_id,
            @Field("user_id") String user_id,
            @Field("consumer_no") String consumer_no,
            @Field("division") String division,
            @Field("live_consumer_no") String live_consumer_no,
            @Field("name_of_feeder") String name_of_feeder,
            @Field("sub_station_name") String sub_station_name,
            @Field("gps_dtr_north") String gps_dtr_north,
            @Field("gps_dtr_east") String gps_dtr_east,
            @Field("gps_tapping_north") String gps_tapping_north,
            @Field("gps_tapping_east") String gps_tapping_east,
            @Field("gps_live_north") String gps_live_north,
            @Field("gps_live_east") String gps_live_east,
            @Field("feeder_note") String feeder_note,
            @Field("dtr_code") String dtr_code
            );
    @FormUrlEncoded
    @POST("add_rtc_details")
    Call<RTCResponse> add_rtc_details(
            @Field("reporting_id") String reporting_id,
            @Field("user_id") String user_id,
            @Field("consumer_no") String consumer_no,
            @Field("division") String division,
            @Field("rtc_status") String rtc_status
            );

  @FormUrlEncoded
    @POST("add_permission_commission")
    Call<PermcommResponse> add_permission_commission(
            @Field("reporting_id") String reporting_id,
            @Field("user_id") String user_id,
            @Field("consumer_no") String consumer_no,
            @Field("division") String division,
            @Field("applied_e1_permission") String applied_e1_permission,
            @Field("received_e1_permission") String received_e1_permission,
            @Field("applied_commission") String applied_commission,
            @Field("remark") String remark
            );

    @FormUrlEncoded
    @POST("add_billing")
    Call<BillingResponse> add_billing(
            @Field("reporting_id") String reporting_id,
            @Field("user_id") String user_id,
            @Field("consumer_no") String consumer_no,
            @Field("division") String division,
            @Field("billing_status") String billing_status,
            @Field("remark") String remark
            );

    @FormUrlEncoded
    @POST("assign_consumer_list")
    Call<AssignExecutionResponse> assign_consumer_list(
            @Field("reporting_id") String reporting_id,
            @Field("user_id") String user_id,
            @Field("division") String division
            );


}

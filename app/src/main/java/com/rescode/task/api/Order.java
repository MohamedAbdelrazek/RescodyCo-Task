package com.rescode.task.api;

import com.rescode.task.NewOrderModel;
import com.rescode.task.OrderModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Order {

    @POST("api/store_Order")
    @FormUrlEncoded
    Call<OrderModel> sendOrder(@Field("user_id") String user_id,
                               @Field("product_id") String product_id,
                               @Field("brunche_id") String brunche_id,
                               @Field("count") String count,
                               @Field("addition") String addition,
                               @Field("subadd") String subadd,
                               @Field("totlePrice") String totlePrice);

    @POST("api/Product")
    @FormUrlEncoded
    Call<NewOrderModel> getOrderByID(@Field("id") String user_id);
}

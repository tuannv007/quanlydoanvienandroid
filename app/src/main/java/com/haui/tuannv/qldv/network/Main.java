package com.haui.tuannv.qldv.network;

import com.haui.tuannv.qldv.data.local.model.DataClasses;
import com.haui.tuannv.qldv.data.local.model.DataDepartment;
import com.haui.tuannv.qldv.data.local.model.DataRevenue;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by tuanbg on 4/9/17.
 */
public class Main {
    public interface DemartmentService {
        @GET("/api/input/index")
        Call<ResponseItem<DataDepartment>> getDepartment();

        @GET("/api/input/classes")
        Call<ResponseItem<DataClasses>> getClasses(@Query("department_id") String departmentId,
                @Query("school_year_id") String schoolYearId);

        @POST("/api/input/create")
        @FormUrlEncoded
        Call<ResponseItem> addOtherSpend(@Field("title") String title, @Field("year") int year,
                @Field("amount") double amount, @Field("description") String description);

        @GET("/api/output/index")
        Call<ResponseItem<DataRevenue>> getAllRevenue(@Query("year") int year);

        @POST("/api/output/create")
        @FormUrlEncoded
        Call<ResponseItem> addNewRevenue(@Field("title") String title, @Field("user_id") int userId,
                @Field("amount") double amount, @Field("description") String description,
                @Field("paid_date") String date);
    }
}

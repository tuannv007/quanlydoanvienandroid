package com.haui.tuannv.qldv.network;

import com.haui.tuannv.qldv.data.local.model.DataClasses;
import com.haui.tuannv.qldv.data.local.model.DataDepartment;
import com.haui.tuannv.qldv.data.local.model.DataRevenue;
import com.haui.tuannv.qldv.data.local.model.DataStudents;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.data.local.model.Student;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
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
        Call<ResponseItem<DataDepartment>> getDepartment(@Query("user_id") int userId);

        @GET("/api/input/classes")
        Call<ResponseItem<DataClasses>> getClasses(@Query("department_id") String departmentId,
                @Query("school_year_id") String schoolYearId);

        @POST("/api/input/create")
        @FormUrlEncoded
        Call<ResponseItem> addOtherSpend(@Field("title") String title, @Field("year") int year,
                @Field("amount") double amount, @Field("description") String description);

        @GET("/api/output/index")
        Call<ResponseItem<DataRevenue>> getAllRevenue(@Query("year") int year,
                @Query("department_id") String departmentId);

        @POST("/api/output/create")
        @FormUrlEncoded
        Call<ResponseItem> addNewRevenue(@Field("title") String title, @Field("user_id") int userId,
                @Field("amount") double amount, @Field("description") String description,
                @Field("paid_date") String date, @Field("department_id") String departmentId);

        @GET("/api/input/students")
        Call<ResponseItem<DataStudents>> getStudents(@Query("class_id") int classId,
                @Query("fee_id") int feeId);

        @POST("/api/input/update")
        Call<ResponseItem> updateMoney(@Body RequestBody body);
    }

    public static class UpdateBody {
        private static final String FEE_ID = "fee_id";
        private static final String NEW_MONEY = "new_st_ids[%s]";
        private static final String REMOVE_MONEY = "unset_st_ids[%s]";
        private String mFeeId;
        private List<Student> mStudentList;

        public UpdateBody(String feeId, List<Student> studentList) {
            mFeeId = feeId;
            mStudentList = studentList;
        }

        public RequestBody getRequestBody() {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            builder.addFormDataPart(FEE_ID, this.mFeeId);
            for (Student student : mStudentList) {
                if (student.getFeePaid() == 1) {
                    String newMoney = String.format(NEW_MONEY, student.getId());
                    builder.addFormDataPart(newMoney, String.valueOf(student.getId()));
                } else {
                    String removeMoney = String.format(REMOVE_MONEY, student.getId());
                    builder.addFormDataPart(removeMoney, String.valueOf(student.getId()));
                }
            }
            return builder.build();
        }
    }
}

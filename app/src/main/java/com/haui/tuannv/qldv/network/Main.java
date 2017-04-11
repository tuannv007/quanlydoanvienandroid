package com.haui.tuannv.qldv.network;

import com.haui.tuannv.qldv.data.local.model.DataClasses;
import com.haui.tuannv.qldv.data.local.model.DataDepartment;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import retrofit2.Call;
import retrofit2.http.GET;
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
    }
}

package com.haui.tuannv.qldv.network;

import com.haui.tuannv.qldv.data.local.model.DataDepartment;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tuanbg on 4/9/17.
 */
public class Main {
    public interface DemartmentService {
        @GET("/api/input/index")
        Call<ResponseItem<DataDepartment>> getDepartment();
    }
}

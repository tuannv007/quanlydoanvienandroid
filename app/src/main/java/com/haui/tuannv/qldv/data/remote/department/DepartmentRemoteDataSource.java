package com.haui.tuannv.qldv.data.remote.department;

import android.util.Log;

import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.data.ServiceGenerator;
import com.haui.tuannv.qldv.data.local.model.DataDepartment;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.network.Main;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tuanbg on 4/9/17.
 */
public class DepartmentRemoteDataSource implements DepartmentDataSource {
    private static DepartmentRemoteDataSource mRemoteDataSource;

    public static DepartmentRemoteDataSource getInstance() {
        if (mRemoteDataSource == null) mRemoteDataSource = new DepartmentRemoteDataSource();
        return mRemoteDataSource;
    }

    @Override
    public void getDepartment(final DataCallback callback) {
        if (callback == null) return;
        ServiceGenerator
            .createService(Main.DemartmentService.class)
            .getDepartment()
            .enqueue(new Callback<ResponseItem<DataDepartment>>() {
                @Override
                public void onResponse(Call<ResponseItem<DataDepartment>> call,
                                       Response<ResponseItem<DataDepartment>> response) {
                    callback.onSuccess(response.body());
                }

                @Override
                public void onFailure(Call<ResponseItem<DataDepartment>> call, Throwable t) {
                    callback.onError(t.getMessage());
                    Log.e("error", t.getMessage());
                }
            });
    }
}

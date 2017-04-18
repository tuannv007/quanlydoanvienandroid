package com.haui.tuannv.qldv.data.remote.department;

import android.util.Log;
import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.data.ServiceGenerator;
import com.haui.tuannv.qldv.data.local.model.DataClasses;
import com.haui.tuannv.qldv.data.local.model.DataDepartment;
import com.haui.tuannv.qldv.data.local.model.DataRevenue;
import com.haui.tuannv.qldv.data.local.model.DataStudents;
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
    public void getDepartment(int userId, final DataCallback callback) {
        if (callback == null) return;
        ServiceGenerator.createService(Main.DemartmentService.class)
                .getDepartment(userId)
                .enqueue(new Callback<ResponseItem<DataDepartment>>() {
                    @Override
                    public void onResponse(Call<ResponseItem<DataDepartment>> call,
                            Response<ResponseItem<DataDepartment>> response) {
                        callback.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseItem<DataDepartment>> call, Throwable t) {
                        callback.onError(t.getMessage());
                    }
                });
    }

    @Override
    public void getClasses(String departmentId, String schoolId, final DataCallback callback) {
        if (callback == null) return;
        ServiceGenerator.createService(Main.DemartmentService.class)
                .getClasses(departmentId, schoolId)
                .enqueue(new Callback<ResponseItem<DataClasses>>() {
                    @Override
                    public void onResponse(Call<ResponseItem<DataClasses>> call,
                            Response<ResponseItem<DataClasses>> response) {
                        callback.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseItem<DataClasses>> call, Throwable t) {
                        callback.onError(t.getMessage());
                    }
                });
    }

    @Override
    public void addOtherSpend(String title, int year, double amount, String description,
            final DataCallback callback) {
        ServiceGenerator.createService(Main.DemartmentService.class)
                .addOtherSpend(title, year, amount, description)
                .enqueue(new Callback<ResponseItem>() {
                    @Override
                    public void onResponse(Call<ResponseItem> call,
                            Response<ResponseItem> response) {
                        callback.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseItem> call, Throwable t) {
                        callback.onError(t.getMessage());
                    }
                });
    }

    @Override
    public void getAllRevenue(int year, String departmentId, final DataCallback callback) {
        ServiceGenerator.createService(Main.DemartmentService.class)
                .getAllRevenue(year, departmentId)
                .enqueue(new Callback<ResponseItem<DataRevenue>>() {
                    @Override
                    public void onResponse(Call<ResponseItem<DataRevenue>> call,
                            Response<ResponseItem<DataRevenue>> response) {
                        callback.onSuccess(response.body());
                        Log.e("Tag", call.request().url() + "");
                    }

                    @Override
                    public void onFailure(Call<ResponseItem<DataRevenue>> call, Throwable t) {
                        callback.onError(t.getMessage());
                    }
                });
    }

    @Override
    public void addNewRevenue(String title, int userId, double amount, String description,
            String date, String departmentId, final DataCallback callback) {
        ServiceGenerator.createService(Main.DemartmentService.class)
                .addNewRevenue(title, userId, amount, description, date, departmentId)
                .enqueue(new Callback<ResponseItem>() {
                    @Override
                    public void onResponse(Call<ResponseItem> call,
                            Response<ResponseItem> response) {
                        callback.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseItem> call, Throwable t) {
                        callback.onError(t.getMessage());
                    }
                });
    }

    @Override
    public void getStudents(int classId, int feeId, final DataCallback callback) {
        ServiceGenerator.createService(Main.DemartmentService.class)
                .getStudents(classId, feeId)
                .enqueue(new Callback<ResponseItem<DataStudents>>() {
                    @Override
                    public void onResponse(Call<ResponseItem<DataStudents>> call,
                            Response<ResponseItem<DataStudents>> response) {
                        callback.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseItem<DataStudents>> call, Throwable t) {
                        callback.onError(t.getMessage());
                    }
                });
    }

    @Override
    public void updateMoney(Main.UpdateBody updateBody, final DataCallback callback) {
        ServiceGenerator.createService(Main.DemartmentService.class)
                .updateMoney(updateBody.getRequestBody())
                .enqueue(new Callback<ResponseItem>() {
                    @Override
                    public void onResponse(Call<ResponseItem> call,
                            Response<ResponseItem> response) {
                        callback.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseItem> call, Throwable t) {
                        callback.onError(t.getMessage());
                    }
                });
    }
}

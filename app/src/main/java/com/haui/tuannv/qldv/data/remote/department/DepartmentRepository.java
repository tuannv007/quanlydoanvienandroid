package com.haui.tuannv.qldv.data.remote.department;

import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.data.local.model.DataClasses;
import com.haui.tuannv.qldv.data.local.model.DataDepartment;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.network.Main;

/**
 * Created by tuanbg on 4/9/17.
 */
public class DepartmentRepository implements DepartmentDataSource {
    private static DepartmentRepository mRepository;
    private DepartmentRemoteDataSource mRemoteDataSource;

    public DepartmentRepository(DepartmentRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public static DepartmentRepository getInstance() {
        if (mRepository == null) {
            mRepository = new DepartmentRepository(DepartmentRemoteDataSource.getInstance());
        }
        return mRepository;
    }

    @Override
    public void getDepartment(int userId, final DataCallback callback) {
        if (callback == null) return;
        mRemoteDataSource.getDepartment(userId, new DataCallback<ResponseItem<DataDepartment>>() {
            @Override
            public void onSuccess(ResponseItem<DataDepartment> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void getClasses(String departmentId, String schoolId, final DataCallback callback) {
        if (callback == null) return;
        mRemoteDataSource.getClasses(departmentId, schoolId,
                new DataCallback<ResponseItem<DataClasses>>() {
                    @Override
                    public void onSuccess(ResponseItem<DataClasses> data) {
                        callback.onSuccess(data);
                    }

                    @Override
                    public void onError(String msg) {
                        callback.onError(msg);
                    }
                });
    }

    @Override
    public void addOtherSpend(String title, int year, double amount, String description,
            final DataCallback callback) {
        mRemoteDataSource.addOtherSpend(title, year, amount, description,
                new DataCallback<ResponseItem>() {
                    @Override
                    public void onSuccess(ResponseItem data) {
                        callback.onSuccess(data);
                    }

                    @Override
                    public void onError(String msg) {
                        callback.onError(msg);
                    }
                });
    }

    @Override
    public void getAllRevenue(int year, String departmentId, final DataCallback callback) {
        mRemoteDataSource.getAllRevenue(year, departmentId, new DataCallback<ResponseItem>() {
            @Override
            public void onSuccess(ResponseItem data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void addNewRevenue(String title, int userId, double amount, String description,
            String date, String departmentId, final DataCallback callback) {
        mRemoteDataSource.addNewRevenue(title, userId, amount, description, date, departmentId,
                new DataCallback<ResponseItem>() {
                    @Override
                    public void onSuccess(ResponseItem data) {
                        callback.onSuccess(data);
                    }

                    @Override
                    public void onError(String msg) {
                        callback.onError(msg);
                    }
                });
    }

    @Override
    public void getStudents(int classId, int feeId, final DataCallback callback) {
        mRemoteDataSource.getStudents(classId, feeId, new DataCallback<ResponseItem>() {
            @Override
            public void onSuccess(ResponseItem data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void updateMoney(Main.UpdateBody updateBody, final DataCallback callback) {
        mRemoteDataSource.updateMoney(updateBody, new DataCallback<ResponseItem>() {
            @Override
            public void onSuccess(ResponseItem data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }
}

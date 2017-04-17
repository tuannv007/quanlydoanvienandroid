package com.haui.tuannv.qldv.data.remote.department;

import com.haui.tuannv.qldv.data.DataCallback;

/**
 * Created by tuanbg on 4/9/17.
 */
public interface DepartmentDataSource {
    void getDepartment(DataCallback callback);

    void getClasses(String departmentId, String schoolId, DataCallback callback);

    void addOtherSpend(String title, int year, double amount, String description,
            DataCallback callback);

    void getAllRevenue(int year, DataCallback callback);

    void addNewRevenue(String title, int userId, double amount, String description, String date,
            DataCallback callback);
}

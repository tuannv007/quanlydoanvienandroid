package com.haui.tuannv.qldv.data.remote.department;

import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.network.Main;

/**
 * Created by tuanbg on 4/9/17.
 */
public interface DepartmentDataSource {
    void getDepartment(int userId, DataCallback callback);

    void getClasses(String departmentId, String schoolId, DataCallback callback);

    void addOtherSpend(String title, int year, double amount, String description,
            DataCallback callback);

    void getAllRevenue(int year, String departmentId, DataCallback callback);

    void addNewRevenue(String title, int userId, double amount, String description, String date,
            String departmentId, DataCallback callback);

    void getStudents(int classId, int feeId, DataCallback callback);

    void updateMoney(Main.UpdateBody updateBody, DataCallback callback);
}

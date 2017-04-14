package com.haui.tuannv.qldv.ui.spend;

import com.haui.tuannv.qldv.data.local.model.DataDepartment;
import com.haui.tuannv.qldv.data.local.model.Department;
import com.haui.tuannv.qldv.data.local.model.Fee;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.data.local.model.SchoolYear;

/**
 * Created by tuanbg on 4/9/17.
 */
public interface SpendListener {
    void onSuccess(ResponseItem<DataDepartment> data);

    void onError(String msg);

    void showDialog();

    void dismissDialog();

    void getClasseFromDepartment(Department department, SchoolYear schoolYear, Fee fee);

    void openOtherSpend();
}

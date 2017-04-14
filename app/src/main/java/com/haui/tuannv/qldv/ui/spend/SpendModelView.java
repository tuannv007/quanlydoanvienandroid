package com.haui.tuannv.qldv.ui.spend;

import android.content.Context;
import android.widget.ArrayAdapter;
import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.data.local.model.DataDepartment;
import com.haui.tuannv.qldv.data.local.model.Department;
import com.haui.tuannv.qldv.data.local.model.Fee;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.data.local.model.SchoolYear;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanbg on 4/9/17.
 */
public class SpendModelView {
    private SpendListener mListener;
    private DepartmentRepository mRepository;
    private List<Department> mDepartmentList = new ArrayList<>();
    private List<SchoolYear> mSchoolYearList = new ArrayList<>();
    private List<Fee> mFeeList = new ArrayList<>();
    private Context mContext;

    public SpendModelView(Context context, SpendListener listener,
            DepartmentRepository repository) {
        mListener = listener;
        mRepository = repository;
        mContext = context;
    }

    public void getDepartment() {
        mListener.showDialog();
        mRepository.getDepartment(new DataCallback<ResponseItem<DataDepartment>>() {
            @Override
            public void onSuccess(ResponseItem<DataDepartment> data) {
                mListener.onSuccess(data);
                mListener.dismissDialog();
            }

            @Override
            public void onError(String msg) {
                mListener.onError(msg);
                mListener.dismissDialog();
            }
        });
    }

    public ArrayAdapter<Department> getDepartment(ResponseItem<DataDepartment> data) {
        mDepartmentList.addAll(data.getData().getDepartments());
        return new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_dropdown_item,
                mDepartmentList);
    }

    public ArrayAdapter<SchoolYear> getSchoolYear(ResponseItem<DataDepartment> data) {
        mSchoolYearList.addAll(data.getData().getSchoolYears());
        return new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_dropdown_item,
                mSchoolYearList);
    }

    public ArrayAdapter<Fee> getFees(ResponseItem<DataDepartment> data) {
        mFeeList.addAll(data.getData().getFees());
        return new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_dropdown_item,
                mFeeList);
    }

    public void getClasse(Department department, SchoolYear schoolYear, Fee fee) {
        mListener.getClasseFromDepartment(department, schoolYear, fee);
    }
    public void openOtherSpend(){
        mListener.openOtherSpend();
    }
}

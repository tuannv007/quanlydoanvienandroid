package com.haui.tuannv.qldv.ui.monney.classes;

import android.databinding.ObservableBoolean;
import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.data.local.model.Classes;
import com.haui.tuannv.qldv.data.local.model.DataClasses;
import com.haui.tuannv.qldv.data.local.model.Fee;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.ui.BaseViewModel;

/**
 * Created by tuanbg on 4/11/17.
 */

public class ClassesViewModel extends BaseViewModel {
    private ClassesListener mListener;
    private DepartmentRepository mRepository;
    private ObservableBoolean mIsRefresh = new ObservableBoolean();

    public ClassesViewModel(ClassesListener listener, DepartmentRepository repository) {
        mListener = listener;
        mRepository = repository;
    }

    public void getClassesFromDepartment(String departmentId, String schoolYearId) {
        mIsRefresh.set(true);
        mRepository.getClasses(departmentId, schoolYearId,
                new DataCallback<ResponseItem<DataClasses>>() {
                    @Override
                    public void onSuccess(ResponseItem<DataClasses> data) {
                        mListener.getDataSuccess(data.getData().getClasses());
                        hideDialog();
                        mIsRefresh.set(false);
                    }

                    @Override
                    public void onError(String msg) {
                        mListener.getDataError(msg);
                        mIsRefresh.set(false);
                    }
                });
    }

    public void getStudent(Classes classes, Fee fee) {
        mListener.getStudent(classes, fee);
    }

    public ObservableBoolean isRefresh() {
        return mIsRefresh;
    }
}

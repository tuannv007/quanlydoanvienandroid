package com.haui.tuannv.qldv.ui.monney.classes;

import android.content.Context;
import android.databinding.ObservableBoolean;
import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.data.local.model.DataClasses;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.ui.BaseViewModel;

/**
 * Created by tuanbg on 4/11/17.
 */

public class ClassesViewModel extends BaseViewModel {
    private ClassesListener mListener;
    private DepartmentRepository mRepository;
    private Context mContext;
    private ObservableBoolean mIsRefresh = new ObservableBoolean();

    public ClassesViewModel(Context context, ClassesListener listener,
            DepartmentRepository repository) {
        mListener = listener;
        mRepository = repository;
        mContext = context;
    }

    public void getClassesFromDepartment(String departmentId, String schoolYearId) {
        mIsRefresh.set(true);
        showDialog(mContext);
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
                        hideDialog();
                        mIsRefresh.set(false);
                    }
                });
    }

    public ObservableBoolean isRefresh() {
        return mIsRefresh;
    }
}

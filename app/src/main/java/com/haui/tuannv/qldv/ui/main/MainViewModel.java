package com.haui.tuannv.qldv.ui.main;

import android.content.Context;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.ui.BaseViewModel;

/**
 * Created by tuanbg on 3/29/17.
 */
public class MainViewModel extends BaseViewModel {
    private DepartmentRepository mRepository;
    private MainListener mListener;
    private Context mContext;

    public MainViewModel(Context context, DepartmentRepository repository, MainListener listener) {
        mRepository = repository;
        mListener = listener;
        mContext = context;
    }

    public void getDepartment() {
       /* showDialog(mContext);
        mRepository.getDepartment(new DataCallback<ResponseItem<DataDepartment>>() {
            @Override
            public void onSuccess(ResponseItem<DataDepartment> data) {
                mListener.onSuccess(data);
                hideDialog();
            }

            @Override
            public void onError(String msg) {
                mListener.onError(msg);
                hideDialog();
            }
        });*/
    }
}

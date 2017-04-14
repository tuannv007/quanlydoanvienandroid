package com.haui.tuannv.qldv.ui.statistical;

import android.content.Context;
import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.data.local.model.DataRevenue;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.ui.BaseViewModel;

/**
 * Created by tuanbg on 4/14/17.
 */

public class StatisticalViewModel extends BaseViewModel {
    private StatisticalListener mListener;
    private DepartmentRepository mRepository;
    private Context mContext;

    public StatisticalViewModel(Context context, StatisticalListener listener,
            DepartmentRepository repository) {
        mListener = listener;
        mRepository = repository;
        mContext = context;
    }

    public void getData(int year) {
        showDialog(mContext);
        mRepository.getAllRevenue(year, new DataCallback<ResponseItem<DataRevenue>>() {
            @Override
            public void onSuccess(ResponseItem<DataRevenue> data) {
                mListener.getDataSuccess(data.getData());
                hideDialog();
            }

            @Override
            public void onError(String msg) {
                mListener.getDataError(msg);
                hideDialog();
            }
        });
    }
}

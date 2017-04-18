package com.haui.tuannv.qldv.ui.spend;

import android.content.Context;
import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.data.local.model.DataRevenue;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.ui.BaseViewModel;
import java.util.Calendar;

/**
 * Created by tuanbg on 4/14/17.
 */

public class SpendViewModel extends BaseViewModel {
    private SpendListener mListener;
    private DepartmentRepository mRepository;
    private Context mContext;
    public SpendViewModel(Context context, SpendListener listener, DepartmentRepository repository) {
        mListener = listener;
        mRepository = repository;
        mContext = context;
    }

    public void getData(int year,String departmentId) {
        showDialog(mContext);
        mRepository.getAllRevenue(year, departmentId,
                new DataCallback<ResponseItem<DataRevenue>>() {
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

    public int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}

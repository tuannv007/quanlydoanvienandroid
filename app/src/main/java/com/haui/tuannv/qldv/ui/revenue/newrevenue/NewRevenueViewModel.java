package com.haui.tuannv.qldv.ui.revenue.newrevenue;

import android.content.Context;
import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.data.local.model.NewRevenue;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.ui.BaseViewModel;
import com.haui.tuannv.qldv.util.ActivityUtil;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by tuanbg on 4/15/17.
 */

public class NewRevenueViewModel extends BaseViewModel {
    private NewRevenueListener mListener;
    private DepartmentRepository mRepository;
    private NewRevenue mNewRevenue = new NewRevenue();
    private int mIdUser;
    private Context mContext;

    public NewRevenueViewModel(Context context, NewRevenueListener listener,
            DepartmentRepository repository, int idUser) {
        mContext = context;
        mListener = listener;
        mRepository = repository;
        mIdUser = idUser;
    }

    private boolean checkEmpty() {
        if (mNewRevenue.getTitle() == null) {
            ActivityUtil.showToast(mContext,
                    mContext.getString(R.string.mes_title_other_spend_empty));
            return false;
        }

        if (mNewRevenue.getAmount() <= 0) {
            ActivityUtil.showToast(mContext, mContext.getString(R.string.mes_amount_));
            return false;
        }
        if (mNewRevenue.getDesciption() == null) {
            ActivityUtil.showToast(mContext,
                    mContext.getString(R.string.mes_des_other_spend_empty));
            return false;
        }
        return true;
    }

    public void addRevenue() {
        if (checkEmpty()) {
            showDialog(mContext);
            mNewRevenue.setDate(getDate());
            mNewRevenue.setUserId(mIdUser);
            mRepository.addNewRevenue(mNewRevenue.getTitle(), mNewRevenue.getUserId(),
                    mNewRevenue.getAmount(), mNewRevenue.getDesciption(), mNewRevenue.getDate(),
                    new DataCallback<ResponseItem>() {
                        @Override
                        public void onSuccess(ResponseItem data) {
                            mListener.onSuccess(data);
                            hideDialog();
                        }

                        @Override
                        public void onError(String msg) {
                            mListener.onError();
                            hideDialog();
                        }
                    });
        }
    }

    public NewRevenue getNewRevenue() {
        return mNewRevenue;
    }

    public String getDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        return df.format(c.getTime());
    }
}

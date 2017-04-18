package com.haui.tuannv.qldv.ui.monney.otherrevenue;

import android.content.Context;
import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.data.local.model.OtherSpend;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.ui.BaseViewModel;
import com.haui.tuannv.qldv.util.ActivityUtil;
import java.util.Date;

/**
 * Created by tuanbg on 4/14/17.
 */

public class OtherViewModel extends BaseViewModel {
    private OtherdListener mListener;
    private DepartmentRepository mRepository;
    private OtherSpend mOtherSpend = new OtherSpend();
    private Context mContext;
    private Date mDate;

    public OtherViewModel(Context context, OtherdListener listener,
            DepartmentRepository repository) {
        mListener = listener;
        mRepository = repository;
        mContext = context;
        mDate = new Date();
    }

    private boolean checkEmpty() {
        if (mOtherSpend.getTitle() == null) {
            ActivityUtil.showToast(mContext,
                    mContext.getString(R.string.mes_title_other_spend_empty));
            return false;
        }

        if (mOtherSpend.getAmount() <= 0) {
            ActivityUtil.showToast(mContext, mContext.getString(R.string.mes_amount_));
            return false;
        }
        if (mOtherSpend.getDesciption() == null) {
            ActivityUtil.showToast(mContext,
                    mContext.getString(R.string.mes_des_other_spend_empty));
            return false;
        }
        if (mOtherSpend.getYear() <= 0) {
            ActivityUtil.showToast(mContext, mContext.getString(R.string.mes_enter_year));
            return true;
        }
        return true;
    }

    public void addOtherSpend() {
        if (checkEmpty()) {
            showDialog(mContext);
            mRepository.addOtherSpend(mOtherSpend.getTitle(), mOtherSpend.getYear(),
                    mOtherSpend.getAmount(), mOtherSpend.getDesciption(),
                    new DataCallback<ResponseItem>() {
                        @Override
                        public void onSuccess(ResponseItem data) {
                            mListener.getDataSuccess(data);
                            hideDialog();
                        }

                        @Override
                        public void onError(String msg) {
                            mListener.getDataError(msg);
                        }
                    });
        }
    }

    public OtherSpend getOtherSpend() {
        return mOtherSpend;
    }
}

package com.haui.tuannv.qldv.ui.spend.otherspend;

import android.content.Context;
import android.content.SharedPreferences;
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

import static com.haui.tuannv.qldv.util.Constant.ConstBundle.BUNDLE_KEY_ID;
import static com.haui.tuannv.qldv.util.Constant.SharePreference.SHARE_PRE_NAME;

/**
 * Created by tuanbg on 4/15/17.
 */

public class OtherSpendViewModel extends BaseViewModel {
    private OtherSpendListener mListener;
    private DepartmentRepository mRepository;
    private NewRevenue mNewRevenue = new NewRevenue();
    private int mIdUser;
    private Context mContext;

    public OtherSpendViewModel(Context context, OtherSpendListener listener,
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
                    getIdDepartment(), new DataCallback<ResponseItem>() {
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

    public String getIdDepartment() {
        SharedPreferences sharedPreferences =
                mContext.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(BUNDLE_KEY_ID, "");
        }
        return null;
    }
}

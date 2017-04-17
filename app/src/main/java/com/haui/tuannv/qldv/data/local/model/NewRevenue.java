package com.haui.tuannv.qldv.data.local.model;

import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by tuanbg on 4/17/17.
 */

public class NewRevenue extends OtherSpend {
    private int mUserId;
    private String mDate;

    @Bindable
    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
        notifyPropertyChanged(BR.userId);
    }

    @Bindable
    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
        notifyPropertyChanged(BR.date);
    }
}

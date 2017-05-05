package com.haui.tuannv.qldv.data.local.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.google.gson.annotations.SerializedName;
import com.haui.tuannv.qldv.BR;

public class Payment extends BaseObservable {
    @SerializedName("id")
    private String mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("amount")
    private String mAmount;
    @SerializedName("paid_date")
    private String mPaidDate;
    @SerializedName("user_name")
    private String mUserName;

    @Bindable
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getAmount() {
        return mAmount;
    }

    public void setAmount(String amount) {
        mAmount = amount;
        notifyPropertyChanged(BR.amount);
    }

    @Bindable
    public String getPaidDate() {
        return mPaidDate;
    }

    public void setPaidDate(String paidDate) {
        mPaidDate = paidDate;
        notifyPropertyChanged(BR.paidDate);
    }

    @Bindable
    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
        notifyPropertyChanged(BR.userName);
    }
}

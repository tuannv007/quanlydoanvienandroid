package com.haui.tuannv.qldv.data.local.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.google.gson.annotations.SerializedName;
import com.haui.tuannv.qldv.BR;

public class Student extends BaseObservable {

    @SerializedName("id")
    private String mId;
    @SerializedName("code")
    private String mCode;
    @SerializedName("name")
    private String mName;
    @SerializedName("class_name")
    private String mClassName;
    @SerializedName("fee_paid")
    private int mFeePaid;
    @SerializedName("date_fee")
    private String mDateFee;
    private boolean isCheck;

    @Bindable
    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
        notifyPropertyChanged(BR.check);
    }

    @Bindable
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
        notifyPropertyChanged(BR.code);
    }

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getClassName() {
        return mClassName;
    }

    public void setClassName(String className) {
        mClassName = className;
        notifyPropertyChanged(BR.className);
    }

    @Bindable
    public int getFeePaid() {
        return mFeePaid;
    }

    public void setFeePaid(int feePaid) {
        mFeePaid = feePaid;
        notifyPropertyChanged(BR.feePaid);
    }

    @Bindable
    public String getDateFee() {
        return mDateFee;
    }

    public void setDateFee(String dateFee) {
        mDateFee = dateFee;
        notifyPropertyChanged(BR.dateFee);
    }

    @Override
    public String toString() {
        return "Student{"
                + "mId='"
                + mId
                + '\''
                + ", mCode='"
                + mCode
                + '\''
                + ", mName='"
                + mName
                + '\''
                + ", mClassName='"
                + mClassName
                + '\''
                + ", mFeePaid="
                + mFeePaid
                + ", mDateFee='"
                + mDateFee
                + '\''
                + ", isCheck="
                + isCheck
                + '}';
    }
}

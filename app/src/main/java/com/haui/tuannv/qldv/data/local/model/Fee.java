package com.haui.tuannv.qldv.data.local.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.google.gson.annotations.SerializedName;
import com.haui.tuannv.qldv.BR;
import java.io.Serializable;

public class Fee extends BaseObservable implements Serializable {
    @SerializedName("title")
    private String mTitle;
    @SerializedName("year")
    private String mYear;
    @SerializedName("amount")
    private String mAmount;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("user_id")
    private String mUserId;
    @SerializedName("id")
    private int mId;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public String getAmount() {
        return mAmount;
    }

    public void setAmount(String amount) {
        mAmount = amount;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    @Bindable
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Override
    public String toString() {
        return mTitle;
    }
}

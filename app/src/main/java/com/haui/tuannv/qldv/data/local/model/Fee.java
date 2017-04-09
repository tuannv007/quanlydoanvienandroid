package com.haui.tuannv.qldv.data.local.model;

import android.os.Parcel;
import com.google.gson.annotations.SerializedName;

public class Fee extends BaseObject {
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

    protected Fee(Parcel in) {
        super(in);
    }

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

    @Override
    public String toString() {
        return mYear;
    }
}

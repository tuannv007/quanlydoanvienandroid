package com.haui.tuannv.qldv.data.local.model;

import android.databinding.Bindable;
import android.os.Parcel;
import com.google.gson.annotations.SerializedName;
import com.haui.tuannv.qldv.BR;

public class SchoolYear extends BaseObject {
    @SerializedName("name")
    private String name;
    @SerializedName("label")
    private String label;
    @SerializedName("start_year")
    private String startYear;
    @SerializedName("end_year")
    private String endYear;
    @SerializedName("user_id")
    private String userId;

    protected SchoolYear(Parcel in) {
        super(in);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return label;
    }
}

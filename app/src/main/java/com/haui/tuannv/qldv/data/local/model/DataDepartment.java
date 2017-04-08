package com.haui.tuannv.qldv.data.local.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataDepartment {
    @SerializedName("departments")
    private List<Department> mDepartments;
    @SerializedName("school_years")
    private List<SchoolYear> mSchoolYears;
    @SerializedName("fees")
    private List<Fee> mFees;

    public List<Department> getDepartments() {
        return mDepartments;
    }

    public void setDepartments(List<Department> departments) {
        mDepartments = departments;
    }

    public List<SchoolYear> getSchoolYears() {
        return mSchoolYears;
    }

    public void setSchoolYears(List<SchoolYear> schoolYears) {
        mSchoolYears = schoolYears;
    }

    public List<Fee> getFees() {
        return mFees;
    }

    public void setFees(List<Fee> fees) {
        mFees = fees;
    }
}

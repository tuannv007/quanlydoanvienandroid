package com.haui.tuannv.qldv.data.local.model;

import android.databinding.BaseObservable;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Classes extends BaseObservable implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("code")
    private String code;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("department_id")
    private String departmentId;
    @SerializedName("school_year_id")
    private String schoolYearId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getSchoolYearId() {
        return schoolYearId;
    }

    public void setSchoolYearId(String schoolYearId) {
        this.schoolYearId = schoolYearId;
    }
}

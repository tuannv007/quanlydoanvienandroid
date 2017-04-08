package com.haui.tuannv.qldv.data.local.model;

import com.google.gson.annotations.SerializedName;

public class Department extends BaseObject {
    @SerializedName("name")
    private String mName;
    @SerializedName("code")
    private String mCode;
    @SerializedName("user_id")
    private String mUserId;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }
}

package com.haui.tuannv.qldv.data.local.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.google.gson.annotations.SerializedName;
import com.haui.tuannv.qldv.BR;
import java.io.Serializable;

public class Department extends BaseObservable implements Serializable {
    @SerializedName("name")
    private String mName;
    @SerializedName("code")
    private String mCode;
    @SerializedName("user_id")
    private String mUserId;
    @SerializedName("id")
    private String mId;

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

    @Bindable
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Override
    public String toString() {
        return mName;
    }
}

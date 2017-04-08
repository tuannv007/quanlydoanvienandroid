package com.haui.tuannv.qldv.data.local.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.haui.tuannv.qldv.BR;

public class DataUserLogin extends BaseObservable {
    @SerializedName("user")
    private User user;

    @Bindable
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        notifyPropertyChanged(BR.user);
    }
}

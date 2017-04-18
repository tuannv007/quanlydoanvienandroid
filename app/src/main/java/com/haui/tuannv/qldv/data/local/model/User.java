package com.haui.tuannv.qldv.data.local.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.google.gson.annotations.SerializedName;
import com.haui.tuannv.qldv.BR;
import java.io.Serializable;

/**
 * Created by tuanbg on 3/31/17.
 */
public class User extends BaseObservable implements Serializable {
    @SerializedName("name")
    private String mName;
    @SerializedName("account")
    private String mAccount;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("id")
    private int mId;
    @SerializedName("level")
    private int mLevel;

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    @Bindable
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
        notifyPropertyChanged(BR.id);
    }

    public User() {
    }

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getAccount() {
        return mAccount;
    }

    public void setAccount(String account) {
        this.mAccount = account;
        notifyPropertyChanged(BR.account);
    }

    @Bindable
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
        notifyPropertyChanged(BR.email);
    }
}

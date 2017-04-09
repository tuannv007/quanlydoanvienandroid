package com.haui.tuannv.qldv.data.local.model;

import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.haui.tuannv.qldv.BR;

/**
 * Created by tuanbg on 3/31/17.
 */
public class User extends BaseObject implements Parcelable {
    @SerializedName("name")
    private String mName;
    @SerializedName("account")
    private String mAccount;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("email")
    private String mEmail;

    protected User(Parcel in) {
        super(in);
        mName = in.readString();
        mAccount = in.readString();
        mPassword = in.readString();
        mEmail = in.readString();
    }

    public User() {
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mAccount);
        dest.writeString(mPassword);
        dest.writeString(mEmail);
    }
}

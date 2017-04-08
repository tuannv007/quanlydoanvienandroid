package com.haui.tuannv.qldv.data.remote.login;

import com.google.gson.annotations.SerializedName;

public class LoginBody {
    @SerializedName("account")
    private String mAccount;
    @SerializedName("password")
    private String mPassword;

    public LoginBody(String account, String password) {
        mAccount = account;
        mPassword = password;
    }
}

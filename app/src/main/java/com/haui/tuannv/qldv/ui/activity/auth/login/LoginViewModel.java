package com.haui.tuannv.qldv.ui.activity.auth.login;

/**
 * Created by tuanbg on 3/31/17.
 */
public class LoginViewModel {
    private LoginListener mListener;

    public LoginViewModel(LoginListener listener) {
        mListener = listener;
    }

    public void loginSystem() {
        mListener.loginSuccess();
    }

    public void forgotPassword() {
        mListener.forgotPassword();
    }
}

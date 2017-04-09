package com.haui.tuannv.qldv.ui.auth.login;

import com.haui.tuannv.qldv.data.local.model.User;

/**
 * Created by tuanbg on 3/31/17.
 */
public interface LoginListener {
    void loginSuccess(User user);

    void loginError(String message);

    void forgotPassword();

    void showDialog();

    void dismissDialog();

    void setChecked(boolean b);
}

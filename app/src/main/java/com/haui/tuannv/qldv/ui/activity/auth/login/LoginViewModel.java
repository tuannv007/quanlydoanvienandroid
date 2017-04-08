package com.haui.tuannv.qldv.ui.activity.auth.login;

import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.data.local.model.DataUserLogin;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.data.local.model.User;
import com.haui.tuannv.qldv.data.remote.login.LoginRepository;

/**
 * Created by tuanbg on 3/31/17.
 */
public class LoginViewModel {
    private LoginListener mListener;
    private LoginRepository mRepository;
    private User mUser = new User();

    public LoginViewModel(LoginListener listener, LoginRepository loginRepository) {
        mListener = listener;
        mRepository = loginRepository;
    }

    public void forgotPassword() {
        mListener.forgotPassword();
    }

    public void login() {
        mListener.showDialog();
        mRepository.login(mUser.getAccount(), mUser.getPassword(),
            new DataCallback<ResponseItem<DataUserLogin>>() {
                @Override
                public void onSuccess(ResponseItem<DataUserLogin> data) {
                    if (data.getData() == null) {
                        mListener.loginError(data.getMessage());
                        mListener.dismissDialog();
                        return;
                    }
                    mListener.loginSuccess(data.getData().getUser());
                    mListener.dismissDialog();
                }

                @Override
                public void onError(String msg) {
                    mListener.loginError(msg);
                    mListener.dismissDialog();
                }
            });
    }

    public User getUser() {
        return mUser;
    }
}

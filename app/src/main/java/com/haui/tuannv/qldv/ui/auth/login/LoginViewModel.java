package com.haui.tuannv.qldv.ui.auth.login;

import android.content.Context;
import android.content.SharedPreferences;
import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.data.local.model.DataUserLogin;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.data.local.model.User;
import com.haui.tuannv.qldv.data.remote.login.LoginRepository;

import static com.haui.tuannv.qldv.util.Utils.SharePreference.SHARE_PRE_NAME;
import static com.haui.tuannv.qldv.util.Utils.SharePreference.SHARE_PRE_PASSWORD;
import static com.haui.tuannv.qldv.util.Utils.SharePreference.SHARE_PRE_USERNAME;

/**
 * Created by tuanbg on 3/31/17.
 */
public class LoginViewModel {
    private SharedPreferences mSharedpreferences;
    private LoginListener mListener;
    private LoginRepository mRepository;
    private User mUser = new User();

    public LoginViewModel(Context context, LoginListener listener,
            LoginRepository loginRepository) {
        mListener = listener;
        mRepository = loginRepository;
        mSharedpreferences = context.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        if (mSharedpreferences != null) {
            mUser.setAccount(loadUsername());
            mUser.setPassword(loadPassword());
            mListener.setChecked(true);
        }
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
                        if (data == null) return;
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

    public void saveDataLogin(String username, String password) {
        SharedPreferences.Editor editor = mSharedpreferences.edit();
        editor.putString(SHARE_PRE_USERNAME, username);
        editor.putString(SHARE_PRE_PASSWORD, password);
        editor.apply();
    }

    public String loadUsername() {
        return mSharedpreferences.getString(SHARE_PRE_USERNAME, "");
    }

    public String loadPassword() {
        return mSharedpreferences.getString(SHARE_PRE_PASSWORD, "");
    }

    public User getUser() {
        return mUser;
    }
}

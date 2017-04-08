package com.haui.tuannv.qldv.data.remote.login;

import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.data.local.model.DataUserLogin;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;

/**
 * Created by tuanbg on 3/31/17.
 */
public class LoginRepository implements LoginDataSource {
    private static LoginRepository mRepository;
    private LoginRemoteDataSource mRemoteDataSource;

    private LoginRepository(LoginRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public static LoginRepository getInstance() {
        if (mRepository == null)
            mRepository = new LoginRepository(LoginRemoteDataSource.getInstance());
        return mRepository;
    }

    @Override
    public void login(String account, String password,
                      final DataCallback<ResponseItem<DataUserLogin>> callback) {
        if (callback == null) return;
        mRemoteDataSource.login(account, password, new DataCallback<ResponseItem<DataUserLogin>>() {
            @Override
            public void onSuccess(ResponseItem<DataUserLogin> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }
}

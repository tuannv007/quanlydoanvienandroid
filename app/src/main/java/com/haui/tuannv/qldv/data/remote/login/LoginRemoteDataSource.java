package com.haui.tuannv.qldv.data.remote.login;

import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.data.ServiceGenerator;
import com.haui.tuannv.qldv.data.local.model.DataUserLogin;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.network.Auth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tuanbg on 3/31/17.
 */
public class LoginRemoteDataSource implements LoginDataSource {
    private static LoginRemoteDataSource mRemoteDataSource;

    public static LoginRemoteDataSource getInstance() {
        if (mRemoteDataSource == null) mRemoteDataSource = new LoginRemoteDataSource();
        return mRemoteDataSource;
    }

    @Override
    public void login(String account, String password,
                      final DataCallback<ResponseItem<DataUserLogin>> callback) {
        ServiceGenerator.createService(Auth.loginService.class)
            .login(account, password)
            .enqueue(new Callback<ResponseItem<DataUserLogin>>() {
                @Override
                public void onResponse(Call<ResponseItem<DataUserLogin>> call,
                                       Response<ResponseItem<DataUserLogin>> response) {
                    callback.onSuccess(response.body());
                }

                @Override
                public void onFailure(Call<ResponseItem<DataUserLogin>> call, Throwable t) {
                    callback.onError(t.getMessage());
                }
            });
    }
}

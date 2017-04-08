package com.haui.tuannv.qldv.network;

import com.haui.tuannv.qldv.data.local.model.DataUserLogin;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by tuanbg on 4/7/17.
 */
public class Auth {
    public interface loginService {
        @POST("/api/auth/login")
        @FormUrlEncoded
        Call<ResponseItem<DataUserLogin>> login(@Field("account") String account,
                                                @Field("password") String password);
    }
}

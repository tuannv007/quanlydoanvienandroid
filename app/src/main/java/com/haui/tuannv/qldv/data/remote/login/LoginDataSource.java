package com.haui.tuannv.qldv.data.remote.login;

import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.data.local.model.DataUserLogin;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;

/**
 * Created by tuanbg on 3/31/17.
 */
public interface LoginDataSource {
    void login(String username, String password, DataCallback<ResponseItem<DataUserLogin>> callback);
}

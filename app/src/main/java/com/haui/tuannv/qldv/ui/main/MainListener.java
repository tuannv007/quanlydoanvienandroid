package com.haui.tuannv.qldv.ui.main;

import com.haui.tuannv.qldv.data.local.model.DataDepartment;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;

/**
 * Created by tuanbg on 4/19/17.
 */

public interface MainListener {

    void onSuccess(ResponseItem<DataDepartment> data);

    void onError(String msg);
}

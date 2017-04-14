package com.haui.tuannv.qldv.ui.monney.otherspend;

import com.haui.tuannv.qldv.data.local.model.ResponseItem;

/**
 * Created by tuanbg on 4/14/17.
 */

public interface OtherSpendListener {
    void getDataSuccess(ResponseItem data);

    void getDataError(String msg);
}

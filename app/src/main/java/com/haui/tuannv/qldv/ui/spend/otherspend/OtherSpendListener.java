package com.haui.tuannv.qldv.ui.spend.otherspend;

import com.haui.tuannv.qldv.data.local.model.ResponseItem;

/**
 * Created by tuanbg on 4/15/17.
 */

public interface OtherSpendListener {
    void onSuccess(ResponseItem data);

    void onError();
}

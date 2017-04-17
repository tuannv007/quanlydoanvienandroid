package com.haui.tuannv.qldv.ui.revenue.newrevenue;

import com.haui.tuannv.qldv.data.local.model.ResponseItem;

/**
 * Created by tuanbg on 4/15/17.
 */

public interface NewRevenueListener {
    void onSuccess(ResponseItem data);

    void onError();
}

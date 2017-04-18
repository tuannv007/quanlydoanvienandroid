package com.haui.tuannv.qldv.ui.spend;

import com.haui.tuannv.qldv.data.local.model.DataRevenue;

/**
 * Created by tuanbg on 4/14/17.
 */

public interface SpendListener {
    void getDataSuccess(DataRevenue data);

    void getDataError(String msg);
}

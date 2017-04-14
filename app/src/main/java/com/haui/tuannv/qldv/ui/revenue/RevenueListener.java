package com.haui.tuannv.qldv.ui.revenue;

import com.haui.tuannv.qldv.data.local.model.DataRevenue;

/**
 * Created by tuanbg on 4/14/17.
 */

public interface RevenueListener {
    void getDataSuccess(DataRevenue data);

    void getDataError(String msg);
}

package com.haui.tuannv.qldv.ui.statistical;

import com.haui.tuannv.qldv.data.local.model.DataRevenue;

/**
 * Created by tuanbg on 4/14/17.
 */

public interface StatisticalListener {
    void getDataSuccess(DataRevenue data);

    void getDataError(String msg);
}

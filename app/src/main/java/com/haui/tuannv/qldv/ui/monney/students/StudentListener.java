package com.haui.tuannv.qldv.ui.monney.students;

import com.haui.tuannv.qldv.data.local.model.DataStudents;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;

/**
 * Created by tuanbg on 4/18/17.
 */

public interface StudentListener {
    void onSuccess(ResponseItem<DataStudents> data);

    void onError(String msg);

    void updateSuccess();

    void updateError(String msg);
}

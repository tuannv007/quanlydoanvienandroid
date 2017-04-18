package com.haui.tuannv.qldv.ui.monney.classes;

import com.haui.tuannv.qldv.data.local.model.Classes;
import com.haui.tuannv.qldv.data.local.model.Fee;
import java.util.List;

/**
 * Created by tuanbg on 4/11/17.
 */

public interface ClassesListener {

    void getDataSuccess(List<Classes> classes);

    void getDataError(String msg);

    void getStudent(Classes classes, Fee fee);
}

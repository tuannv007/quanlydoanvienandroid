package com.haui.tuannv.qldv.ui;

import android.content.Context;
import com.haui.tuannv.qldv.util.TProgressDialog;

/**
 * Created by tuanbg on 4/13/17.
 */

public class BaseViewModel {
    private TProgressDialog mDialog;

    public void showDialog(Context context) {
        if (mDialog == null) mDialog = new TProgressDialog(context);
        mDialog.show();
    }

    public void hideDialog() {
        if (mDialog != null && mDialog.isShowing()) mDialog.dismiss();
    }
}

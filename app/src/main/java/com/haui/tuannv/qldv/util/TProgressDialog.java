package com.haui.tuannv.qldv.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ProgressBar;

/**
 * Created by tuanbg on 3/1/17.
 */
public class TProgressDialog extends ProgressDialog {
    public TProgressDialog(Context context) {
        super(context);
        setCancelable(false);
    }

    @Override
    public void show() {
        super.show();
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        setContentView(new ProgressBar(getContext()));
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}

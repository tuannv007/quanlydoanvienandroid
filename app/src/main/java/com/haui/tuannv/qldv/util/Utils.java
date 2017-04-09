package com.haui.tuannv.qldv.util;

import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.haui.tuannv.qldv.R;

/**
 * Created by tuanbg on 3/27/17.
 */
public class Utils {
    private static Snackbar mSnackBar;

    public static void createSnackBar(boolean isConnect, RelativeLayout layout) {
        if (!isConnect) {
            if (mSnackBar == null) {
                mSnackBar =
                        Snackbar.make(layout, R.string.msg_no_internet, Snackbar.LENGTH_INDEFINITE)
                                .setAction("Ok", null);
                View view = mSnackBar.getView();
                TextView textSnackbar =
                        (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                textSnackbar.setGravity(Gravity.CENTER);
                view.setBackgroundColor(
                        ContextCompat.getColor(view.getContext(), R.color.color_teal_500));
            }
            mSnackBar.show();
        } else {
            if (mSnackBar != null && mSnackBar.isShown()) {
                mSnackBar.dismiss();
            }
        }
    }

    public class BaseNetwork {
        public static final String BASE_URL = "http://tuannv007.xyz/";
    }

    public class SharePreference {
        public static final String SHARE_PRE_NAME = "SHARE_PRE_NAME";
        public static final String SHARE_PRE_USERNAME = "PREF_USERNAME";
        public static final String SHARE_PRE_PASSWORD = "PREF_PASSWORD";
    }
}

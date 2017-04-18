package com.haui.tuannv.qldv.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by tuanbg on 3/31/17.
 */
public class ActivityUtil {
    public static void addFragmentToActivity(AppCompatActivity activity, int id,
            Fragment fragment) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment)
                .addToBackStack(null)
                .commit();
    }
    public static void addFragmentToActivityNoBST(AppCompatActivity activity, int id,
            Fragment fragment) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment)
                .commit();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}

package com.haui.tuannv.qldv.util;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tuanbg on 3/31/17.
 */
public class ActivityUtil {
    public static void addFragmentToActivity(AppCompatActivity activity, int id, Fragment
        fragment) {
        activity.getSupportFragmentManager()
            .beginTransaction()
            .replace(id, fragment)
            .addToBackStack(null)
            .commit();
    }
}

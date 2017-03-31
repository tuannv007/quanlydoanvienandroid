package com.haui.tuannv.qldv.ui.activity.auth.forgotpassword;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.databinding.DialogFrgmentForgotPasswordBinding;

/**
 * Created by tuanbg on 3/31/17.
 */
public class ForgotPasswordFragment extends DialogFragment {
    private DialogFrgmentForgotPasswordBinding mBinding;

    public static ForgotPasswordFragment newInstance() {
        Bundle args = new Bundle();
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mBinding =
            DataBindingUtil
                .inflate(inflater, R.layout.dialog_frgment_forgot_password, container, false);
        mBinding.setDialog(this);
        return mBinding.getRoot();
    }

    public void dismissDialog() {
        if (getDialog() != null && getDialog().isShowing()) dismiss();
    }
}

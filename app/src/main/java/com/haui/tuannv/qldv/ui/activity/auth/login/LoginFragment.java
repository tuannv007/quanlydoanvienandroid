package com.haui.tuannv.qldv.ui.activity.auth.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.databinding.FragmentLoginBinding;
import com.haui.tuannv.qldv.ui.activity.auth.forgotpassword.ForgotPasswordFragment;
import com.haui.tuannv.qldv.ui.activity.main.MainActivity;
import com.haui.tuannv.qldv.util.Constant;

/**
 * Created by tuanbg on 3/24/17.
 */
public class LoginFragment extends Fragment implements LoginListener {
    private LoginViewModel mViewModel;
    private FragmentLoginBinding mBinding;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        mViewModel = new LoginViewModel(this);
        mBinding.setViewmodel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void loginSuccess() {
        startActivity(MainActivity.getDataIntent(getActivity()));
    }

    @Override
    public void loginError() {
    }

    @Override
    public void forgotPassword() {
        showDialogForgotPass();
    }

    public void showDialogForgotPass() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        DialogFragment newFragment = ForgotPasswordFragment.newInstance();
        newFragment.show(ft, Constant.TYPE_DIALOG_FRAGMENT);
    }
}

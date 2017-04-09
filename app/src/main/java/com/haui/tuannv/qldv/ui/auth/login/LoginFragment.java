package com.haui.tuannv.qldv.ui.auth.login;

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
import com.haui.tuannv.qldv.data.local.model.User;
import com.haui.tuannv.qldv.data.remote.login.LoginRepository;
import com.haui.tuannv.qldv.databinding.FragmentLoginBinding;
import com.haui.tuannv.qldv.ui.auth.forgotpassword.ForgotPasswordFragment;
import com.haui.tuannv.qldv.ui.main.MainActivity;
import com.haui.tuannv.qldv.util.ActivityUtil;
import com.haui.tuannv.qldv.util.Constant;
import com.haui.tuannv.qldv.util.TProgressDialog;

/**
 * Created by tuanbg on 3/24/17.
 */
public class LoginFragment extends Fragment implements LoginListener {
    private LoginViewModel mViewModel;
    private FragmentLoginBinding mBinding;
    private TProgressDialog mDialog;

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

        mViewModel = new LoginViewModel(getActivity(), this, LoginRepository.getInstance());
        mBinding.setViewmodel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void loginSuccess(User user) {
        startActivity(MainActivity.getDataIntent(getActivity(), user));
    }

    @Override
    public void loginError(String message) {
        if (message == null) return;
        ActivityUtil.showToast(getActivity(), message);
    }

    @Override
    public void forgotPassword() {
        showDialogForgotPass();
    }

    @Override
    public void showDialog() {
        if (mDialog == null) mDialog = new TProgressDialog(getActivity());
        mDialog.show();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBinding.checkkedTextview.isChecked()) {
            mViewModel.saveDataLogin(mBinding.editUsername.getText().toString(),
                    mBinding.editPassword.getText().toString());
        } else {
            mViewModel.saveDataLogin("", "");
        }
    }

    @Override
    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) mDialog.dismiss();
    }

    @Override
    public void setChecked(boolean b) {
        mBinding.checkkedTextview.setChecked(b);
    }

    public void showDialogForgotPass() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        DialogFragment newFragment = ForgotPasswordFragment.newInstance();
        newFragment.show(ft, Constant.TYPE_DIALOG_FRAGMENT);
    }
}

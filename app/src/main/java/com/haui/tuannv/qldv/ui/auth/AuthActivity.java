package com.haui.tuannv.qldv.ui.auth;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.databinding.ActivityAuthBinding;
import com.haui.tuannv.qldv.ui.auth.login.LoginFragment;
import com.haui.tuannv.qldv.util.ActivityUtil;

/**
 * Created by tuanbg on 3/31/17.
 */
public class AuthActivity extends AppCompatActivity {
    private ActivityAuthBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
        if (getSupportFragmentManager().findFragmentById(R.id.frame_layout) == null) {
            ActivityUtil
                .addFragmentToActivity(this, R.id.frame_layout, LoginFragment.newInstance());
        }
    }
}

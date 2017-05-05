package com.haui.tuannv.qldv.ui.introduce;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.databinding.ActivtyIntroduceBinding;

/**
 * Created by tuanbg on 5/8/17.
 */

public class IntroduceActivity extends AppCompatActivity {
    private ActivtyIntroduceBinding mBinding;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activty_introduce);
        mToolbar = mBinding.toolbar;
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setTitle(getString(R.string.title_introduce));
    }
}

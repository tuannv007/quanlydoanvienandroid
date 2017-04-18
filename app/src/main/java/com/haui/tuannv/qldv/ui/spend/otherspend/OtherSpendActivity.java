package com.haui.tuannv.qldv.ui.spend.otherspend;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.data.local.model.User;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.databinding.ActivityAddRevenueBinding;
import com.haui.tuannv.qldv.util.ActivityUtil;

import static com.haui.tuannv.qldv.util.Constant.BUNDLE_USER;

/**
 * Created by tuanbg on 4/15/17.
 */

public class OtherSpendActivity extends AppCompatActivity implements OtherSpendListener {
    private ActivityAddRevenueBinding mBinding;
    private OtherSpendViewModel mViewModel;
    private Toolbar mToolbar;
    private int mIdUser;

    public static Intent getNewRenueIntent(Context context, User user) {
        Intent intent = new Intent(context, OtherSpendActivity.class);
        intent.putExtra(BUNDLE_USER, user);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_revenue);
        getDataFromIntent();
        mViewModel =
                new OtherSpendViewModel(this, this, DepartmentRepository.getInstance(), mIdUser);
        mBinding.setViewmodel(mViewModel);
        initToolBar();
    }

    public void getDataFromIntent() {
        if (getIntent().getExtras() == null) return;
        User user = (User) getIntent().getExtras().getSerializable(BUNDLE_USER);
        if (user == null) return;
        mIdUser = user.getId();
    }

    @Override
    public void onSuccess(ResponseItem data) {
        ActivityUtil.showToast(getApplicationContext(), data.getMessage());
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onError() {
        ActivityUtil.showToast(getApplicationContext(), getString(R.string.msg_no_connect));
    }

    public void initToolBar() {
        mToolbar = mBinding.toolbar.toolbar;
        if (mToolbar == null) return;
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.new_renvenue));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

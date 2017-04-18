package com.haui.tuannv.qldv.ui.monney.otherrevenue;

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
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.databinding.ActivityOtherSpendBinding;
import com.haui.tuannv.qldv.util.ActivityUtil;

/**
 * Created by tuanbg on 4/14/17.
 */

public class OtherRevenueActivity extends AppCompatActivity implements OtherdListener {
    private ActivityOtherSpendBinding mBinding;
    private OtherViewModel mViewModel;
    private Toolbar mToolbar;

    public static Intent getSpendIntent(Context context) {
        return new Intent(context, OtherRevenueActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_other_spend);
        mViewModel = new OtherViewModel(this, this, DepartmentRepository.getInstance());
        mBinding.setViewmodel(mViewModel);
        initToolBar();
    }

    public void initToolBar() {
        mToolbar = mBinding.toolbar.toolbar;
        if (mToolbar == null) return;
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.title_spend_other));
    }

    @Override
    public void getDataSuccess(ResponseItem data) {
        ActivityUtil.showToast(getApplicationContext(), data.getMessage());
        finish();
    }

    @Override
    public void getDataError(String msg) {
        ActivityUtil.showToast(getApplicationContext(), getString(R.string.msg_no_connect));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

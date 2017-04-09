package com.haui.tuannv.qldv.ui.monney;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.data.local.model.Department;
import com.haui.tuannv.qldv.data.local.model.Fee;
import com.haui.tuannv.qldv.data.local.model.SchoolYear;
import com.haui.tuannv.qldv.databinding.ActivityMoneyBinding;

/**
 * Created by tuanbg on 4/11/17.
 */

public class MoneyActivity extends AppCompatActivity {
    private ActivityMoneyBinding mBinding;
    private Toolbar mToolbar;

    public static Intent getProfileIntent(Context context, Department department,
            SchoolYear schoolYear, Fee fee) {
        Intent intent = new Intent(context, MoneyActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("department", department);
        bundle.putParcelable("shoolyear", schoolYear);
        bundle.putParcelable("fee", fee);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_money);
        mBinding.getRoot();
        initToolBar();
    }

    public void initToolBar() {
        mToolbar = mBinding.toolbar.toolbar;
        if (mToolbar == null) return;
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.title_manager_classes));
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

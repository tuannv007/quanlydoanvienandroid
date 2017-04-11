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
import com.haui.tuannv.qldv.ui.monney.classes.ClassesFragment;
import com.haui.tuannv.qldv.util.ActivityUtil;

import static com.haui.tuannv.qldv.util.Constant.ConstBundle.BUNDLE_DEPARTMENT;
import static com.haui.tuannv.qldv.util.Constant.ConstBundle.BUNDLE_FEE;
import static com.haui.tuannv.qldv.util.Constant.ConstBundle.BUNDLE_SCHOOLYEAR;

/**
 * Created by tuanbg on 4/11/17.
 */

public class MoneyActivity extends AppCompatActivity {
    private ActivityMoneyBinding mBinding;
    private Toolbar mToolbar;
    private Department mDepartment;
    private SchoolYear mSchoolYear;
    private Fee mFee;

    public static Intent getProfileIntent(Context context, Department department,
            SchoolYear schoolYear, Fee fee) {
        Intent intent = new Intent(context, MoneyActivity.class);
        intent.putExtra(BUNDLE_DEPARTMENT, department);
        intent.putExtra(BUNDLE_SCHOOLYEAR, schoolYear);
        intent.putExtra(BUNDLE_FEE, fee);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_money);
        initToolBar();
        getDataFromIntent();
        ActivityUtil.addFragmentToActivity(this, R.id.frame_layout,
                ClassesFragment.newInstance(mDepartment, mSchoolYear, mFee));
    }

    public void initToolBar() {
        mToolbar = mBinding.toolbar.toolbar;
        if (mToolbar == null) return;
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.title_manager_classes));
    }

    public void getDataFromIntent() {
        if (getIntent() == null) return;
        Bundle bundle = getIntent().getExtras();
        mDepartment = (Department) bundle.getSerializable(BUNDLE_DEPARTMENT);
        mSchoolYear = (SchoolYear) getIntent().getSerializableExtra(BUNDLE_SCHOOLYEAR);
        mFee = (Fee) getIntent().getSerializableExtra(BUNDLE_FEE);
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

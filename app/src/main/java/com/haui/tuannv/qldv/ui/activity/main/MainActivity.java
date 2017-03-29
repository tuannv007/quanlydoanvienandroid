package com.haui.tuannv.qldv.ui.activity.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.broadcast.NetworkReceiver;
import com.haui.tuannv.qldv.databinding.ActivityMainBinding;
import com.haui.tuannv.qldv.ui.fragment.RevenueFragment;
import com.haui.tuannv.qldv.ui.fragment.SpendFragment;
import com.haui.tuannv.qldv.ui.fragment.StatisticalFragment;
import com.haui.tuannv.qldv.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements NetworkReceiver.NetworkReceiverListener {
    private RelativeLayout mLayout;
    private ActivityMainBinding mBinding;
    private Toolbar mToolbar;
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setActivity(this);
        mLayout = mBinding.mainActivity;
        NetworkReceiver.setNetworkReceiver(this);
        initToolBar();
        init();
    }

    public void init() {
        List<Fragment> listFragments = new ArrayList<>();
        listFragments.add(RevenueFragment.newInstance());
        listFragments.add(SpendFragment.newInstance());
        listFragments.add(StatisticalFragment.newInstance());
        String[] titles = getResources().getStringArray(R.array.array_title_fragment);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), listFragments, titles);
    }

    @Override
    public void onNetworkConnectChange(boolean isConnect) {
        Utils.createSnackBar(isConnect, mLayout);
    }

    public void initToolBar() {
        mToolbar = mBinding.toolbarLayout.toolbar;
        if (mToolbar == null) return;
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public ViewPagerAdapter getAdapter() {
        return mAdapter;
    }
}

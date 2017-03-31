package com.haui.tuannv.qldv.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.broadcast.NetworkReceiver;
import com.haui.tuannv.qldv.databinding.ActivityMainBinding;
import com.haui.tuannv.qldv.ui.fragment.revenue.RevenueFragment;
import com.haui.tuannv.qldv.ui.fragment.spend.SpendFragment;
import com.haui.tuannv.qldv.ui.fragment.statistical.StatisticalFragment;
import com.haui.tuannv.qldv.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements NetworkReceiver.NetworkReceiverListener {
    private RelativeLayout mLayout;
    private ActivityMainBinding mBinding;
    private Toolbar mToolbar;
    private ViewPagerAdapter mAdapter;
    private DrawerLayout mDrawerLayout;

    public static Intent getDataIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

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
        mToolbar = mBinding.toolbar;
        mDrawerLayout = mBinding.drawerLayout;
        if (mToolbar == null) return;
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public ViewPagerAdapter getAdapter() {
        return mAdapter;
    }
}

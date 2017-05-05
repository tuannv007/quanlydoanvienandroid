package com.haui.tuannv.qldv.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.broadcast.NetworkReceiver;
import com.haui.tuannv.qldv.data.local.model.User;
import com.haui.tuannv.qldv.databinding.ActivityMainBinding;
import com.haui.tuannv.qldv.ui.introduce.IntroduceActivity;
import com.haui.tuannv.qldv.ui.revenue.RevenueFragment;
import com.haui.tuannv.qldv.ui.spend.SpendFragment;
import com.haui.tuannv.qldv.ui.statistical.StatisticalFragment;
import com.haui.tuannv.qldv.util.Utils;
import java.util.ArrayList;
import java.util.List;

import static com.haui.tuannv.qldv.util.Constant.BUNDLE_USER;
import static com.haui.tuannv.qldv.util.Constant.SharePreference.SHARE_PRE_NAME;

public class MainActivity extends AppCompatActivity
        implements NetworkReceiver.NetworkReceiverListener,
        NavigationView.OnNavigationItemSelectedListener {
    private RelativeLayout mLayout;
    private ActivityMainBinding mBinding;
    private Toolbar mToolbar;
    private ViewPagerAdapter mAdapter;
    private DrawerLayout mDrawerLayout;
    private User mUser;
    private NavigationView mNavigationView;

    public static Intent getDataIntent(Context context, User user) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(BUNDLE_USER, user);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getDataFromIntent();
        mBinding.setActivity(this);
        mLayout = mBinding.mainActivity;
        mNavigationView = mBinding.navigationView;
        NetworkReceiver.setNetworkReceiver(this);
        initToolBar();
        init();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void getDataFromIntent() {
        if (getIntent().getExtras() == null) return;
        mUser = (User) getIntent().getExtras().getSerializable(BUNDLE_USER);
    }

    public void init() {
        List<Fragment> listFragments = new ArrayList<>();
        listFragments.add(RevenueFragment.newInstance(mUser));
        listFragments.add(SpendFragment.newInstance(mUser));
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

    public User getUser() {
        return mUser;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.introduce:
                startActivity(new Intent(MainActivity.this, IntroduceActivity.class));
                break;
            case R.id.logout:
                SharedPreferences preferences =
                        getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                finish();
                break;
        }
        return false;
    }
}

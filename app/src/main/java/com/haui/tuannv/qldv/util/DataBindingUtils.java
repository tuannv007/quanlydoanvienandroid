package com.haui.tuannv.qldv.util;

import android.databinding.BindingAdapter;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import com.haui.tuannv.qldv.data.local.model.Student;
import com.haui.tuannv.qldv.data.local.model.User;
import com.haui.tuannv.qldv.databinding.HeaderBinding;
import com.haui.tuannv.qldv.ui.monney.classes.ClassesFragment;
import com.haui.tuannv.qldv.ui.monney.students.StudentFragment;

/**
 * Created by tuanbg
 * <></>
 */
public class DataBindingUtils {
    @BindingAdapter({ "bind:adapterViewPager" })
    public static void setViewPagerAdapter(ViewPager view, FragmentPagerAdapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView view,
            LayoutManageUtil.LayoutManagerFactory layout) {
        view.setLayoutManager(layout.create(view));
    }

    @BindingAdapter({ "bind:bindAdapter" })
    public static void bindAdapterRecycler(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setNestedScrollingEnabled(false);
        view.setAdapter(adapter);
    }

    @BindingAdapter({ "bind:setErrorEditText" })
    public static void setError(final EditText editText, final String msg) {
        if (TextUtils.isEmpty(editText.getText().toString())) editText.setError(msg);
    }

    @BindingAdapter({ "bind:viewPager" })
    public static void setUpWithViewPager(TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @BindingAdapter("bind:headerNavigation")
    public static void setHeaderNavigation(NavigationView view, User user) {
        HeaderBinding binding = HeaderBinding.inflate(LayoutInflater.from(view.getContext()));
        binding.setUser(user);
        binding.executePendingBindings();
        view.addHeaderView(binding.getRoot());
    }

    @BindingAdapter({ "bind:classesFragment" })
    public static void refreshLayout(SwipeRefreshLayout view, final ClassesFragment classes) {
        view.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                classes.loadData(classes.getDepartmentId(), classes.getSchoolId());
            }
        });
    }

    @BindingAdapter({ "bind:refreshSwipeLayout" })
    public static void setRefreshrSwipeLayout(SwipeRefreshLayout view, boolean isReFresh) {
        view.setRefreshing(isReFresh);
    }

    @BindingAdapter("bind:checkedCheckBox")
    public static void checked(CheckBox view, final Student student) {
        if (student.getFeePaid() == 1) {
            view.setChecked(true);
        } else {
            view.setChecked(false);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    student.setFeePaid(1);
                } else {
                    student.setFeePaid(0);
                }
            }
        });
    }

    @BindingAdapter({ "bind:studentFragment" })
    public static void refreshLayoutStudent(SwipeRefreshLayout view,
            final StudentFragment student) {
        view.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                student.loadData();
            }
        });
    }
}

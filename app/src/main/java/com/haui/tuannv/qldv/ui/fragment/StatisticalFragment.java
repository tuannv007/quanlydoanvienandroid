package com.haui.tuannv.qldv.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.databinding.FragmentStatisticalBinding;

/**
 * Created by tuanbg on 3/29/17.
 */
public class StatisticalFragment extends Fragment {
    private FragmentStatisticalBinding mBinding;

    public static StatisticalFragment newInstance() {
        Bundle args = new Bundle();
        StatisticalFragment fragment = new StatisticalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_statistical, container, false);
        return mBinding.getRoot();
    }
}

package com.haui.tuannv.qldv.ui.fragment.spend;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.databinding.FragmentSpendBinding;

/**
 * Created by tuanbg on 3/29/17.
 */
public class SpendFragment extends Fragment {
    private FragmentSpendBinding mBinding;

    public static SpendFragment newInstance() {
        Bundle args = new Bundle();
        SpendFragment fragment = new SpendFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_spend, container, false);
        return mBinding.getRoot();
    }
}

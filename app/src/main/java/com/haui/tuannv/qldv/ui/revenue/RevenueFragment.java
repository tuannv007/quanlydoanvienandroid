package com.haui.tuannv.qldv.ui.revenue;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.data.local.model.DataRevenue;
import com.haui.tuannv.qldv.data.local.model.Payment;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.databinding.FragmentRevenueBinding;
import com.haui.tuannv.qldv.util.ActivityUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanbg on 3/29/17.
 */
public class RevenueFragment extends Fragment implements RevenueListener {
    private FragmentRevenueBinding mBinding;
    private ObservableField<RevenueAdapter> mAdapter = new ObservableField<>();
    private RevenueViewModel mViewModel;
    private List<Payment> mPaymentList = new ArrayList<>();

    public static RevenueFragment newInstance() {
        Bundle args = new Bundle();
        RevenueFragment fragment = new RevenueFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_revenue, container, false);
        mViewModel = new RevenueViewModel(getActivity(), this, DepartmentRepository.getInstance());
        mBinding.setFragment(this);
        return mBinding.getRoot();
    }

    @Override
    public void getDataSuccess(DataRevenue data) {
        mPaymentList.clear();
        mPaymentList.addAll(data.getPayments());
        mAdapter.set(new RevenueAdapter(mPaymentList));
    }

    @Override
    public void getDataError(String msg) {
        ActivityUtil.showToast(getActivity(), msg);
    }

    public void getData() {
        if (mBinding.editYear.getText().toString().isEmpty()) {
            ActivityUtil.showToast(getActivity(), getString(R.string.msg_requai_year));
            return;
        }
        mViewModel.getData(Integer.parseInt(mBinding.editYear.getText().toString()));
    }

    public ObservableField<RevenueAdapter> getAdapter() {
        return mAdapter;
    }
}

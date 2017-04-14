package com.haui.tuannv.qldv.ui.spend;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.data.local.model.DataDepartment;
import com.haui.tuannv.qldv.data.local.model.Department;
import com.haui.tuannv.qldv.data.local.model.Fee;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.data.local.model.SchoolYear;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.databinding.FragmentSpendBinding;
import com.haui.tuannv.qldv.ui.monney.MoneyActivity;
import com.haui.tuannv.qldv.ui.monney.otherspend.OtherSpendActivity;
import com.haui.tuannv.qldv.util.ActivityUtil;
import com.haui.tuannv.qldv.util.TProgressDialog;

/**
 * Created by tuanbg on 3/29/17.
 */
public class SpendFragment extends Fragment implements SpendListener {
    private FragmentSpendBinding mBinding;
    private SpendModelView mViewModel;
    private TProgressDialog mDialog;
    private ObservableField<Department> mDepartment = new ObservableField<>();
    private ObservableField<SchoolYear> mSchoolYear = new ObservableField<>();
    private ObservableField<Fee> mFee = new ObservableField<>();

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
        mViewModel = new SpendModelView(getActivity(), this, DepartmentRepository.getInstance());
        mBinding.setFragment(this);
        mBinding.setViewmodel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getDepartment();
        mBinding.buttonFillter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDepartment.set((Department) mBinding.spinDepartment.getSelectedItem());
                mSchoolYear.set((SchoolYear) mBinding.spinSchoolYear.getSelectedItem());
                mFee.set((Fee) mBinding.spinFee.getSelectedItem());
                mViewModel.getClasse(mDepartment.get(), mSchoolYear.get(), mFee.get());
            }
        });
    }

    @Override
    public void onSuccess(ResponseItem<DataDepartment> data) {
        if (data == null) return;
        mBinding.spinDepartment.setAdapter(mViewModel.getDepartment(data));
        mBinding.spinSchoolYear.setAdapter(mViewModel.getSchoolYear(data));
        mBinding.spinFee.setAdapter(mViewModel.getFees(data));
    }

    @Override
    public void onError(String msg) {
        ActivityUtil.showToast(getActivity(), msg);
    }

    @Override
    public void showDialog() {
        if (mDialog == null) mDialog = new TProgressDialog(getActivity());
        mDialog.show();
    }

    @Override
    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) mDialog.dismiss();
    }

    @Override
    public void getClasseFromDepartment(Department department, SchoolYear schoolYear, Fee fee) {
        startActivity(MoneyActivity.getProfileIntent(getActivity(), department, schoolYear, fee));
    }

    @Override
    public void openOtherSpend() {
        startActivity(OtherSpendActivity.getSpendIntent(getActivity()));
    }
}

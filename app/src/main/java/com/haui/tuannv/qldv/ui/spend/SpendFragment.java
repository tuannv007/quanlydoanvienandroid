package com.haui.tuannv.qldv.ui.spend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.haui.tuannv.qldv.data.local.model.User;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.databinding.FragmentRevenueBinding;
import com.haui.tuannv.qldv.ui.spend.otherspend.OtherSpendActivity;
import com.haui.tuannv.qldv.util.ActivityUtil;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.haui.tuannv.qldv.util.Constant.BUNDLE_USER;
import static com.haui.tuannv.qldv.util.Constant.ConstBundle.BUNDLE_KEY_ID;
import static com.haui.tuannv.qldv.util.Constant.SharePreference.SHARE_PRE_NAME;

/**
 * Created by tuanbg on 3/29/17.
 * quan ly chi tieu
 */
public class SpendFragment extends Fragment implements SpendListener {
    private FragmentRevenueBinding mBinding;
    private ObservableField<SpendAdapter> mAdapter = new ObservableField<>();
    private SpendViewModel mViewModel;
    private List<Payment> mPaymentList = new ArrayList<>();
    private User mUser = new User();

    public static SpendFragment newInstance(User user) {
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_USER, user);
        SpendFragment fragment = new SpendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_revenue, container, false);
        getDataFromIntent();
        mViewModel = new SpendViewModel(getActivity(), this, DepartmentRepository.getInstance());
        mBinding.setFragment(this);
        return mBinding.getRoot();
    }

    @Override
    public void getDataSuccess(DataRevenue data) {
        mPaymentList.clear();
        mPaymentList.addAll(data.getPayments());
        mAdapter.set(new SpendAdapter(mPaymentList));
    }

    public void getDataFromIntent() {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        mUser = (User) bundle.getSerializable(BUNDLE_USER);
    }

    @Override
    public void getDataError(String msg) {
        ActivityUtil.showToast(getActivity(), getString(R.string.msg_no_connect));
    }

    public void getData() {
        if (mBinding.editYear.getText().toString().isEmpty()) {
            ActivityUtil.showToast(getActivity(), getString(R.string.msg_requai_year));
            return;
        }
        mViewModel.getData(Integer.parseInt(mBinding.editYear.getText().toString()),
                getIdDepartment());
    }

    public ObservableField<SpendAdapter> getAdapter() {
        return mAdapter;
    }

    public void addRevenue() {
        startActivityForResult(OtherSpendActivity.getNewRenueIntent(getActivity(), mUser), 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            mBinding.editYear.setText(String.valueOf(mViewModel.getYear()));
            mViewModel.getData(mViewModel.getYear(), getIdDepartment());
        }
    }

    public String getIdDepartment() {
        SharedPreferences sharedPreferences =
                getActivity().getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(BUNDLE_KEY_ID, "");
        }
        return null;
    }
}


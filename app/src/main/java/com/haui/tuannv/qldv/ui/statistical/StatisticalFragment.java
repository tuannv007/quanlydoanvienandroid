package com.haui.tuannv.qldv.ui.statistical;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.data.local.model.DataRevenue;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.databinding.FragmentStatisticalBinding;
import com.haui.tuannv.qldv.util.ActivityUtil;

import static com.haui.tuannv.qldv.util.Constant.ConstBundle.BUNDLE_KEY_ID;
import static com.haui.tuannv.qldv.util.Constant.SharePreference.SHARE_PRE_NAME;

/**
 * Created by tuanbg on 3/29/17.
 */
public class StatisticalFragment extends Fragment implements StatisticalListener {
    private FragmentStatisticalBinding mBinding;
    private StatisticalViewModel mViewModel;
    private ObservableField<String> mInputTotal = new ObservableField<>();
    private ObservableField<String> mOutputTotal = new ObservableField<>();
    private ObservableInt mResult = new ObservableInt();

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
        mViewModel =
                new StatisticalViewModel(getActivity(), this, DepartmentRepository.getInstance());
        mBinding.setFragment(this);
        return mBinding.getRoot();
    }

    @Override
    public void getDataSuccess(DataRevenue data) {
        mInputTotal.set(data.getInputTotal());
        mOutputTotal.set(data.getOutputTotal());
        mResult.set(data.getRestTotal());
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

    public ObservableField<String> getInputTotal() {
        return mInputTotal;
    }

    public ObservableField<String> getOutputTotal() {
        return mOutputTotal;
    }

    public ObservableInt getResult() {
        return mResult;
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

package com.haui.tuannv.qldv.ui.revenue;

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
import android.widget.AdapterView;
import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.data.local.model.DataDepartment;
import com.haui.tuannv.qldv.data.local.model.Department;
import com.haui.tuannv.qldv.data.local.model.Fee;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.data.local.model.SchoolYear;
import com.haui.tuannv.qldv.data.local.model.User;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.databinding.FragmentSpendBinding;
import com.haui.tuannv.qldv.ui.monney.MoneyActivity;
import com.haui.tuannv.qldv.ui.monney.otherrevenue.OtherRevenueActivity;
import com.haui.tuannv.qldv.util.ActivityUtil;
import com.haui.tuannv.qldv.util.TProgressDialog;

import static android.app.Activity.RESULT_OK;
import static com.haui.tuannv.qldv.util.Constant.BUNDLE_USER;
import static com.haui.tuannv.qldv.util.Constant.ConstBundle.BUNDLE_KEY_ID;
import static com.haui.tuannv.qldv.util.Constant.SharePreference.SHARE_PRE_NAME;

/**
 * Created by tuanbg on 3/29/17.
 * quan ly thu tien
 */
public class RevenueFragment extends Fragment implements RevenueListener {
    private FragmentSpendBinding mBinding;
    private RevenueModelView mViewModel;
    private TProgressDialog mDialog;
    private ObservableField<Department> mDepartment = new ObservableField<>();
    private ObservableField<SchoolYear> mSchoolYear = new ObservableField<>();
    private ObservableField<Fee> mFee = new ObservableField<>();
    private User mUser = new User();
    private ResponseItem<DataDepartment> mData = new ResponseItem<>();

    public static RevenueFragment newInstance(User user) {
        RevenueFragment fragment = new RevenueFragment();
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_spend, container, false);
        getDataFromIntent();
        mViewModel = new RevenueModelView(getActivity(), this, DepartmentRepository.getInstance(),
                mUser.getId());
        mBinding.setFragment(this);
        mBinding.setViewmodel(mViewModel);
        mBinding.setUser(mUser);
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
        setSellectSpinner();
    }

    public void getDataFromIntent() {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        mUser = (User) bundle.getSerializable(BUNDLE_USER);
    }

    public void setSellectSpinner() {
        mBinding.spinDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Department item = (Department) parent.getItemAtPosition(position);
                String idDepartment = item.getId();
                getIdDepartment(idDepartment);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onSuccess(ResponseItem<DataDepartment> data) {
        if (data == null) return;
        mData = data;
        mBinding.spinDepartment.setAdapter(mViewModel.getDepartment(data));
        mBinding.spinSchoolYear.setAdapter(mViewModel.getSchoolYear(data));
        mBinding.spinFee.setAdapter(mViewModel.getFees(data));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            mViewModel.getDepartment();
        }
    }

    @Override
    public void onError(String msg) {
        ActivityUtil.showToast(getActivity(), getString(R.string.msg_no_connect));
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
        startActivityForResult(OtherRevenueActivity.getSpendIntent(getActivity()), 1);
    }

    public void getIdDepartment(String id) {
        SharedPreferences sharedPreferences =
                getActivity().getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(BUNDLE_KEY_ID, id);
        editor.apply();
    }
}

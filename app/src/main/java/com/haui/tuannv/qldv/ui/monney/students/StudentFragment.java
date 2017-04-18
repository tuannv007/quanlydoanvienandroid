package com.haui.tuannv.qldv.ui.monney.students;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.data.local.model.Classes;
import com.haui.tuannv.qldv.data.local.model.DataStudents;
import com.haui.tuannv.qldv.data.local.model.Fee;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.data.local.model.Student;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.databinding.FragmentStudentsBinding;
import com.haui.tuannv.qldv.ui.monney.MoneyActivity;
import com.haui.tuannv.qldv.util.ActivityUtil;
import java.util.ArrayList;
import java.util.List;

import static com.haui.tuannv.qldv.util.Constant.ConstBundle.BUNDLE_CLASSES;
import static com.haui.tuannv.qldv.util.Constant.ConstBundle.BUNDLE_FEE;

/**
 * Created by tuanbg on 4/18/17.
 */

public class StudentFragment extends Fragment implements StudentListener {
    private FragmentStudentsBinding mBinding;
    private StudentViewModel mViewModel;
    private ObservableField<StudentAdapter> mAdapter = new ObservableField<>();
    private Classes mClasses;
    private Fee mFee;
    private List<Student> mStudentList = new ArrayList<>();

    public static StudentFragment newInstance(Classes classes, Fee fee) {
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_CLASSES, classes);
        args.putSerializable(BUNDLE_FEE, fee);
        StudentFragment fragment = new StudentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_students, container, false);
        getDataFromIntent();
        mViewModel = new StudentViewModel(String.valueOf(mFee.getId()), this,
                DepartmentRepository.getInstance());
        mBinding.setFragment(this);
        mBinding.setViewmodel(mViewModel);
        return mBinding.getRoot();
    }

    public void getDataFromIntent() {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        mClasses = (Classes) bundle.getSerializable(BUNDLE_CLASSES);
        mFee = (Fee) bundle.getSerializable(BUNDLE_FEE);
    }

    @Override
    public void onSuccess(ResponseItem<DataStudents> data) {
        mStudentList.clear();
        mStudentList.addAll(data.getData().getStudents());
        mAdapter.set(new StudentAdapter(mStudentList));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getStudent(mClasses.getId(), mFee.getId());
        ((MoneyActivity) getActivity()).setActionBarTitle(mClasses.getName());
    }

    @Override
    public void onError(String msg) {
        ActivityUtil.showToast(getActivity(), getString(R.string.msg_no_connect));
    }

    @Override
    public void updateSuccess() {
        ActivityUtil.showToast(getActivity(), getString(R.string.msg_update_money_success));
    }

    @Override
    public void updateError(String msg) {
        ActivityUtil.showToast(getActivity(), msg);
    }

    public ObservableField<StudentAdapter> getAdapter() {
        return mAdapter;
    }

    public void loadData() {
        mViewModel.getStudent(mClasses.getId(), mFee.getId());
    }
}

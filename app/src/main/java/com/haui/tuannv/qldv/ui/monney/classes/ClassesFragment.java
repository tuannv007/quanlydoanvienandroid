package com.haui.tuannv.qldv.ui.monney.classes;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.data.local.model.Classes;
import com.haui.tuannv.qldv.data.local.model.Department;
import com.haui.tuannv.qldv.data.local.model.Fee;
import com.haui.tuannv.qldv.data.local.model.SchoolYear;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.databinding.FragmentClassesBinding;
import com.haui.tuannv.qldv.ui.monney.MoneyActivity;
import com.haui.tuannv.qldv.ui.monney.students.StudentFragment;
import com.haui.tuannv.qldv.util.ActivityUtil;
import java.util.ArrayList;
import java.util.List;

import static com.haui.tuannv.qldv.util.Constant.ConstBundle.BUNDLE_DEPARTMENT;
import static com.haui.tuannv.qldv.util.Constant.ConstBundle.BUNDLE_FEE;
import static com.haui.tuannv.qldv.util.Constant.ConstBundle.BUNDLE_SCHOOLYEAR;

/**
 * Created by tuanbg on 4/11/17.
 */

public class ClassesFragment extends Fragment implements ClassesListener {
    private FragmentClassesBinding mBinding;
    private ObservableField<ClassesAdapter> mAdapter = new ObservableField<>();
    private List<Classes> mClassesList = new ArrayList<>();
    private ClassesViewModel mViewModel;
    private String mDepartmentId;
    private String mSchoolId;
    private Fee fee = new Fee();
    private Department mDepartment;

    public static ClassesFragment newInstance(Department department, SchoolYear schoolYear,
            Fee fee) {
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_DEPARTMENT, department);
        args.putSerializable(BUNDLE_SCHOOLYEAR, schoolYear);
        args.putSerializable(BUNDLE_FEE, fee);
        ClassesFragment fragment = new ClassesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_classes, container, false);
        getDataFromIntent();
        mViewModel = new ClassesViewModel(this, DepartmentRepository.getInstance());
        mBinding.setFragment(this);
        mBinding.setViewmodel(mViewModel);
        return mBinding.getRoot();
    }

    public void getDataFromIntent() {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        mDepartment = (Department) bundle.getSerializable(BUNDLE_DEPARTMENT);
        SchoolYear schoolYear = (SchoolYear) bundle.getSerializable(BUNDLE_SCHOOLYEAR);
        fee = (Fee) bundle.getSerializable(BUNDLE_FEE);
        if (mDepartment == null || schoolYear == null) return;
        mDepartmentId = mDepartment.getId();
        mSchoolId = schoolYear.getId();
    }

    public ObservableField<ClassesAdapter> getAdapter() {
        return mAdapter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getClassesFromDepartment(mDepartmentId, mSchoolId);
        ((MoneyActivity) getActivity()).setActionBarTitle(mDepartment.getName());
    }

    @Override
    public void getDataSuccess(List<Classes> classes) {
        mClassesList.clear();
        mClassesList.addAll(classes);
        mAdapter.set(new ClassesAdapter(mViewModel, this, mClassesList));
    }

    @Override
    public void getDataError(String msg) {
        ActivityUtil.showToast(getActivity(), getString(R.string.msg_no_connect));
    }

    @Override
    public void getStudent(Classes classes, Fee fee) {
        ActivityUtil.addFragmentToActivity((AppCompatActivity) getActivity(), R.id.frame_layout,
                StudentFragment.newInstance(classes, fee));
    }

    public String getDepartmentId() {
        return mDepartmentId;
    }

    public String getSchoolId() {
        return mSchoolId;
    }

    public void loadData(String departmentId, String schoolId) {
        mViewModel.getClassesFromDepartment(departmentId, schoolId);
    }

    public Fee getFee() {
        return fee;
    }
}

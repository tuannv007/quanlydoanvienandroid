package com.haui.tuannv.qldv.ui.monney.classes;

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
import com.haui.tuannv.qldv.data.local.model.Department;
import com.haui.tuannv.qldv.data.local.model.Fee;
import com.haui.tuannv.qldv.data.local.model.SchoolYear;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.databinding.FragmentClassesBinding;
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
        mViewModel = new ClassesViewModel(getActivity(), this, DepartmentRepository.getInstance());
        mBinding.setFragment(this);
        mBinding.setViewmodel(mViewModel);
        return mBinding.getRoot();
    }

    public void getDataFromIntent() {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        Department department = (Department) bundle.getSerializable(BUNDLE_DEPARTMENT);
        SchoolYear schoolYear = (SchoolYear) bundle.getSerializable(BUNDLE_SCHOOLYEAR);
        Fee fee = (Fee) bundle.getSerializable(BUNDLE_FEE);
        if (department == null || schoolYear == null) return;
        mDepartmentId = department.getId();
        mSchoolId = schoolYear.getId();
    }

    public ObservableField<ClassesAdapter> getAdapter() {
        return mAdapter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getClassesFromDepartment(mDepartmentId, mSchoolId);
    }

    @Override
    public void getDataSuccess(List<Classes> classes) {
        mClassesList.clear();
        mClassesList.addAll(classes);
        mAdapter.set(new ClassesAdapter(mClassesList));

    }

    @Override
    public void getDataError(String msg) {

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


}

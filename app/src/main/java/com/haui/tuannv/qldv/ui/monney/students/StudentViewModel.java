package com.haui.tuannv.qldv.ui.monney.students;

import android.databinding.ObservableBoolean;
import android.util.Log;
import com.haui.tuannv.qldv.data.DataCallback;
import com.haui.tuannv.qldv.data.local.model.DataStudents;
import com.haui.tuannv.qldv.data.local.model.ResponseItem;
import com.haui.tuannv.qldv.data.local.model.Student;
import com.haui.tuannv.qldv.data.remote.department.DepartmentRepository;
import com.haui.tuannv.qldv.network.Main;
import com.haui.tuannv.qldv.ui.BaseViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanbg on 4/18/17.
 */

public class StudentViewModel extends BaseViewModel {
    private StudentListener mListener;
    private DepartmentRepository mRepository;
    private ObservableBoolean mIsRefresh = new ObservableBoolean();
    private List<Student> mStudentList = new ArrayList<>();
    private String mFeeId;

    public StudentViewModel(String feeId, StudentListener listener,
            DepartmentRepository repository) {
        mListener = listener;
        mRepository = repository;
        mFeeId = feeId;
    }

    public void getStudent(int classId, int feeId) {
        mIsRefresh.set(true);
        mRepository.getStudents(classId, feeId, new DataCallback<ResponseItem<DataStudents>>() {
            @Override
            public void onSuccess(ResponseItem<DataStudents> data) {
                mListener.onSuccess(data);
                mStudentList.addAll(data.getData().getStudents());
                mIsRefresh.set(false);
            }

            @Override
            public void onError(String msg) {
                mListener.onError(msg);
                mIsRefresh.set(false);
            }
        });
    }

    public void aaa(Main.UpdateBody body) {
        mIsRefresh.set(true);
        mRepository.updateMoney(body, new DataCallback<ResponseItem>() {
            @Override
            public void onSuccess(ResponseItem data) {
                Log.e("tag", data.toString());
                mIsRefresh.set(false);
                mListener.updateSuccess();
            }

            @Override
            public void onError(String msg) {
                Log.e("ta2", msg);
                mIsRefresh.set(false);
                mListener.updateError(msg);
            }
        });
    }

    public void update() {
        Main.UpdateBody updateBody = new Main.UpdateBody(mFeeId, mStudentList);
        aaa(updateBody);
    }

    public ObservableBoolean getIsRefresh() {
        return mIsRefresh;
    }
}

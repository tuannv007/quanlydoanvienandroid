package com.haui.tuannv.qldv.ui.monney.students;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.data.local.model.Student;
import com.haui.tuannv.qldv.databinding.ItemStudentBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanbg on 4/18/17.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> mStudentList = new ArrayList<>();
    private LayoutInflater mInflater;

    public StudentAdapter(List<Student> studentList) {
        mStudentList = studentList;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemStudentBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_student, parent, false);
        return new StudentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        Student item = mStudentList.get(position);
        if (item != null) holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mStudentList == null ? 0 : mStudentList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        private ItemStudentBinding mBinding;

        public StudentViewHolder(ItemStudentBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
        }

        private void bind(Student item) {
            mBinding.setStudents(item);
            mBinding.executePendingBindings();
        }
    }
}

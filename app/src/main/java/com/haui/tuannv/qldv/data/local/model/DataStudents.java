package com.haui.tuannv.qldv.data.local.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DataStudents {

    @SerializedName("students")
    private List<Student> mStudents = null;

    public List<Student> getStudents() {
        return mStudents;
    }

    public void setStudents(List<Student> students) {
        mStudents = students;
    }
}

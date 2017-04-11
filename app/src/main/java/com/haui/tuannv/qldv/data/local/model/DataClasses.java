package com.haui.tuannv.qldv.data.local.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DataClasses {
    @SerializedName("classes")
    private List<Classes> classes = null;

    public List<Classes> getClasses() {
        return classes;
    }

    public void setClasses(List<Classes> classes) {
        this.classes = classes;
    }
}

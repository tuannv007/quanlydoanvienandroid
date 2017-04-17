package com.haui.tuannv.qldv.data.local.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.google.gson.annotations.SerializedName;
import com.haui.tuannv.qldv.BR;
import java.util.List;

public class DataRevenue extends BaseObservable {
    @SerializedName("input_total")
    private String mInputTotal;
    @SerializedName("output_total")
    private String mOutputTotal;
    @SerializedName("rest_total")
    private int mRestTotal;
    @SerializedName("payments")
    private List<Payment> mPayments = null;

    @Bindable
    public String getInputTotal() {
        return mInputTotal;
    }

    public void setInputTotal(String inputTotal) {
        mInputTotal = inputTotal;
        notifyPropertyChanged(BR.inputTotal);
    }

    @Bindable
    public String getOutputTotal() {
        return mOutputTotal;
    }

    public void setOutputTotal(String outputTotal) {
        mOutputTotal = outputTotal;
        notifyPropertyChanged(BR.outputTotal);
    }

    @Bindable
    public int getRestTotal() {
        if (mRestTotal < 0) return 0;
        return mRestTotal;
    }

    public void setRestTotal(int restTotal) {
        mRestTotal = restTotal;
        notifyPropertyChanged(BR.restTotal);
    }

    public List<Payment> getPayments() {
        return mPayments;
    }

    public void setPayments(List<Payment> payments) {
        mPayments = payments;
    }
}

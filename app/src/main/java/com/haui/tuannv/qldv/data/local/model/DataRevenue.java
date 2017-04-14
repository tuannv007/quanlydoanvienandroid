package com.haui.tuannv.qldv.data.local.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DataRevenue {

    @SerializedName("input_total")
    private String mInputTotal;
    @SerializedName("output_total")
    private String mOutputTotal;
    @SerializedName("rest_total")
    private int mRestTotal;
    @SerializedName("payments")
    private List<Payment> mPayments = null;

    public String getInputTotal() {
        return mInputTotal;
    }

    public void setInputTotal(String inputTotal) {
        mInputTotal = inputTotal;
    }

    public String getOutputTotal() {
        return mOutputTotal;
    }

    public void setOutputTotal(String outputTotal) {
        mOutputTotal = outputTotal;
    }

    public int getRestTotal() {
        return mRestTotal;
    }

    public void setRestTotal(int restTotal) {
        mRestTotal = restTotal;
    }

    public List<Payment> getPayments() {
        return mPayments;
    }

    public void setPayments(List<Payment> payments) {
        mPayments = payments;
    }
}

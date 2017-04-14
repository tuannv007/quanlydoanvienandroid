package com.haui.tuannv.qldv.data.local.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.haui.tuannv.qldv.BR;

/**
 * Created by tuanbg on 4/14/17.
 */

public class OtherSpend extends BaseObservable {
    private String mTitle;
    private int mYear;
    private double mAmount;
    private String mDesciption;

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        mYear = year;
        notifyPropertyChanged(BR.year);
    }

    @Bindable
    public double getAmount() {
        return mAmount;
    }

    public void setAmount(double amount) {
        mAmount = amount;
        notifyPropertyChanged(BR.amount);
    }
    @Bindable
    public String getDesciption() {
        return mDesciption;
    }

    public void setDesciption(String desciption) {
        mDesciption = desciption;
    }
}

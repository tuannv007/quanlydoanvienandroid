package com.haui.tuannv.qldv.data.local.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.haui.tuannv.qldv.BR;

/**
 * Created by tuanbg on 4/9/17.
 */
public class BaseObject extends BaseObservable implements Parcelable {
    @SerializedName("id")
    private String mId;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("updated_at")
    private Object mUpdatedAt;

    protected BaseObject(Parcel in) {
        mId = in.readString();
        mCreatedAt = in.readString();
    }

    public static final Creator<BaseObject> CREATOR = new Creator<BaseObject>() {
        @Override
        public BaseObject createFromParcel(Parcel in) {
            return new BaseObject(in);
        }

        @Override
        public BaseObject[] newArray(int size) {
            return new BaseObject[size];
        }
    };

    @Bindable
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.mCreatedAt = createdAt;
        notifyPropertyChanged(BR.createdAt);
    }

    @Bindable
    public Object getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.mUpdatedAt = updatedAt;
        notifyPropertyChanged(BR.updatedAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mCreatedAt);
    }

    public BaseObject() {
    }
}

package com.haui.tuannv.qldv.data.local.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tuanbg on 4/7/17.
 */
public class ResponseItem<T> {
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseItem{" +
            "code=" + code +
            ", message='" + message + '\'' +
            ", data=" + data +
            '}';
    }
}

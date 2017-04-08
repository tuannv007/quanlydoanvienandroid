package com.haui.tuannv.qldv.data;

public interface DataCallback<T> {
    void onSuccess(T data);
    void onError(String msg);
}

package com.lr.baselibrary.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GeneralResult<T> implements Serializable {
    private static final long serialVersionUID = 154307691161700036L;
    @SerializedName(value = "code", alternate = {"errorCode", "status"})
    public int code;
    @SerializedName(value = "msg", alternate = {"errorMsg", "message"})
    public String msg;
    public String time;
    @SerializedName(value = "data", alternate = {"token", "number"})
    public T data;

    @Override
    public String toString() {
        return "GeneralResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' +
                ", data=" + data +
                '}';
    }
}

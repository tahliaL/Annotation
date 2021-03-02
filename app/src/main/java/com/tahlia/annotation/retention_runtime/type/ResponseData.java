package com.tahlia.annotation.retention_runtime.type;

public class ResponseData<T extends Number, D> {
    private int code;
    private String msg;
    private T data1;
    private D data2;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData1() {
        return data1;
    }

    public void setData1(T data1) {
        this.data1 = data1;
    }

    public D getData2() {
        return data2;
    }

    public void setData2(D data2) {
        this.data2 = data2;
    }
}

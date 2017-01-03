package com.somnus.pay.core.pojo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private int               code             = -1; // 返回状态码
    private boolean           ok;                   // 返回状态
    private Object            data;                 // 返回信息对象

    public Message() {
        super();
    }

    public Message(boolean ok, Object data) {
        super();
        this.ok = ok;
        this.data = data;
    }

    public Message(int code, boolean ok, Object data) {
        super();
        this.code = code;
        this.ok = ok;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Msg [code=" + code + ", ok=" + ok + ",  data=" + data + "]";
    }


    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }
}

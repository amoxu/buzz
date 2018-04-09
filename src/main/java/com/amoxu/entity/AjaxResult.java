package com.amoxu.entity;

import com.alibaba.fastjson.JSON;
import com.amoxu.util.StaticEnum;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AjaxResult<T> {
    private int status;
    private T data;
    private String msg = "";
    private int count = -1;


    public void ok() {
        this.status = 0;
        setMsg(StaticEnum.OPT_SUCCESS);
    }

    public void failed() {
        this.status = 1;
        setMsg(StaticEnum.OPT_ERROR);
    }

    public int getStatus() {
        return status;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        if (this.count == -1) {
            if (data instanceof java.util.List)
                this.count = ((List) data).size();
            this.count = 1;
        }
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}


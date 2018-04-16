package com.amoxu.entity;

import java.util.List;

public class PageResult<T> {
    /*查询数据列表*/
    private List<T> list;
    /*页数*/
    private int offset;
    /*每页数据量*/
    private int limit;
    /*总数据量*/
    private int count;

    public List<T> getList() {
        return list;
    }

    public PageResult<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public PageResult<T> setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public PageResult<T> setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public int getCount() {
        return count;
    }

    public PageResult<T> setCount(int count) {
        this.count = count;
        return this;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "list=" + list +
                ", offset=" + offset +
                ", limit=" + limit +
                ", count=" + count +
                '}';
    }
}

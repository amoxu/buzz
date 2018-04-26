package com.amoxu.service;

import com.amoxu.entity.PageResult;

public interface SearchService {

    PageResult search(Integer type, String key, PageResult pageResult);
}

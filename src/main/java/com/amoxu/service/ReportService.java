package com.amoxu.service;

import com.amoxu.entity.AjaxResult;

import javax.servlet.http.HttpServletRequest;

public interface ReportService {

    AjaxResult report(String reason, Integer type, Integer cid, HttpServletRequest request);
}

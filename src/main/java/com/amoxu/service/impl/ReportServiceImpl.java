package com.amoxu.service.impl;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Report;
import com.amoxu.mapper.ReportMapper;
import com.amoxu.service.ReportService;
import com.amoxu.util.NetworkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Override
    public AjaxResult report(String reason, Integer type, Integer cid, HttpServletRequest request) {

        Report report = new Report();
        report.setAddr(NetworkUtil.getIpAddr(request));
        report.setCid(cid);
        report.setType(type);
        report.setReason(reason);
        report.setCtime(new Date());
        AjaxResult<String> ajaxResult = new AjaxResult<>();
        try {
            reportMapper.insertSelective(report);
            ajaxResult.ok();
            return ajaxResult;
        } catch (Exception e) {
            ajaxResult.setMsg("服务器故障，请联系管理员");
            return ajaxResult;
        }

    }
}

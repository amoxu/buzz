package com.amoxu.controller;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Feature;
import com.amoxu.service.FeatureService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class FeatureController {
    @Resource(name = "featureServiceImpl")
    private FeatureService featureService;
    @RequestMapping(value = "/feature",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String regFeature() {

        AjaxResult<Feature> ajaxResult = new AjaxResult<>();

        List<Feature> regFeatures = featureService.getRegFeatures();
        ajaxResult.setData(regFeatures);
        ajaxResult.ok();
        return ajaxResult.toString();
    }


}

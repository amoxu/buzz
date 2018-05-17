package com.amoxu.controller;

import com.amoxu.service.NearbyService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class NearbyController {
    @Resource(name = "nearbyServiceImpl")
    private NearbyService nearbyService;

    @RequestMapping(value = "/nearby"
            , method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String getNearby(HttpServletRequest request) {
        return nearbyService.nearbyUser(request).toString();
    }
}

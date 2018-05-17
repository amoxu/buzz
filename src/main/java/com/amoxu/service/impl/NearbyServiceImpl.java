package com.amoxu.service.impl;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Coordinate;
import com.amoxu.entity.User;
import com.amoxu.entity.UserExample;
import com.amoxu.mapper.UserMapper;
import com.amoxu.service.NearbyService;
import com.amoxu.util.Datx.IpUtil;
import com.amoxu.util.JedisPoolUtil;
import com.amoxu.util.JedisUtil;
import com.amoxu.util.NetworkUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class NearbyServiceImpl implements NearbyService {
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Autowired
    UserMapper userMapper;
    private final static String LocationKey = "location";

    private final static double BeijinLng = 116.405285;
    private final static double BeijinLat = 39.904989;

    @Override

    public AjaxResult nearbyUser(HttpServletRequest request) {
        String ipAddr = NetworkUtil.getIpAddr(request);
        IpUtil ipUtil = new IpUtil();
        Coordinate userLocation = ipUtil.getLocation(ipAddr, sqlSessionFactory);
        Iterable<? extends GeoRadiusResponse> location;

        User principal;
        try (Jedis jedis = JedisPoolUtil.getJedis()) {
            Subject subject = SecurityUtils.getSubject();
            boolean authenticated = subject.isAuthenticated();
            if (authenticated) {
                principal = (User) subject.getPrincipal();
                jedis.geoadd(LocationKey.getBytes(), userLocation.getLng(), userLocation.getLat(), JedisUtil.enSeri(principal.clone()));
            }
            if (userLocation == null) {
                location = jedis.georadius(LocationKey, BeijinLng, BeijinLng, 100, GeoUnit.KM);
            } else {
                location = jedis.georadius(LocationKey, userLocation.getLng(), userLocation.getLat(), 100, GeoUnit.KM);
            }
        }

        /*将临近用户存进临近用户列表*/
        List<User> nearbyUser = new ArrayList<>();
        User outUser;
        for (GeoRadiusResponse geo : location) {
            byte[] member = geo.getMember();
            outUser = JedisUtil.deSeri(member, User.class);
            nearbyUser.add(outUser);
            System.err.println(JSON.toJSONString(geo));
        }

        if (nearbyUser.size() < 6) {
            UserExample userExample = new UserExample();
            userExample.setOrderByClause("rand()");
            userExample.setLimit(6 - nearbyUser.size());
            List<User> users = userMapper.selectByExample(userExample);
            for (User user : users) {
                nearbyUser.add(user.publicUser());
            }
        }
        AjaxResult<List<User>> ajaxResult = new AjaxResult<>();
        ajaxResult.ok();
        ajaxResult.setData(nearbyUser);
        return ajaxResult;
    }

}

package com.amoxu.service;

import com.amoxu.exception.UnLoginException;

import java.util.List;

public interface UserFeatureService {
    int createUserFeature(List<Integer> features) throws UnLoginException;

    /**
     * 通过关键字设置用户兴趣爱好
     * @param uid 用户id
     * @param features 兴趣关键词
     * @param count 增加的数值
     * */

    int setUserFeature(Integer uid, Double count, String... features);

    /**
     * 通过id设置用户兴趣
     * @param uid 用户id
     * @param fids 兴趣id */
    int setUserFeature(Integer uid, Integer... fids);


}

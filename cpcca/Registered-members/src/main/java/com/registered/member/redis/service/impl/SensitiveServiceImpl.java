package com.registered.member.redis.service.impl;


import com.registered.member.redis.IRedisService;
import org.springframework.stereotype.Service;


/**
 * Created by xf.shu 2018-05-08
 * 评论验证
 */
@Service
public class SensitiveServiceImpl extends IRedisService {
    private static final String REDIS_KEY = "note";

    @Override
    protected String getRedisKey() {
        return this.REDIS_KEY;
    }

}
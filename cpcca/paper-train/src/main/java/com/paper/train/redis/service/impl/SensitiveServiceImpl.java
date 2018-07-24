package com.paper.approve.redis.service.impl;

import com.paper.common.redis.IRedisService;
import org.springframework.stereotype.Service;
import java.util.Map;


/**
 * Created by xf.shu 2018-05-08
 * 评论验证
 */
@Service
public class SensitiveServiceImpl extends IRedisService<String> {
    private static final String REDIS_KEY = "note";

    @Override
    protected String getRedisKey() {
        return this.REDIS_KEY;
    }

}
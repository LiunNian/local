package cn.org.cpcca.feignserver.paper.shiro.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

@Configuration
public class HttpSessionConfiguration {
    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new CookieHttpSessionStrategy();
    }
}
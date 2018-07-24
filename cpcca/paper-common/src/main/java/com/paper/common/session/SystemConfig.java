package com.paper.common.session;




import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by xf.shu on 2018-4-12
 */
//@Configuration
public class SystemConfig {
    /**
     * 跨域
     * @return
     */
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**");
//            }
//        };
//    }

//    /**
//     * 设置session过期时间
//     * @return
//     */
//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer(){
//        return new EmbeddedServletContainerCustomizer() {
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                container.setSessionTimeout(1800);//单位为S
//            }
//        };
//    }





}

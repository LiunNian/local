package cn.org.cpcca.feignserver.paper.configuration;

import feign.Contract;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@EnableFeignClients(basePackages = "cn.org.cpcaa.feignserver.services")
public class FeignClientsConfiguration implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                Enumeration<String> values = request.getHeaders(name);
                while (values.hasMoreElements()) {
                    String value = values.nextElement();
                    System.out.println("header set:");
                    System.out.println(name);
                    System.out.println(value);
                    template.header(name, value);
                }
            }
        }

    }
    @Bean
    public Contract feignContract(){
        //return new feign.Contract.Default();
        return new Contract.Default();
    }

    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.FULL;
    }
}

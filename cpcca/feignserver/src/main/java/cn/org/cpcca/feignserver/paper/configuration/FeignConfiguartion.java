package cn.org.cpcca.feignserver.paper.configuration;

import com.google.common.base.Strings;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class FeignConfiguartion {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes == null) {
                return;
            }
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            String sessionId = request.getSession().getId();
            System.out.println("header set cookie:");
            System.out.println(sessionId);
            if (!Strings.isNullOrEmpty(sessionId)) {
                requestTemplate.header("Cookie", "SESSION=" + sessionId);
            }
        };
    }
}

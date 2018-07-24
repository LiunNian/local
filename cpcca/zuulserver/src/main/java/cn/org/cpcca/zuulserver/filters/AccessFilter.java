package cn.org.cpcca.zuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
@RefreshScope
public class AccessFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
    @Value("${orgins}")
    private String[] IPs;
    @Override
    public String filterType() {
        //前置过滤器
        return "pre";
    }

    @Override
    public int filterOrder() {
        //优先级，数字越大，优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否执行该过滤器，true代表需要过滤
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        String originHeader = request.getHeader("Origin");
        System.out.println(IPs.toString());
        if (Arrays.asList(IPs).contains(originHeader))
        {
            response.addHeader("Access-Control-Allow-Origin", originHeader);
            response.addHeader("Access-Control-Allow-Credentials","true");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
        }
        //response.setHeader("Access-Control-Allow-Headers", "content-type, accept");
        /*response.addHeader("Access-Control-Allow-Origin", "http://cpcca.cqshusheng.cn");
        response.addHeader("Access-Control-Allow-Credentials","true");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");*/
        //response.setContentType("text/html;charset=UTF-8");
        /*log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

        //获取传来的参数accessToken
        Object accessToken = request.getParameter("accessToken");
        if(accessToken == null) {
            log.warn("access token is empty");
            //过滤该请求，不往下级服务去转发请求，到此结束
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"result\":\"accessToken is empty!\"}");
            return null;
        }
        //如果有token，则进行路由转发
        log.info("access token ok");
        //这里return的值没有意义，zuul框架没有使用该返回值*/
        return null;
    }
}

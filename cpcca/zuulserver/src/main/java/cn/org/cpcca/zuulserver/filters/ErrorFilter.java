package cn.org.cpcca.zuulserver.filters;

import cn.org.cpcca.zuulserver.controllers.BaseController;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class ErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        //System.out.println(ctx.getResponseBody());
        ctx.setSendZuulResponse(false);
        ctx.setResponseBody("{\"code\":0,\"data\":[],\"message\":\"出现异常，确认您做的操作为常规操作\"}");
        return null;
    }
}

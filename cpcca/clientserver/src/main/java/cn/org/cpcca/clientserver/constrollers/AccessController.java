package cn.org.cpcca.clientserver.constrollers;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RefreshScope
public class AccessController {
    @Value("${myww}")
    String myww;
    @RequestMapping(value="/hi")
    public String hi(HttpServletRequest request){
        HttpSession session =  request.getSession();
        return myww;
    }
}

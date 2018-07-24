package cn.org.cpcca.paperserver.controllers;

import cn.org.cpcca.paperserver.models.ReturnDataModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * 认证控制器
 */
@RestController
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class VerifyController {
    /**
     * 登录身份认证
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ReturnDataModel login(@RequestParam("username") String username, @RequestParam("password") String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        boolean flag = false;
        try {
            //4、登录，即身份验证
            subject.login(token);
            flag = subject.isAuthenticated();
            if (flag) {
                System.out.println("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                return new ReturnDataModel(0,"登录成功","redirect:/index");

            } else {
                token.clear();
                System.out.println("用户[" + username + "]登录认证失败,重新登陆");
                return new ReturnDataModel(1,"登录认证失败,重新登陆","redirect:/login");
            }
        } catch ( UnknownAccountException uae ) {
            //logger.info("对用户[" + username + "]进行登录验证..验证失败-username wasn't in the system");
            return new ReturnDataModel(1,"登录验证..验证失败,用户名不存在","redirect:/login");
        } catch ( IncorrectCredentialsException ice ) {
            //logger.info("对用户[" + username + "]进行登录验证..验证失败-password didn't match");
            return new ReturnDataModel(1,"登录验证..验证失败,密码错误","redirect:/login");
        } catch ( LockedAccountException lae ) {
            //logger.info("对用户[" + username + "]进行登录验证..验证失败-account is locked in the system");
            return new ReturnDataModel(1,"登录验证..验证失败,用户已登录","redirect:/login");

        } catch ( AuthenticationException ae ) {
            //logger.error(ae.getMessage());
            return new ReturnDataModel(1,ae.getMessage(),"redirect:/login");


        }
        //return String.valueOf(flag);
    }

    /**logout
     * 用户退出
     * @return
     */
    @RequestMapping(value="/logout",method = RequestMethod.POST)
    public ReturnDataModel logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ReturnDataModel(0,"退出成功","redirect:/login");
    }
}

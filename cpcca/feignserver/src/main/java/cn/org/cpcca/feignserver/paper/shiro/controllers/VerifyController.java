package cn.org.cpcca.feignserver.paper.shiro.controllers;

import cn.org.cpcca.feignserver.paper.models.ReturnDataModel;
import cn.org.cpcca.feignserver.paper.shiro.models.AccountModel;
import cn.org.cpcca.feignserver.paper.shiro.models.User;
import cn.org.cpcca.feignserver.paper.shiro.services.AccountServiceInterface;
import cn.org.cpcca.feignserver.paper.shiro.services.ResourcesServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证控制器
 */
@Service
@Api(value = "",hidden = true)
@RestController
public class VerifyController extends BaseController{
    @Autowired
    private AccountServiceInterface userService;
    /**
     * 登录身份认证
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value = "",hidden = true)
    @RequestMapping(value="/login")
    public ReturnDataModel login(
            HttpServletRequest request,
            String username,
            String password,
            String platform){

        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        User token = new User(username, password,"myrealm");
        boolean flag = false;
        try {
            //登录，即身份验证
            subject.login(token);
            flag = subject.isAuthenticated();
            if (flag) {
                HttpSession session =  request.getSession();
                //System.out.println("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                AccountModel userModel = userService.getNameByInfo(token.getUsername());
                int uid = userModel.getId();
                if(userService.getPlid(uid).contains(0) || userService.getPlatforms(uid).contains(platform)) {
                    session.setAttribute("uid", userModel.getId());
                    session.setAttribute("username", userModel.getUsername());
                    //System.out.println(session.getId());
                    //System.out.println(session.getAttribute("uid"));
                    dataMap.put("username", userModel.getUsername());
                    dataMap.put("flag", userModel.getFlag());
                    dataMap.put("role", userService.getsRoleByUid(userModel.getId()));
                    this.returnData(returnDataModel, 0, dataMap, "登录成功", "redirect:/index");
                }else{
                    returnDataModel.setCode(2);
                    returnDataModel.setMessage("您不具备操作本系统平台的权限");
                }

                return returnDataModel;

            } else {
                token.clear();
                //System.out.println("用户[" + username + "]登录认证失败,重新登录");
                returnDataModel.setMessage("登录认证失败,重新登录");
                returnDataModel.setRedirect("redirect:/login");
                return returnDataModel;
            }
        } catch ( UnknownAccountException uae ) {
            //logger.info("对用户[" + username + "]进行登录验证..验证失败-username wasn't in the system");
            returnDataModel.setMessage("登录验证..验证失败,用户名不存在");
            returnDataModel.setRedirect("login");
            return returnDataModel;
            //return new ReturnDataModel(1,"登录验证..验证失败,用户名不存在","redirect:/login");
        } catch ( IncorrectCredentialsException ice ) {
            //logger.info("对用户[" + username + "]进行登录验证..验证失败-password didn't match");

            returnDataModel.setMessage("登录验证..验证失败,密码错误");
            returnDataModel.setRedirect("login");
            return returnDataModel;
            //return new ReturnDataModel(1,"登录验证..验证失败,密码错误","redirect:/login");
        } catch ( LockedAccountException lae ) {
            //logger.info("对用户[" + username + "]进行登录验证..验证失败-account is locked in the system");
            returnDataModel.setMessage("登录验证..验证失败,用户已登录");
            returnDataModel.setRedirect("login");
            return returnDataModel;
            //return new ReturnDataModel(1,"登录验证..验证失败,用户已登录","redirect:/login");

        } catch ( AuthenticationException ae ) {
            //logger.error(ae.getMessage());
            returnDataModel.setMessage(ae.getMessage());
            returnDataModel.setRedirect("login");
            return returnDataModel;
            //return new ReturnDataModel(1,ae.getMessage(),"redirect:/login");
        }
        //return String.valueOf(flag);
    }

    /**logout
     * 用户退出
     * @return
     */
    @ApiOperation(value = "",hidden = true)
    @RequestMapping(value="/logout")
    public ReturnDataModel logout(HttpServletRequest request){
        ReturnDataModel returnDataModel= this.start();
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
            request.getSession().invalidate();
        }catch (Exception e){

        }
        this.returnData(returnDataModel,0,"","退出成功","login");
        return returnDataModel;
    }
    //修改密码
    //@RequestMapping(value="updatepassword")
    public ReturnDataModel updatePassword(
            @RequestParam("uid") int uid,
            @RequestParam("oldpassword") String oldpassword,
            @RequestParam("password") String password){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();

        AccountModel userModel = userService.selectUser(uid);
        String passwd = new Md5Hash(oldpassword).toString();
        //System.out.println(passwd);
        //System.out.println(userModel.getPassword());
        String newPasword = new Md5Hash(password).toString();
        if(passwd.equals(userModel.getPassword())){
            if(userService.updatePassword(newPasword,userModel.getUsername())>0){
                this.returnData(returnDataModel,0,dataMap,"修改密码成功");
            }else{
                returnDataModel.setMessage("修改密码失败");
            }
        }else{
            returnDataModel.setMessage("密码验证错误");
        }
        return returnDataModel;
    }
}

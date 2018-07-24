package cn.org.cpcca.feignserver.paper.shiro.realms;

import cn.org.cpcca.feignserver.paper.shiro.models.User;
import cn.org.cpcca.feignserver.train.models.TrainUser;
import cn.org.cpcca.feignserver.train.services.TrainUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

public class TrainRealm extends AuthorizingRealm{

    @Autowired
    private TrainUserService trainUserService;


    @Override
    public String getName() {
        return "trainSubscribe";
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();  //得到用户名
        TrainUser userModel = trainUserService.trainLogin(username);
        return new SimpleAuthenticationInfo(userModel, userModel.getPassword(), getName());
    }

    /**
     * Shiro权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User shiroUser = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 角色
        info.addRole("forecastTrain");
        return info;
    }
}

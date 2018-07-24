package cn.org.cpcca.feignserver.paper.shiro.realms;

import cn.org.cpcca.feignserver.paper.shiro.models.AccountModel;
import cn.org.cpcca.feignserver.paper.shiro.services.AccountServiceInterface;
import cn.org.cpcca.feignserver.paper.shiro.services.ResourcesServiceInterface;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

public class VerifyUriRealm extends AuthorizingRealm{
    private Logger logger = LoggerFactory.getLogger(VerifyUriRealm.class);
    @Autowired
    private AccountServiceInterface userService;
    @Autowired
    private ResourcesServiceInterface resourcesService;
    @Override
    public String getName() {
        return "myrealm1";
    }
    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();  //得到用户名
        String password = new String((char[])authenticationToken.getCredentials()); //得到密码
        if(userService!=null){
            AccountModel userModel = userService.getNameByInfo(username);
            //System.out.println(userModel.getUsername());
            System.out.println(userModel.getPassword());
//            List<String> roleStrlist=new ArrayList<String>();////用户的角色集合
//            List<String> perminsStrlist=new ArrayList<String>();//用户的权限集合
//            roleStrlist.add("admin");
//            perminsStrlist.add("cacuser:unitaccount");
//            userModel.setRoleStrlist(roleStrlist);
//            userModel.setPerminsStrlist(perminsStrlist);
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            //System.out.println(md5Password);
            if(!md5Password.equals(userModel.getPassword())){
                throw new IncorrectCredentialsException(); //如果密码错误
            }else{
                //如果身份认证验证成功，返回一个AuthenticationInfo实现；
                int uid = userModel.getId();
                userModel.setRoleStrlist(resourcesService.getPrems(uid));
                /*List<Integer> rids = userService.getRoleIds(uid);
                List<Integer> pids = userService.getPermissionIds(rids);*/
                //userModel.setPerminsStrlist(perminsStrlist);
                //userModel.setPerminsStrlist(perminsStrlist);
                return new SimpleAuthenticationInfo(userModel, password, getName());
            }
        }else{
            throw new UnknownAccountException(); //如果用户名错误
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");
        AccountModel userModel = (AccountModel)principalCollection.getPrimaryPrincipal();
//        //到数据库查是否有此对象
//        User user = null;// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
//        user = userMapper.findByName(loginName);
        if (userModel != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            System.out.println(userModel.getRoleStrlist().toString());
            //System.out.println(userModel.getPerminsStrlist().toString());
            //用户的角色集合
            info.addRoles(userModel.getRoleStrlist());
            //用户的权限集合
            //info.addStringPermissions(userModel.getPerminsStrlist());
            return info;
        }
        return null;
    }

}

package com.paper.common.shiro.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * shiro认证类
 */
public class ShiroDbRealm extends AuthorizingRealm {
	
//	@Autowired
//    private CulturalAppUserServiceClient culturalAppUserServiceClient;


	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
//		User user = null;
//		try {
//			user = culturalAppUserServiceClient.getUser(new User(token.getUsername(),null));
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//		// 无法查询到数据
//		if(user == null || "".equals(user.getPassword())){
//			throw new UnknownAccountException("用户不存在!");
//		}
//
//		// 如果帐号被禁用，输出
//		if(user.getState() == null || 0 == user.getState()){
//			throw new LockedAccountException("用户被锁定");
//		}
//		Object pwd = user.getPassword();
//		// 存入session
//		Object principal = user;
//		user.setPassword(AddUserEncryption.encryptionPasswordByMD5(user.getPassword())); //加密后存入缓存

		// 认证缓存信息SimpleAuthenticationInfo
		return  new SimpleAuthenticationInfo("", "", getName());
	}

	/**
	 * Shiro权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		String shiroUser =(String) principals.getPrimaryPrincipal();
//		List<Resources> roleList = culturalAppUserServiceClient.getResources(new Resources(shiroUser,null,null,null,"2",null,0));
		Set<String> urlSet = new HashSet<String>();
//		for (Resources roleId : roleList) {
//			urlSet.add(roleId.getUrl());
//		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(urlSet);

		return info;
	}

}

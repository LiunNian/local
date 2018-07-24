package com.paper.common.shiro.config;

import com.paper.common.shiro.realms.ShiroDbRealm;
import com.paper.common.shiro.redis.RedisCacheManager;
import com.paper.common.shiro.redis.RedisSessionDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Value(value = "${shiro_redis_session}")
	private int shiro_redis_session;

	@Value(value = "${shiro_redis_cache}")
	private int shiro_redis_cache;


    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断

        // 启动时刷新shiro配置接口
//        filterChainDefinitionMap.put("/shiro/reloadShiro", "anon");

        // 登录相关
//        filterChainDefinitionMap.put("/login", "anon");
//        filterChainDefinitionMap.put("/api/**", "anon");
          filterChainDefinitionMap.put("/mapping/system/user/getUser", "anon");
          filterChainDefinitionMap.put("/mapping/system/dict/findByTypeName", "anon");
          filterChainDefinitionMap.put("/wechat/system/clockIn/clockIn", "anon");
//        // swagger2相关
//        filterChainDefinitionMap.put("/v2/**", "anon");
//        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
//        filterChainDefinitionMap.put("/webjars/**", "anon");

        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "anon");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面 -->现在通过后就请求index
//        shiroFilterFactoryBean.setLoginUrl("/index");
//        shiroFilterFactoryBean.setSuccessUrl();

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 配置认证器
     * @return
     */
    @Bean
    public ShiroDbRealm myShiroRealm(){
    	ShiroDbRealm myShiroRealm = new ShiroDbRealm();
        return myShiroRealm;
    }
    /**
     * RedisCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，
     * 然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库。
     * 
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager() {
    	RedisCacheManager cacheManager = new RedisCacheManager();
    	cacheManager.setRedisTemplate(redisTemplate);
    	cacheManager.setExpire(shiro_redis_cache);
        return cacheManager;
    }
    
    @Bean
    public RedisSessionDAO redisSessionDAO() {
    	RedisSessionDAO sessionDAO = new RedisSessionDAO();
    	sessionDAO.setRedisTemplate(redisTemplate);
    	sessionDAO.setExpire(shiro_redis_session);
        return sessionDAO;
    }
  
    
    @Bean
    public DefaultWebSessionManager sessionManager() {
    	DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
    	defaultWebSessionManager.setSessionDAO(redisSessionDAO() );
    	defaultWebSessionManager.setSessionIdCookie(sessionIdCookie());
    	defaultWebSessionManager.setGlobalSessionTimeout(shiro_redis_session);
        return defaultWebSessionManager;
    }
    
    @Bean
    public SimpleCookie sessionIdCookie() {
    	SimpleCookie simpleCookie = new SimpleCookie("culturalSessionId");
        return simpleCookie;
    }
    
    
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());

        // <!-- 用户授权/认证信息Cache, 采用 Redis 缓存 -->
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(redisCacheManager());
        return securityManager;
    }
    
    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
}
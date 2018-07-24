package cn.org.cpcca.feignserver.paper.shiro.configuration;


import cn.org.cpcca.feignserver.paper.shiro.filter.CustomRolesAuthorizationFilter;
import cn.org.cpcca.feignserver.paper.shiro.realms.TrainRealm;
import cn.org.cpcca.feignserver.paper.shiro.realms.VerifyUriRealm;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

//    @Bean(name = "verifyUriRealm")
//    @DependsOn("lifecycleBeanPostProcessor")
//    public VerifyUriRealm myAuthRealm() {
//        VerifyUriRealm verifyUriRealm = new VerifyUriRealm();
//        return verifyUriRealm;
//    }
//
//    @Bean(name = "securityManager")
//    @DependsOn("lifecycleBeanPostProcessor")
//    public DefaultWebSecurityManager securityManager(@Qualifier("verifyUriRealm")VerifyUriRealm myRealm) {
//        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
//        manager.setRealm(myRealm);
//        return manager;
//    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public VerifyUriRealm verifyUriRealm() {
        VerifyUriRealm verifyUriRealm = new VerifyUriRealm();
        return verifyUriRealm;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public TrainRealm trainRealm() {
        TrainRealm trainRealm = new TrainRealm();
        return trainRealm;
    }

    @Bean(value = "securityManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        // 配置Realm-->多Realm配置
        securityManager.setAuthenticator(modularRealmAuthenticator());
        List<Realm> realms = new ArrayList<>();
        realms.add(verifyUriRealm());
        realms.add(trainRealm());
        securityManager.setRealms(realms);
        return securityManager;
    }


    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        CustomRolesAuthorizationFilter corroles = new CustomRolesAuthorizationFilter();
        filters.put("corroles",corroles);
        shiroFilterFactoryBean.setFilters(filters);
//        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
//        LogoutFilter logoutFilter = new LogoutFilter();
//        logoutFilter.setRedirectUrl("/login");
//        filters.put("logout", logoutFilter);
//        shiroFilterFactoryBean.setFilters(filters);
       /* System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<String, String>();

        filterChainDefinitionManager.put("/test/**", "authc,roles[admin],perms[region]");
        filterChainDefinitionManager.put("/index", "anon");*/
        /*filterChainDefinitionManager.put("/logout", "logout");
        filterChainDefinitionManager.put("/user/**", "authc,roles[user]");
        filterChainDefinitionManager.put("/shop/**", "authc,roles[shop]");
        filterChainDefinitionManager.put("/admin/**", "authc,roles[admin]");
        filterChainDefinitionManager.put("/index", "anon");//anon 可以理解为不拦截
        filterChainDefinitionManager.put("/ajaxLogin", "anon");//anon 可以理解为不拦截
        filterChainDefinitionManager.put("/statistic/**",  "anon");//静态资源不拦截
        filterChainDefinitionManager.put("/**",  "authc,roles[user]");//其他资源全部拦截*/

       /*shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);
        shiroFilterFactoryBean.setLoginUrl("/log");*/
        /*
        shiroFilterFactoryBean.setLoginUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");*/

        shiroFilterFactoryBean.setUnauthorizedUrl("/shiro/error");
        shiroFilterFactoryBean.setLoginUrl("/shiro/error");

        return shiroFilterFactoryBean;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
//        aasa.setSecurityManager(securityManager);
//        return aasa;
//    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    /**
     * 系统自带的Realm管理，主要针对多realm
     * */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        //自己重写的ModularRealmAuthenticator
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

}

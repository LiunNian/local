package cn.org.cpcca.paperserver.configuration;

import cn.org.cpcca.paperserver.shiro.realms.VerifyUriRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ShiroConfiguration {
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean(name = "verifyUriRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public VerifyUriRealm myAuthRealm() {
        VerifyUriRealm verifyUriRealm = new VerifyUriRealm();
        return verifyUriRealm;
    }

    @Bean(name = "securityManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultWebSecurityManager securityManager(@Qualifier("verifyUriRealm")VerifyUriRealm myRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm);
        return manager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager  securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
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
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");*/
        shiroFilterFactoryBean.setUnauthorizedUrl("/log");
        shiroFilterFactoryBean.setLoginUrl("/log");
        return shiroFilterFactoryBean;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

}

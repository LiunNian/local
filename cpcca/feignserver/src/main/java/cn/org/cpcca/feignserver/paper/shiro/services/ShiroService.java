package cn.org.cpcca.feignserver.paper.shiro.services;

import cn.org.cpcca.feignserver.paper.shiro.models.ResourcesModel;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("shiroService")
public class ShiroService {
    @Resource
    ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    private ResourcesServiceInterface resourcesService;

    /**
     * 初始化权限
     */
    public Map<String, String> loadFilterChainDefinitions() {
        //shiroFilterFactoryBean.setUnauthorizedUrl("/shiro/error");
        //shiroFilterFactoryBean.setLoginUrl("/shiro/error");
        // 权限控制map.从数据库获取
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        List<ResourcesModel> list = resourcesService.getAll();
        filterChainDefinitionMap.put("/role/refresh", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/**/login", "anon");
        filterChainDefinitionMap.put("/**/logout", "anon");
        filterChainDefinitionMap.put("/**/error", "anon");
        filterChainDefinitionMap.put("/**/regist", "anon");
        filterChainDefinitionMap.put("/**/call", "anon");
        filterChainDefinitionMap.put("/**/verification", "anon");
        filterChainDefinitionMap.put("/**/reset", "anon");

        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        for (ResourcesModel resourcesModel : list) {
            if(filterMap.containsKey(resourcesModel.getUrl())){
                filterMap.put(resourcesModel.getUrl(),filterMap.get(resourcesModel.getUrl())+","+resourcesModel.getRoles());
            }else{
                filterMap.put(resourcesModel.getUrl(),resourcesModel.getRoles());
            }
        }
        for (Map.Entry<String,String> entry : filterMap.entrySet()) {
            filterChainDefinitionMap.put(entry.getKey(),"authc,corroles["+entry.getValue()+"]");//+":"+resourcesModel.getPermissions()+"
        }
        System.out.println(filterChainDefinitionMap);
        return filterChainDefinitionMap;
    }
    /**
     * 重新加载权限
     */
    public void updatePermission() {

        synchronized (shiroFilterFactoryBean) {

            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }

            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
            //System.out.println(manager.getFilterChains().toString());
            System.out.println("更新权限成功！！");
        }
    }
}

package cn.org.cpcca.feignserver.paper.shiro.services;


import cn.org.cpcca.feignserver.paper.shiro.models.AccountModel;

import java.util.List;

public interface AccountServiceInterface {
    List<AccountModel> listUser();
    List<AccountModel> listUser(List<Integer> ids);
    List<Integer> listUserId(String role);
    AccountModel getNameByInfo(String username);
    int updatePassword(String password, String userModel);
   /* List<String> getRoles(int id);
    List<Integer> getRoleIds(int id);
    List<Integer> getPermissionIds(List<Integer> rid);
    List<String> getPermissions(List<Integer> pid);*/
    int updateFlag(int uid);
    AccountModel selectUser(int uid);
    List<String> getPlatforms(int uid);
    List<Integer> getPlid(int uid);
    List<String> getsRoleByUid(int uid);
}

package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.models.UserModel;

import java.util.List;
import java.util.Map;

public interface UserServiceInterface {
    List<UserModel> listUser();
    List<UserModel> listUser(List<Integer> ids);
    List<Integer> listUserId(String role);
    UserModel getNameByInfo(String username);
    int updatePassword(String password,String userModel);
    List<String> getRoles(int id);
    List<Integer> getRoleIds(int id);
    List<Map<String,Object>> getRoleNameByIds(int rid);
    List<Integer> getPermissionIds(List<Integer> rid);
    List<String> getPermissions(List<Integer> pid);
    int updateFlag(int uid);
    UserModel selectUser(int uid);
}

package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.mappers.UserMapper;
import cn.org.cpcca.paperserver.models.UserModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserServiceInterface {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserModel> listUser() {
        return userMapper.listUsers();
    }

    @Override
    public List<UserModel> listUser(List<Integer> ids) {
        return userMapper.listUser(ids);
    }

    @Override
    public List<Integer> listUserId(String role) {
        return userMapper.listUserId(role);
    }

    @Override
    public UserModel getNameByInfo(String username) {
        return userMapper.getNameByInfo(username);
    }

    @Override
    public int updatePassword(String password,String username) {
        return userMapper.updatePassword(password,username);
    }

    @Override
    public List<String> getRoles(int uid) {
        return userMapper.getRoles(uid);
    }

    @Override
    public List<Integer> getRoleIds(int uid) {
        return userMapper.getRoleIds(uid);
    }

    @Override
    public List<Map<String,Object>> getRoleNameByIds(int rid) {
        return userMapper.getRoleNameByIds(rid);
    }

    @Override
    public List<Integer> getPermissionIds(List<Integer> rid) {
        return userMapper.getPermissionIds(rid);
    }

    @Override
    public List<String> getPermissions(List<Integer> pid) {
        return userMapper.getPermissions(pid);
    }

    @Override
    public int updateFlag(int uid) {
        return userMapper.updateFlag(uid);
    }

    @Override
    public UserModel selectUser(int uid) {
        return userMapper.selectUser(uid);
    }
}

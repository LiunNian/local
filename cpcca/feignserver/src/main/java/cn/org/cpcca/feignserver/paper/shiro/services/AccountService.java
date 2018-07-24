package cn.org.cpcca.feignserver.paper.shiro.services;

import cn.org.cpcca.feignserver.paper.shiro.mappers.AccountMapper;
import cn.org.cpcca.feignserver.paper.shiro.models.AccountModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountService implements AccountServiceInterface {
    @Resource
    private AccountMapper userMapper;

    @Override
    public List<AccountModel> listUser() {
        return userMapper.listUsers();
    }

    @Override
    public List<AccountModel> listUser(List<Integer> ids) {
        return userMapper.listUser(ids);
    }

    @Override
    public List<Integer> listUserId(String role) {
        return userMapper.listUserId(role);
    }

    @Override
    public AccountModel getNameByInfo(String username) {
        return userMapper.getNameByInfo(username);
    }

    @Override
    public int updatePassword(String password,String username) {
        return userMapper.updatePassword(password,username);
    }

   /* @Override
    public List<String> getRoles(int uid) {
        return userMapper.getRoles(uid);
    }

    @Override
    public List<Integer> getRoleIds(int uid) {
        return userMapper.getRoleIds(uid);
    }

    @Override
    public List<Integer> getPermissionIds(List<Integer> rid) {
        return userMapper.getPermissionIds(rid);
    }

    @Override
    public List<String> getPermissions(List<Integer> pid) {
        return userMapper.getPermissions(pid);
    }
*/
    @Override
    public int updateFlag(int uid) {
        return userMapper.updateFlag(uid);
    }

    @Override
    public AccountModel selectUser(int uid) {
        return userMapper.selectUser(uid);
    }

    @Override
    public List<String> getPlatforms(int uid) {
        return userMapper.getPlatforms(uid);
    }

    @Override
    public List<Integer> getPlid(int uid) {
        return userMapper.getPlid(uid);
    }

    @Override
    public List<String> getsRoleByUid(int uid) {
        return userMapper.getsRoleByUid(uid);
    }

}

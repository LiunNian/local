package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.mappers.AuthorMapper;
import cn.org.cpcca.paperserver.models.AuthorModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthorService implements  AuthorServiceInterface {
    @Resource
    private AuthorMapper authorMapper;
    @Override
    public int addAuthor(AuthorModel authorModel) {
        authorMapper.addAuthor(authorModel);
        return authorModel.getId();
    }

    @Override
    public List<AuthorModel> listAuthor(List<Integer> auids) {
        return authorMapper.listAuthor(auids);
    }
}

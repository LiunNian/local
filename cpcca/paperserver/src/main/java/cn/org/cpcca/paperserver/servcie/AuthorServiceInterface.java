package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.models.AuthorModel;

import java.util.List;

public interface AuthorServiceInterface {
    int addAuthor(AuthorModel authorModel);
    List<AuthorModel> listAuthor(List<Integer> auids);
}

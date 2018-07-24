package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.models.NewsModel;
import cn.org.cpcca.paperserver.models.NewsTypeModel;

import java.util.List;
import java.util.Map;

public interface NewsServiceInterface {
    int addNews(NewsModel newsModel);
    NewsModel selectNews(int id);
    List<NewsModel> listNews(int type);
    int updateNews(NewsModel newsModel);
    int updateState(Map<String,Object> newsMap);
    int deleteNews(List<Integer> nids);
    List<NewsTypeModel> newsType();
    String getTypeName(int id);
    List<NewsModel> listNewsBin(int type);
}

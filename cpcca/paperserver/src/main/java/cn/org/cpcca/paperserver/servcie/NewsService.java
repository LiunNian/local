package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.mappers.NewsMapper;
import cn.org.cpcca.paperserver.mappers.NewsTypeMapper;
import cn.org.cpcca.paperserver.models.NewsModel;
import cn.org.cpcca.paperserver.models.NewsTypeModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class NewsService implements NewsServiceInterface {
    @Resource
    private NewsMapper newsMapper;
    @Resource
    private NewsTypeMapper newsTypeMapper;
    @Override
    public int addNews(NewsModel newsModel) {
        newsMapper.addNews(newsModel);
        System.out.println(newsModel.getId());
        return newsModel.getId();
    }

    @Override
    public NewsModel selectNews(int id) {
        return newsMapper.selectNews(id);
    }

    @Override
    public List<NewsModel> listNews(int type) {
        return newsMapper.listNews(type);
    }

    @Override
    public int updateNews(NewsModel newsModel) {
        return newsMapper.updateNews(newsModel);
    }

    @Override
    public int updateState(Map<String, Object> newsMap) {
        return newsMapper.updateState(newsMap);
    }

    @Override
    public int deleteNews(List<Integer> nids) {
        return newsMapper.deleteNews(nids);
    }

    @Override
    public List<NewsTypeModel> newsType() {
        return newsTypeMapper.getType();
    }

    @Override
    public String getTypeName(int id) {
        return newsTypeMapper.getTypeName(id);
    }

    @Override
    public List<NewsModel> listNewsBin(int type) {
        return newsMapper.listNewsModelBin(type);
    }
}

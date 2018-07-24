package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.mappers.ThemeMapper;
import cn.org.cpcca.paperserver.models.ThemeModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ThemeService implements ThemeServiceInterface {
    @Resource
    private ThemeMapper themeMapper;
    @Override
    public int addTheme(ThemeModel themeModel){
        themeMapper.addTheme(themeModel);
        return themeModel.getId();
    }

    @Override
    public ThemeModel selectTheme(int id) {
        return themeMapper.selectTheme(id);
    }

    @Override
    public List<ThemeModel> listTheme(int itid){
       return themeMapper.listTheme(itid);
    }

    @Override
    public int updateTheme(ThemeModel themeModel) {
        return themeMapper.updateTheme(themeModel);
    }

    @Override
    public int updateState(Map<String, Object> themeMap) {
        return themeMapper.updateState(themeMap);
    }

    @Override
    public int deleteTheme(List<Integer> thids) {
        return themeMapper.deleteTheme(thids);
    }

    @Override
    public List<ThemeModel> listThemeBin(int itid) {
        return themeMapper.listThemeBin(itid);
    }
}

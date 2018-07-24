package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.models.ThemeModel;

import java.util.List;
import java.util.Map;

public interface ThemeServiceInterface {
    int addTheme(ThemeModel themeModel);
    ThemeModel selectTheme(int id);
    List<ThemeModel> listTheme(int itid);
    int updateTheme(ThemeModel themeModel);
    int updateState(Map<String,Object> themeMap);
    int deleteTheme(List<Integer> thids);
    List<ThemeModel> listThemeBin(int itid);
}

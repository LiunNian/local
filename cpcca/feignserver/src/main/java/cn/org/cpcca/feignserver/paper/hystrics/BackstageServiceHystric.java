package cn.org.cpcca.feignserver.paper.hystrics;

import cn.org.cpcca.feignserver.paper.services.BackstageService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BackstageServiceHystric extends DefaultHystric implements BackstageService {
    @Override
    public String login(String username, String password) {
        return this.defaultZone();
    }

    @Override
    public String logout() {
        return this.defaultZone();
    }

    @Override
    public String listUser() {
        return this.defaultZone();
    }

    @Override
    public String listItemType() {
        return this.defaultZone();
    }

    @Override
    public String addItem(int uid, String fuid, String stid, String title, String description, String instruction) {
        return this.defaultZone();
    }

    @Override
    public String selectItem(String id) {
        return this.defaultZone();
    }

    @Override
    public String updateItem(Map<String, Object> itemMap) {
        return this.defaultZone();
    }

    @Override
    public String updateItemState(Map<String, Object> itemMap) {
        return this.defaultZone();
    }

    @Override
    public String deleteItem(List<Integer> itids) {
        return this.defaultZone();
    }

    @Override
    public String listItem(String page,String limit) {
        return this.defaultZone();
    }

    @Override
    public String listItemBin(String page, String limit) {
        return this.defaultZone();
    }

    @Override
    public String addTheme(Map<String, Object> themeMap) {
        return this.defaultZone();
    }

    @Override
    public String selectTheme(String id) {
        return this.defaultZone();
    }

    @Override
    public String listTheme(String thids,String page,String limit) {
        return this.defaultZone();
    }

    @Override
    public String listThemeBin(String thids, String page, String limit) {
        return this.defaultZone();
    }


    @Override
    public String updateTheme(Map<String, Object> themeMap) {
        return this.defaultZone();
    }

    @Override
    public String updateThemeState(Map<String, Object> themeMap) {
        return this.defaultZone();
    }

    @Override
    public String deleteTheme(List<Integer> thids) {
        return this.defaultZone();
    }


    @Override
    public String addDirection(String thid, String title, String description) {
        return this.defaultZone();
    }

    @Override
    public String selectDirection(String id) {
        return this.defaultZone();
    }

    @Override
    public String listDirection(String thid,String page,String limit) {
        return this.defaultZone();
    }

    @Override
    public String updateDirection(Map<String, Object> directionMap) {
        return this.defaultZone();
    }

    @Override
    public String updateDirecitonState(Map<String, Object> directionMap) {
        return this.defaultZone();
    }

    @Override
    public String deleteDirection(List<Integer> dids) {
        return this.defaultZone();
    }

    @Override
    public String getNewsType() {
        return this.defaultZone();
    }

    @Override
    public String addNews(Map<String, Object> newsMap) {
        return this.defaultZone();
    }

    @Override
    public String selectNews(int id) {
        return this.defaultZone();
    }

    @Override
    public String listNews(String type, String page, String limit) {
        return this.defaultZone();
    }

    @Override
    public String listNewsBin(String type, String page, String limit) {
        return this.defaultZone();
    }

    @Override
    public String updateNews(Map<String, Object> newsMap) {
        return this.defaultZone();
    }

    @Override
    public String updataNewsState(Map<String, Object> newsMap) {
        return this.defaultZone();
    }

    @Override
    public String deleteNews(List<Integer> nids) {
        return this.defaultZone();
    }

    @Override
    public String addStatement(Map<String, Object> statementMap) {
        return this.defaultZone();
    }

    @Override
    public String selectStatement(int id) {
        return this.defaultZone();
    }

    @Override
    public String listStatement(String page, String limit) {
        return this.defaultZone();
    }

    @Override
    public String listStatementAll() {
        return this.defaultZone();
    }

    @Override
    public String getStatements(List<Integer> stids) {
        return this.defaultZone();
    }

    @Override
    public String listStatementBin(String page, String limit) {
        return this.defaultZone();
    }

    @Override
    public String updateStatement(Map<String, Object> statementMap) {
        return this.defaultZone();
    }

    @Override
    public String updataStatementState(Map<String, Object> statementMap) {
        return this.defaultZone();
    }

    @Override
    public String deleteStatement(List<Integer> nids) {
        return this.defaultZone();
    }

}

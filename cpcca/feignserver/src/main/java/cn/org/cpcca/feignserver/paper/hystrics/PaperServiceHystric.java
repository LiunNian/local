package cn.org.cpcca.feignserver.paper.hystrics;

import cn.org.cpcca.feignserver.paper.services.PaperService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Component
public class PaperServiceHystric extends DefaultHystric implements PaperService {
    /*@Override
    public String login(HttpServletRequest request,String username, String password) {
        return this.defaultZone();
    }
*/
    @Override
    public String login(String username, String password) {
        return this.defaultZone();
    }

    @Override
    public String logout() {
        return this.defaultZone();
    }

    @Override
    public String listItems(String page, String limit) {
        return this.defaultZone();
    }

    @Override
    public String listItemEnd(String page, String limit) {
        return this.defaultZone();
    }

    @Override
    public String getThemes(String itid) {
        return this.defaultZone();
    }


    @Override
    public String addAuthor(String name, int sex, String cardnum, String phonenum, String unitname, String deptname, String region) {
        return this.defaultZone();
    }
    @Override
    public String getAuthors(Map<String, List<Integer>> authorMap) {
        System.out.println("getAuthors error");return this.defaultZone();
    }

    @Override
    public String selectItem(int id) {
        return this.defaultZone();
    }

    @Override
    public String upload(MultipartFile file) {
        return this.defaultZone();
    }

    @Override
    public String addPaper(Map<String, Object> paperMap) {
        return this.defaultZone();
    }

    @Override
    public String listpaper(int uid, int page, int limit) {
        return this.defaultZone();
    }

    @Override
    public String listpaperByItid(int uid,int itid, int page, int limit) {
        return this.defaultZone();
    }

    @Override
    public String selectPaper(int id) {
        return this.defaultZone();
    }

    @Override
    public String updatepaper(Map<String, Object> paperMap) {
        return this.defaultZone();
    }

    @Override
    public String stampPaper(String id, String sign) {
        return this.defaultZone();
    }

    @Override
    public String getUserInfo(int uid)  {
        return this.defaultZone();
    }

    @Override
    public String updateUserInfo( Map<String, Object> userInfoMap) {
        return this.defaultZone();
    }

    @Override
    public String listNews(String type, String page, String limit) {
        return this.defaultZone();
    }

    @Override
    public String selectNews(int id) {
        return this.defaultZone();
    }

    @Override
    public String getFileInfo(int id) {
        return this.defaultZone();
    }

    @Override
    public String searchStatement(int id) {
        return this.defaultZone();
    }

    @Override
    public String getStatements(List<Integer> stids) {
        return this.defaultZone();
    }

    @Override
    public String getFiles(List<Integer> ids) {
        return this.defaultZone();
    }

    @Override
    public String getItem(int id) {
        return this.defaultZone();
    }
}

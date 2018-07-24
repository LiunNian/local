package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.models.StatementModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatementServiceInterface {
    int addStatement(StatementModel statementModel);
    StatementModel selectStatement(int id);
    int updateStatement(StatementModel statementModel);
    List<StatementModel> listStatement();
    List<StatementModel> listStatementAll();
    int updateState(Map<String,Object> statementMap);
    int deleteStatement(List<Integer> ids);
    List<StatementModel> listStatementBin();
    List<StatementModel> getStatementById(List<Integer> stids);
}

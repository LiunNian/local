package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.mappers.StatementMapper;
import cn.org.cpcca.paperserver.models.StatementModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
public class StatementService implements StatementServiceInterface{

    @Resource
    private StatementMapper statementMapper;
    @Override
    public int addStatement(StatementModel statementModel) {
        statementMapper.addStatement(statementModel);
        return statementModel.getId();
    }

    @Override
    public StatementModel selectStatement(int id) {
        return statementMapper.selectStatement(id);
    }

    @Override
    public int updateStatement(StatementModel statementModel) {
        return statementMapper.updateStatement(statementModel);
    }

    @Override
    public List<StatementModel> listStatement() {
        return statementMapper.listStatement();
    }

    @Override
    public List<StatementModel> listStatementAll() {
        return statementMapper.listStatementAll();
    }

    @Override
    public int updateState(Map<String, Object> statementMap) {
        return statementMapper.updateState(statementMap);
    }

    @Override
    public int deleteStatement(List<Integer> ids) {
        return statementMapper.deleteStatement(ids);
    }

    @Override
    public List<StatementModel> listStatementBin() {
        return statementMapper.listStatementBin();
    }

    @Override
    public List<StatementModel> getStatementById(List<Integer> stids) {
        return statementMapper.getStatementById(stids);
    }
}

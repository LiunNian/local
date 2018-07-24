package cn.org.cpcca.paperserver.mappers;

import	cn.org.cpcca.paperserver.models.ReviewPaperBean;
import cn.org.cpcca.paperserver.models.ReviewPaperModel;
import cn.org.cpcca.paperserver.models.ReviewProgressModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ReviewPaperDao{

	public int insertRecord(ReviewPaperBean record);//添加一条完整记录

	public int insertSelective(ReviewPaperBean record);//添加指定列的数据

	public int deleteById(Integer revpid);//通过Id(主键)删除一条记录

	public int deleteByRevId(Integer revid);//通过revid(评审关联)删除记录

	public int updateByIdSelective(ReviewPaperBean record);//按Id(主键)修改指定列的值

	public int updateById(ReviewPaperBean record);//按Id(主键)修改指定列的值

	public int countRecord();//计算表中的总记录数

	public int countSelective(ReviewPaperBean record);//根据条件计算记录条数

	public int maxId();//获得表中的最大Id

	public	ReviewPaperBean	selectById(Integer revpid);//通过Id(主键)查询一条记录

	public List selectAll();//查询所有记录

	public List searchPapersByRevid(Integer revid); //通过评审项目id查询

	public List<ReviewPaperModel> searchPapersByDirection(Map<String,Object> queryParam); //通过方向查询

	public List searchPapersByKeyword(Map<String,Object> queryMap); //通过关键词查询


	public List getReviewPapersList(Map<String,Object> queryMap);//查询评审论文列表

	public List getReviewPapersAll(Map<String,Object> queryMap);//查询评审论列表

	public List getReviewPapersListByUid(Integer uid);//查询评审论文revpid 和项目revid表

	public List<ReviewProgressModel> reviewProgress(int revid); //进度数据

	List<Integer> getItidsByUid(int uid); //通过uid获取当前专家对应论文活动项目

	List<String> groupByProgress(int revid);//通过revid查询progress列表

	List<String> groupByResult(int revid);//通过revid查询result列表
}
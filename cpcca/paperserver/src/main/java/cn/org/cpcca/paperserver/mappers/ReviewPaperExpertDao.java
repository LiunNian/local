package cn.org.cpcca.paperserver.mappers;

import	cn.org.cpcca.paperserver.models.ReviewPaperExpertBean;
import cn.org.cpcca.paperserver.models.ReviewResultModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ReviewPaperExpertDao{

	public int insertRecord(ReviewPaperExpertBean record);//添加一条完整记录

	public int insertSelective(ReviewPaperExpertBean record);//添加指定列的数据

	public int deleteById(Integer pexid);//通过Id(主键)删除一条记录

	public int deleteByRevpid(Map<String,List<Integer>> params);

	public int updateByIdSelective(ReviewPaperExpertBean record);//按Id(主键)修改指定列的值

	public int updateById(ReviewPaperExpertBean record);//按Id(主键)修改指定列的值

	public int countRecord();//计算表中的总记录数

	public int countSelective(ReviewPaperExpertBean record);//根据条件计算记录条数

	public int maxId();//获得表中的最大Id

	public	List<Integer> selectUidByRevpid(List<Integer> revpid);//通过revpId(主键)查询专家列表

	public	ReviewPaperExpertBean	selectById(Integer pexid);//通过Id(主键)查询一条记录

	public List selectAll();//查询所有记录

	public List selectByRevpid(int revpid);//通过评审论文id查询用户信息

	public ReviewPaperExpertBean selectByRevpidUid(Map<String,Object> queryParam); //通过评审论文id和专家查询平分和评语

	public List<ReviewResultModel> reviewResult(int revid); //获取结果数据

}
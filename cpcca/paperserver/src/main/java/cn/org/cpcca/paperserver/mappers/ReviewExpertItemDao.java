package cn.org.cpcca.paperserver.mappers;

import	cn.org.cpcca.paperserver.models.ReviewExpertItemBean;
import cn.org.cpcca.paperserver.models.ReviewExpertModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ReviewExpertItemDao{

	public int insertRecord(ReviewExpertItemBean record);//添加一条完整记录

	public int insertSelective(ReviewExpertItemBean record);//添加指定列的数据

	public int deleteById(Integer exitid);//通过Id(主键)删除一条记录

	public int  deleteByRevId(Integer revid);//通过revid(评审关联)删除记录

	public int updateByIdSelective(ReviewExpertItemBean record);//按Id(主键)修改指定列的值

	public int updateById(ReviewExpertItemBean record);//按Id(主键)修改指定列的值

	public int countRecord();//计算表中的总记录数

	public int countSelective(ReviewExpertItemBean record);//根据条件计算记录条数

	public int maxId();//获得表中的最大Id

	public	ReviewExpertItemBean	selectById(Integer exitid);//通过Id(主键)查询一条记录

	public	List<ReviewExpertModel> selectByRevid(Integer revid);//通过revid(评审关联)查询一条记录

	public List selectAll();//查询所有记录


}
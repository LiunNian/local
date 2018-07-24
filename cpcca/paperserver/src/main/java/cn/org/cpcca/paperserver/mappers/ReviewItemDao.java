package cn.org.cpcca.paperserver.mappers;

import	cn.org.cpcca.paperserver.models.ReviewItemBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface ReviewItemDao{

	public int insertRecord(ReviewItemBean record);//添加一条完整记录

	public int insertSelective(ReviewItemBean record);//添加指定列的数据

	public int deleteById(Integer revid);//通过Id(主键)删除一条记录

	public int updateByIdSelective(ReviewItemBean record);//按Id(主键)修改指定列的值

	public int updateById(ReviewItemBean record);//按Id(主键)修改指定列的值

	public int countRecord();//计算表中的总记录数

	public int countSelective(ReviewItemBean record);//根据条件计算记录条数

	public int maxId();//获得表中的最大Id

	public	ReviewItemBean	selectById(Integer revid);//通过Id(主键)查询一条记录
	public List selectByItid(Integer itid);
	public List selectAll();//查询所有记录


}
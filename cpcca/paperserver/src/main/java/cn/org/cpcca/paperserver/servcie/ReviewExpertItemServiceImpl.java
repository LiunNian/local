package cn.org.cpcca.paperserver.servcie;


import cn.org.cpcca.paperserver.models.ReviewExpertItemBean;

import cn.org.cpcca.paperserver.mappers.ReviewExpertItemDao;
import cn.org.cpcca.paperserver.models.ReviewExpertModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReviewExpertItemServiceImpl	implements ReviewExpertItemService{
	@Resource
	private ReviewExpertItemDao	reviewExpertItemDao;

	public ReviewExpertItemDao getReviewExpertItemDao(){
		return	reviewExpertItemDao;
	}

	public ReviewExpertItemDao setReviewExpertItemDao(ReviewExpertItemDao reviewExpertItemDao){
		return this.reviewExpertItemDao=reviewExpertItemDao;
	}

	//添加一条完整记录
	public int insertRecord(ReviewExpertItemBean record){
		return	reviewExpertItemDao.insertRecord(record);
	}

	//添加指定列的数据
	public int insertSelective(ReviewExpertItemBean record){
		return	reviewExpertItemDao.insertSelective(record);
	}

	//通过Id(主键)删除一条记录
	public int deleteById(Integer exitid){
		return	reviewExpertItemDao.deleteById(exitid);
	}

	@Override
	public int deleteByRevId(Integer revid) {
		return reviewExpertItemDao.deleteByRevId(revid);
	}

	//按Id(主键)修改指定列的值
	public int updateByIdSelective(ReviewExpertItemBean record){
		return	reviewExpertItemDao.updateByIdSelective(record);
	}

	//按Id(主键)修改指定列的值
	public int updateById(ReviewExpertItemBean record){
		return	reviewExpertItemDao.updateById(record);
	}

	//计算表中的总记录数
	public int countRecord(){
		return	reviewExpertItemDao.countRecord();
	}

	//根据条件计算记录条数
	public int countSelective(ReviewExpertItemBean record){
		return	reviewExpertItemDao.countSelective(record);
	}

	//获得表中的最大Id
	public int maxId(){
		return	reviewExpertItemDao.maxId();
	}

	//通过Id(主键)查询一条记录
	public	ReviewExpertItemBean	selectById(Integer	exitid){
		return	reviewExpertItemDao.selectById(exitid);
	}

	@Override
	public List<ReviewExpertModel> selectByRevid(Integer revid) {
		return reviewExpertItemDao.selectByRevid(revid) ;
	}

	//查询所有记录
	public List selectAll(){
		return	reviewExpertItemDao.selectAll();
	}


}
package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.servcie.ReviewItemService;

import cn.org.cpcca.paperserver.models.ReviewItemBean;

import cn.org.cpcca.paperserver.mappers.ReviewItemDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ReviewItemServiceImpl	implements ReviewItemService{
	@Resource
	private ReviewItemDao	reviewItemDao;

	public ReviewItemDao getReviewItemDao(){
		return	reviewItemDao;
	}

	public ReviewItemDao setReviewItemDao(ReviewItemDao reviewItemDao){
		return this.reviewItemDao=reviewItemDao;
	}

	//添加一条完整记录
	public int insertRecord(ReviewItemBean record){
		return	reviewItemDao.insertRecord(record);
	}

	//添加指定列的数据
	public int insertSelective(ReviewItemBean record){
		reviewItemDao.insertSelective(record);
		return record.getRevid();
	}

	//通过Id(主键)删除一条记录
	public int deleteById(Integer revid){
		return	reviewItemDao.deleteById(revid);
	}

	//按Id(主键)修改指定列的值
	public int updateByIdSelective(ReviewItemBean record){
		return	reviewItemDao.updateByIdSelective(record);
	}

	//按Id(主键)修改指定列的值
	public int updateById(ReviewItemBean record){
		return	reviewItemDao.updateById(record);
	}

	//计算表中的总记录数
	public int countRecord(){
		return	reviewItemDao.countRecord();
	}

	//根据条件计算记录条数
	public int countSelective(ReviewItemBean record){
		return	reviewItemDao.countSelective(record);
	}

	//获得表中的最大Id
	public int maxId(){
		return	reviewItemDao.maxId();
	}

	//通过Id(主键)查询一条记录
	public	ReviewItemBean	selectById(Integer	revid){
		return	reviewItemDao.selectById(revid);
	}

	@Override
	public List selectByItid(Integer itid) {
		return	reviewItemDao.selectByItid(itid) ;
	}

	//查询所有记录
	public List selectAll(){
		return	reviewItemDao.selectAll();
	}


}
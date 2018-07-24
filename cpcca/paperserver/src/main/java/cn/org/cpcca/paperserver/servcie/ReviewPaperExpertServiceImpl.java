package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.models.ReviewResultModel;
import cn.org.cpcca.paperserver.servcie.ReviewPaperExpertService;

import cn.org.cpcca.paperserver.models.ReviewPaperExpertBean;

import cn.org.cpcca.paperserver.mappers.ReviewPaperExpertDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ReviewPaperExpertServiceImpl	implements ReviewPaperExpertService{
	@Resource
	private ReviewPaperExpertDao	reviewPaperExpertDao;

	public ReviewPaperExpertDao getReviewPaperExpertDao(){
		return	reviewPaperExpertDao;
	}

	public ReviewPaperExpertDao setReviewPaperExpertDao(ReviewPaperExpertDao reviewPaperExpertDao){
		return this.reviewPaperExpertDao=reviewPaperExpertDao;
	}

	//添加一条完整记录
	public int insertRecord(ReviewPaperExpertBean record){
		return	reviewPaperExpertDao.insertRecord(record);
	}

	//添加指定列的数据
	public int insertSelective(ReviewPaperExpertBean record){
		return	reviewPaperExpertDao.insertSelective(record);
	}

	//通过Id(主键)删除一条记录
	public int deleteById(Integer pexid){
		return	reviewPaperExpertDao.deleteById(pexid);
	}

	@Override
	public int deleteByRevpid(Map<String,List<Integer>> params) {
		return reviewPaperExpertDao.deleteByRevpid(params);
	}

	//按Id(主键)修改指定列的值
	public int updateByIdSelective(ReviewPaperExpertBean record){
		return	reviewPaperExpertDao.updateByIdSelective(record);
	}

	//按Id(主键)修改指定列的值
	public int updateById(ReviewPaperExpertBean record){
		return	reviewPaperExpertDao.updateById(record);
	}

	//计算表中的总记录数
	public int countRecord(){
		return	reviewPaperExpertDao.countRecord();
	}

	//根据条件计算记录条数
	public int countSelective(ReviewPaperExpertBean record){
		return	reviewPaperExpertDao.countSelective(record);
	}

	//获得表中的最大Id
	public int maxId(){
		return	reviewPaperExpertDao.maxId();
	}

	//通过Id(主键)查询一条记录
	public	ReviewPaperExpertBean	selectById(Integer	pexid){
		return	reviewPaperExpertDao.selectById(pexid);
	}

	//查询所有记录
	public List selectAll(){
		return	reviewPaperExpertDao.selectAll();
	}

	@Override
	public List selectByRevpid(int revpid) {
		return reviewPaperExpertDao.selectByRevpid(revpid);
	}

	@Override
	public List<Integer> selectUidByRevpid(List<Integer> revpid) {
		return reviewPaperExpertDao.selectUidByRevpid(revpid);
	}

	@Override
	public ReviewPaperExpertBean selectByRevpidUid(Map<String, Object> queryParam) {
		return reviewPaperExpertDao.selectByRevpidUid(queryParam);
	}

	@Override
	public List<ReviewResultModel> reviewResult(int revid) {
		return reviewPaperExpertDao.reviewResult(revid);
	}
}
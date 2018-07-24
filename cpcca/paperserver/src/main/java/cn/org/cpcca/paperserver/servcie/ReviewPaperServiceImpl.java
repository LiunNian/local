package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.models.ReviewPaperModel;
import cn.org.cpcca.paperserver.models.ReviewProgressModel;
import cn.org.cpcca.paperserver.servcie.ReviewPaperService;

import cn.org.cpcca.paperserver.models.ReviewPaperBean;

import cn.org.cpcca.paperserver.mappers.ReviewPaperDao;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ReviewPaperServiceImpl	implements ReviewPaperService{
	@Resource
	private ReviewPaperDao reviewPaperDao;

	public ReviewPaperDao getReviewPaperDao(){
		return	reviewPaperDao;
	}

	public ReviewPaperDao setReviewPaperDao(ReviewPaperDao 	reviewPaperDao){
		return this.reviewPaperDao=	reviewPaperDao;
	}

	//添加一条完整记录
	public int insertRecord(ReviewPaperBean record){
		return	reviewPaperDao.insertRecord(record);
	}

	//添加指定列的数据
	public int insertSelective(ReviewPaperBean record){
		return	reviewPaperDao.insertSelective(record);
	}

	//通过Id(主键)删除一条记录
	public int deleteById(Integer revpid){
		return	reviewPaperDao.deleteById(revpid);
	}

	@Override
	public int deleteByRevId(Integer revid) {
		return reviewPaperDao.deleteByRevId(revid);
	}

	//按Id(主键)修改指定列的值
	public int updateByIdSelective(ReviewPaperBean record){
		return	reviewPaperDao.updateByIdSelective(record);
	}

	//按Id(主键)修改指定列的值
	public int updateById(ReviewPaperBean record){
		return	reviewPaperDao.updateById(record);
	}

	//计算表中的总记录数
	public int countRecord(){
		return	reviewPaperDao.countRecord();
	}

	//根据条件计算记录条数
	public int countSelective(ReviewPaperBean record){
		return	reviewPaperDao.countSelective(record);
	}

	//获得表中的最大Id
	public int maxId(){
		return	reviewPaperDao.maxId();
	}

	//通过Id(主键)查询一条记录
	public	ReviewPaperBean	selectById(Integer	revpid){
		return	reviewPaperDao.selectById(revpid);
	}

	//查询所有记录
	public List selectAll(){
		return	reviewPaperDao.selectAll();
	}

	@Override
	public List searchPapersByRevid(Integer revid) {
		return reviewPaperDao.searchPapersByRevid(revid);
	}

	@Override
	public List<ReviewPaperModel> searchPapersByDirection(Map<String,Object> queryParam) {
		return reviewPaperDao.searchPapersByDirection(queryParam);
	}

	@Override
	public List searchPapersByKeyword(Map<String,Object> queryMap) {
		return reviewPaperDao.searchPapersByKeyword(queryMap);
	}

	@Override
	public List getReviewPapersList(Map<String, Object> queryMap) {
		return reviewPaperDao.getReviewPapersList(queryMap);
	}

	@Override
	public List getReviewPapersAll(Map<String, Object> queryMap) {
		return reviewPaperDao.getReviewPapersAll(queryMap);
	}

	@Override
	public List getReviewPapersListByUid(Integer uid) {
		return reviewPaperDao.getReviewPapersListByUid(uid);
	}

	@Override
	public List<ReviewProgressModel> reviewProgress(int revid) {
		return reviewPaperDao.reviewProgress(revid);
	}

	@Override
	public List<Integer> getItidsByUid(int uid) {
		return reviewPaperDao.getItidsByUid(uid);
	}

	@Override
	public List<String> groupByProgress(int revid) {
		return reviewPaperDao.groupByProgress(revid);
	}

	@Override
	public List<String> groupByResult(int revid) {
		return reviewPaperDao.groupByResult(revid);
	}


}
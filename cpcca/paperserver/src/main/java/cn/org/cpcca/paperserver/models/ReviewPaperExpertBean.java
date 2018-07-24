package cn.org.cpcca.paperserver.models;

import java.sql.Timestamp;

public class ReviewPaperExpertBean{

	private	Integer	pexid;
	private	Integer	revpid;
	private	Integer	uid;
	private	Integer	score;
	private	String	comment;
	private	Timestamp	ctime;
	private	Integer	state;
	public	Integer	getPexid(){
		return	pexid;
	}
	public	Integer	getRevpid(){
		return	revpid;
	}
	public	Integer	getUid(){
		return	uid;
	}
	public	Integer	getScore(){
		return	score;
	}
	public	String	getComment(){
		return	comment;
	}
	public	Timestamp	getCtime(){
		return	ctime;
	}
	public	Integer	getState(){
		return	state;
	}
	public void	setPexid(Integer pexid){
		this.pexid = pexid;
	}
	public void	setRevpid(Integer revpid){
		this.revpid = revpid;
	}
	public void	setUid(Integer uid){
		this.uid = uid;
	}
	public void	setScore(Integer score){
		this.score = score;
	}
	public void	setComment(String comment){
		this.comment = comment;
	}
	public void	setCtime(Timestamp ctime){
		this.ctime = ctime;
	}
	public void	setState(Integer state){
		this.state = state;
	}
	public	ReviewPaperExpertBean(){
		super();
	}
	public ReviewPaperExpertBean(Integer pexid,Integer revpid,Integer uid,Integer score,String comment,Timestamp ctime,Integer state){
		super();
		this.pexid = pexid;
		this.revpid = revpid;
		this.uid = uid;
		this.score = score;
		this.comment = comment;
		this.ctime = ctime;
		this.state = state;
	}
}
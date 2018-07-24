package cn.org.cpcca.paperserver.models;

import java.sql.Timestamp;

public class ReviewExpertItemBean{

	private	Integer	exitid;
	private	Integer	uid;
	private	Integer	revid;
	private	Timestamp	ctime;
	private	Integer	state;
	public	Integer	getExitid(){
		return	exitid;
	}
	public	Integer	getUid(){
		return	uid;
	}
	public	Integer	getRevid(){
		return	revid;
	}
	public	Timestamp	getCtime(){
		return	ctime;
	}
	public	Integer	getState(){
		return	state;
	}
	public void	setExitid(Integer exitid){
		this.exitid = exitid;
	}
	public void	setUid(Integer uid){
		this.uid = uid;
	}
	public void	setRevid(Integer revid){
		this.revid = revid;
	}
	public void	setCtime(Timestamp ctime){
		this.ctime = ctime;
	}
	public void	setState(Integer state){
		this.state = state;
	}
	public	ReviewExpertItemBean(){
		super();
	}
	public ReviewExpertItemBean(Integer exitid,Integer uid,Integer revid,Timestamp ctime,Integer state){
		super();
		this.exitid = exitid;
		this.uid = uid;
		this.revid = revid;
		this.ctime = ctime;
		this.state = state;
	}
}
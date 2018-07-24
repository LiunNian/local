package cn.org.cpcca.paperserver.models;

import java.sql.Timestamp;

public class ReviewItemBean{

	private	Integer	revid;
	private	Integer	itid;
	private	String	title;
	private	Timestamp	ctime;
	private	Integer	state;
	public	Integer	getRevid(){
		return	revid;
	}
	public	Integer	getItid(){
		return	itid;
	}
	public	String	getTitle(){
		return	title;
	}
	public	Timestamp	getCtime(){
		return	ctime;
	}
	public	Integer	getState(){
		return	state;
	}
	public void	setRevid(Integer revid){
		this.revid = revid;
	}
	public void	setItid(Integer itid){
		this.itid = itid;
	}
	public void	setTitle(String title){
		this.title = title;
	}
	public void	setCtime(Timestamp ctime){
		this.ctime = ctime;
	}
	public void	setState(Integer state){
		this.state = state;
	}
	public	ReviewItemBean(){
		super();
	}
	public ReviewItemBean(Integer revid,Integer itid,String title,Timestamp ctime,Integer state){
		super();
		this.revid = revid;
		this.itid = itid;
		this.title = title;
		this.ctime = ctime;
		this.state = state;
	}
}
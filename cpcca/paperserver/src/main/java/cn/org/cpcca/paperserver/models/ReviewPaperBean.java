package cn.org.cpcca.paperserver.models;

import java.sql.Timestamp;

public class ReviewPaperBean{

	private	Integer	revpid;
	private	Integer	revid;
	private	Integer	id;
	private	Integer	did;
	private	String	progress;
	private	String	result;
	private String repeatword;
	private String quoteword;
	private String selfword;
	private	Timestamp	ctime;
	private	Integer	state;
	public	Integer	getRevpid(){
		return	revpid;
	}
	public	Integer	getRevid(){
		return	revid;
	}
	public	Integer	getId(){
		return	id;
	}
	public	Integer	getDid(){
		return	did;
	}
	public	String	getProgress(){
		return	progress;
	}
	public	String	getResult(){
		return	result;
	}
	public	Timestamp	getCtime(){
		return	ctime;
	}
	public	Integer	getState(){
		return	state;
	}
	public void	setRevpid(Integer revpid){
		this.revpid = revpid;
	}
	public void	setRevid(Integer revid){
		this.revid = revid;
	}
	public void	setId(Integer id){
		this.id = id;
	}
	public void	setDid(Integer did){
		this.did = did;
	}
	public void	setProgress(String progress){
		this.progress = progress;
	}

	public String getRepeatword() {
		return repeatword;
	}

	public void setRepeatword(String repeatword) {
		this.repeatword = repeatword;
	}

	public String getQuoteword() {
		return quoteword;
	}

	public void setQuoteword(String quoteword) {
		this.quoteword = quoteword;
	}

	public String getSelfword() {
		return selfword;
	}

	public void setSelfword(String selfword) {
		this.selfword = selfword;
	}

	public void	setResult(String result){
		this.result = result;
	}
	public void	setCtime(Timestamp ctime){
		this.ctime = ctime;
	}
	public void	setState(Integer state){
		this.state = state;
	}
	public	ReviewPaperBean(){
		super();
	}
	public ReviewPaperBean(Integer revpid,Integer revid,Integer id,Integer did,String progress,String result,Timestamp ctime,Integer state){
		super();
		this.revpid = revpid;
		this.revid = revid;
		this.id = id;
		this.id = did;
		this.progress = progress;
		this.result = result;
		this.ctime = ctime;
		this.state = state;
	}

}
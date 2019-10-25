package com.sesoc.moneybook.vo;

public class MemberVO {

	private String userid;
	private String userpwd;
	
	public MemberVO() {}
	public MemberVO(String userid, String userpwd) {
		super();
		this.userid = userid;
		this.userpwd = userpwd;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", userpwd=" + userpwd + "]";
	}
}

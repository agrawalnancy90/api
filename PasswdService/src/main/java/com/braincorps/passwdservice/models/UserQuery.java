package com.braincorps.passwdservice.models;

public class UserQuery {

	private String name;
	private Long uid;
	private Long gid;
	private String comment;
	private String home;
	private String shell;
	
	public UserQuery(String name, Long uid, Long gid, String comment, String home, String shell) {
		super();
		this.name = name;
		this.uid = uid;
		this.gid = gid;
		this.comment = comment;
		this.home = home;
		this.shell = shell;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getGid() {
		return gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getShell() {
		return shell;
	}

	public void setShell(String shell) {
		this.shell = shell;
	}
	
	
	
	
}

package com.braincorps.passwdservice.models;

public class User extends Entity{
	private long uid;
	private String comment;
	private String home;
	private String shell;
	
	public User(long uid, long gid, String name, String comment, String home, String shell) {
		super(name, gid);
		this.uid = uid;
		this.comment = comment;
		this.home = home;
		this.shell = shell;
	}

	public long getUid() {
		return uid;
	}


	public void setUid(long uid) {
		this.uid = uid;
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

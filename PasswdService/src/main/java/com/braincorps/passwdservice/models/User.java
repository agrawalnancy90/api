package com.braincorps.passwdservice.models;

public class User {
	String name;
	long uid;
	long gid;
	String comment;
	String home;
	String shell;
	
	public User(long uid, long gid, String name, String comment, String home, String shell) {
		super();
		this.uid = uid;
		this.gid = gid;
		this.name = name;
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


	public long getGid() {
		return gid;
	}


	public void setGid(long gid) {
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


	public void setName(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("User [uid=%s, name=%s]", uid, name);
	}

}

package com.braincorps.passwdservice.models;

import java.util.List;

public class Group {
	private String name;
	private long gid;
	private List<String> members;
	
	public Group(String name, long gid, List<String> members) {
		super();
		this.name = name;
		this.gid = gid;
		this.members = members;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getGid() {
		return gid;
	}

	public void setGid(long gid) {
		this.gid = gid;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(List<String> members) {
		this.members = members;
	}
	
	@Override
	public String toString() {
		return String.format("Group [name=%s, gid=%s, members=%s]", name, gid, members.toString());
	}
	
}

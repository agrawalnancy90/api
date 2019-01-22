package com.braincorps.passwdservice.models;

import java.util.ArrayList;
import java.util.List;

public class GroupQuery {

	private String name;
	private Long gid;
	List<String> members;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public List<String> getMembers() {
		return members;
	}
	public void setMembers(List<String> members) {
		this.members = members;
	}
	
	public GroupQuery(String name, Long gid, List<String> members) {
		super();
		this.name = name;
		this.gid = gid;
		if(members != null)
			this.members = members;
		else
			this.members = new ArrayList<String>();
	}
	
	
}

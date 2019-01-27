package com.braincorps.passwdservice.models;

import java.util.List;

public class Group extends Entity{
	private List<String> members;
	
	public Group(String name, long gid, List<String> members) {
		super(name, gid);
		this.members = members;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(List<String> members) {
		this.members = members;
	}
		
}

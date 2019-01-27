package com.braincorps.passwdservice.models;

public class Entity {
	
	private String name;
	private long gid;
	
	public Entity(String name, long gid) {
		super();
		this.name = name;
		this.gid = gid;
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

	

}

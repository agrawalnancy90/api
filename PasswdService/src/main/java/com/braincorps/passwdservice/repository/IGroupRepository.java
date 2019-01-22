package com.braincorps.passwdservice.repository;

import java.util.List;

import com.braincorps.passwdservice.models.Group;
import com.braincorps.passwdservice.models.GroupQuery;
import com.braincorps.passwdservice.models.User;

public interface IGroupRepository {
	
	public Group getGroup(long gid);
	public List<Group> getAllGroups();
	public List<Group> getGroupsByQuery(GroupQuery query);
}

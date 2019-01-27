package com.braincorps.passwdservice.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.braincorps.passwdservice.models.Group;
import com.braincorps.passwdservice.models.GroupQuery;
import com.braincorps.passwdservice.utils.Reader;

@Component
@Service
public class GroupFileRepository implements IGroupRepository{
	
	@Value("${filepath.group}")
	private String groupFilePath;
	
	@Override
	public Group getGroup(long gid) {		
		List<Group> allGroups = (List<Group>) Reader.loadObjectsFromFile(groupFilePath, "group");
		for(Group group : allGroups) {
			if(group.getGid() == gid)
				return group;
		}
		
		return null;
	}

	@Override
	public List<Group> getAllGroups() {
		return (List<Group>) Reader.loadObjectsFromFile(groupFilePath, "group");		
	}
	
	
	@Override
	public List<Group> getGroupsByUser(String member) {
		List<Group> allGroups = (List<Group>) Reader.loadObjectsFromFile(groupFilePath, "group");
		List<Group> groups = new ArrayList<>();
		for(Group group : allGroups) {
			if(group.getMembers().contains(member))
				groups.add(group);
		}

		return groups;
	}


	@Override
	public List<Group> getGroupsByQuery(GroupQuery query) {
		List<Group> allGroups = (List<Group>) Reader.loadObjectsFromFile(groupFilePath, "group");
		List<Group> groups = new ArrayList<>();
		for(Group group : allGroups) {
			if(matchesQuery(group, query))
				groups.add(group);
		}

		return groups;
	}
	
	
	//Helper Functions:
	
	public boolean matchesQuery(Group group, GroupQuery query) {
		if(query.getName() != null && !group.getName().equals(query.getName()))
			return false;
		if(query.getGid() != null && group.getGid() != query.getGid())
			return false;
		List<String> groupMembers = group.getMembers();
		for(String member: query.getMembers()) {
			if(!groupMembers.contains(member))
				return false;
		}	
		return true;
	}
	
}

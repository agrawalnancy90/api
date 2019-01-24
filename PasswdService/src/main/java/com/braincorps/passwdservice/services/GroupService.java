package com.braincorps.passwdservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braincorps.passwdservice.models.Group;
import com.braincorps.passwdservice.models.GroupQuery;
import com.braincorps.passwdservice.repository.IGroupRepository;

@Service
public class GroupService {
	
	@Autowired
    IGroupRepository groupRepository;
	
	  public Group getGroupById(Long gid) {
	        return groupRepository.getGroup(gid);
	    }
	    
	    public List<Group> getAllGroups(){
	        return groupRepository.getAllGroups();
	    }
	    
	    public List<Group> getGroupsByQuery(GroupQuery groupQuery){
	        return groupRepository.getGroupsByQuery(groupQuery);
	    }
	    
	    public List<Group> getGroupsByUser(String member){
	        return groupRepository.getGroupsByUser(member);
	    }
}

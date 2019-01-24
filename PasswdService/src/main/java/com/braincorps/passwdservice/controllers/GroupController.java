package com.braincorps.passwdservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.braincorps.passwdservice.models.Group;
import com.braincorps.passwdservice.models.GroupQuery;
import com.braincorps.passwdservice.services.GroupService;

@RestController
public class GroupController {
	
	@Autowired
    GroupService groupService;
	
	@GetMapping("/groups")
	public List<Group> getAllGroups(){
		return groupService.getAllGroups();
	}
	
	@GetMapping("/groups/{gid}")
	public ResponseEntity<Group> getGroup(@PathVariable Long gid){
		Group group = groupService.getGroupById(gid);
		if(group != null)
			return new ResponseEntity<Group>(group, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/groups/query")
	public List<Group> getGroupsByQuery(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "gid", required = false) Long gid,
			@RequestParam(value = "member", required = false) List<String> members){
		
		GroupQuery query = new GroupQuery(name, gid, members);
		return groupService.getGroupsByQuery(query);
				
	}

}

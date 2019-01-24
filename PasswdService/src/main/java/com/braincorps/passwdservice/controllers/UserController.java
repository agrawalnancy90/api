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
import com.braincorps.passwdservice.models.User;
import com.braincorps.passwdservice.models.UserQuery;
import com.braincorps.passwdservice.repository.GroupFileRepository;
import com.braincorps.passwdservice.services.UserService;

@RestController
public class UserController {
	
	@Autowired
    UserService userService;
	  
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/users/{uid}")
	public ResponseEntity<User> getUser(@PathVariable Long uid){
		User user = userService.getUserById(uid);
		if(user != null)
			return new ResponseEntity<User>(user, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/users/query")
	public List<User> getUsersByQuery(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "uid", required = false) Long uid,
			@RequestParam(value = "gid", required = false) Long gid,
			@RequestParam(value = "comment", required = false) String comment,
			@RequestParam(value = "home", required = false) String home,
			@RequestParam(value = "shell", required = false) String shell){
		
		UserQuery query = new UserQuery(name, uid, gid, comment, home, shell);
		return userService.getUsersByQuery(query);
				
	}
	
	@GetMapping("/users/{uid}/groups")
	public ResponseEntity<List<Group>> getUserGroups(@PathVariable Long uid){
		User user = userService.getUserById(uid);
		if(user != null) {
			List<Group> groups = (new GroupFileRepository()).getGroupsByUser(user.getName());
			return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}

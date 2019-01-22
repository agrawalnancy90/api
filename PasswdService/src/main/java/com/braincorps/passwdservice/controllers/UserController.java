package com.braincorps.passwdservice.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.braincorps.passwdservice.models.User;
import com.braincorps.passwdservice.models.UserQuery;
import com.braincorps.passwdservice.repository.IUserRepository;
import com.braincorps.passwdservice.repository.UserFileRepository;



@RestController
public class UserController {
	
	//@Autowired(required = true)
    IUserRepository userRepository = new UserFileRepository();
	
    
    
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.getAllUsers();
	}
	
	
	
	@GetMapping("/users/{uid}")
	public ResponseEntity<User> getUser(@PathVariable Long uid){
		User user = userRepository.getUser(uid);
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
		return userRepository.getUsersByQuery(query);
				
	}

}

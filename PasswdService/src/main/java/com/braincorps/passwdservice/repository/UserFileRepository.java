package com.braincorps.passwdservice.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.braincorps.passwdservice.models.User;
import com.braincorps.passwdservice.models.UserQuery;
import com.braincorps.passwdservice.utils.Reader;

@Component
@Service
public class UserFileRepository implements IUserRepository{

	@Value("${filepath.user}")
	private String userFilePath;
	
	@Override
	public User getUser(long uid) {		
		List<User> allUsers = (List<User>) Reader.loadObjectsFromFile(userFilePath, "user");
		for(User user : allUsers) {
			if(user.getUid() == uid)
				return user;
		}
		
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		return (List<User>) Reader.loadObjectsFromFile(userFilePath, "user");		
	}
	


	@Override
	public List<User> getUsersByQuery(UserQuery query) {
		List<User> allUsers = (List<User>) Reader.loadObjectsFromFile(userFilePath, "user"); 
		List<User> users = new ArrayList<>();
		for(User user : allUsers) {
			if(matchesQuery(user, query))
				users.add(user);
		}

		return users;
	}
	
	//Helper function
	private boolean matchesQuery(User user, UserQuery query) {
		if(query.getName() != null && !user.getName().equals(query.getName()))
			return false;
		if(query.getUid() != null && user.getUid() != query.getUid())
			return false;
		if(query.getGid() != null && user.getGid() != query.getGid())
			return false;
		if(query.getComment() != null && !user.getComment().equals(query.getComment()))
			return false;
		if(query.getHome() != null && !user.getHome().equals(query.getHome()))
			return false;
		if(query.getShell() != null && !user.getShell().equals(query.getShell()))
			return false;		
		return true;
	}


}

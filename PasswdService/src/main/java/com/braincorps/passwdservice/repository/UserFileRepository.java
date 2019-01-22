package com.braincorps.passwdservice.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;

import com.braincorps.passwdservice.configuration.Properties;
import com.braincorps.passwdservice.models.User;
import com.braincorps.passwdservice.models.UserQuery;

@ComponentScan
public class UserFileRepository implements IUserRepository{

	//@Value("${filepath.user}")
	private String userFilePath = Properties.USERS_FILEPATH;
	
	@Override
	public User getUser(long uid) {
		
		File file = new File(userFilePath); 
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			
			String line;
			if(br != null)
				while ((line = br.readLine()) != null) {
					User user = parseLineAsUser(line);
					if(user != null && user.getUid() == uid) {
						br.close();
						return user;							
					}
				}
		} catch (FileNotFoundException e) {
			// TODO Display relevant error
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		File file = new File(userFilePath); 
		List<User> users = new ArrayList<>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			
			String line;
			if(br != null)
				while ((line = br.readLine()) != null) {
					User user = parseLineAsUser(line);
					if(user != null)
						users.add(user);							
				}
		} catch (FileNotFoundException e) {
			// TODO Display relevant error
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;	
	}
	
	
	/**
	 * 
	 * @param line
	 * @return
	 */
	private User parseLineAsUser(String line){
		
		//Ignore comments
		if(line.startsWith("#"))
			return null;
		
		String[] lineParts = line.split(":");
		try {
			User user = new User(Long.parseLong(lineParts[2]), 
							Long.parseLong(lineParts[3]), lineParts[0],
							lineParts[4], lineParts[5], lineParts[6]);
			return user;
		} catch(Exception e) {
			return null;
		}
	}

/**
 * 	
 * @param user
 * @param query
 * @return
 */
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

	@Override
	public List<User> getUsersByQuery(UserQuery query) {
		File file = new File(userFilePath); 
		List<User> users = new ArrayList<>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			
			String line;
			if(br != null)
				while ((line = br.readLine()) != null) {
					User user = parseLineAsUser(line);
					if(user != null && matchesQuery(user, query))
						users.add(user);							
				}
		} catch (FileNotFoundException e) {
			// TODO Display relevant error
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}

}

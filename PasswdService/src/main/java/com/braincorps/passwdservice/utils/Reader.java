package com.braincorps.passwdservice.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.braincorps.passwdservice.models.Entity;
import com.braincorps.passwdservice.models.Group;
import com.braincorps.passwdservice.models.User;

public class Reader {
	
	public static <T> List<? extends Entity> loadObjectsFromFile(String filepath, String type){
		List<Entity> objectsList = new ArrayList<>();
		File file = new File(filepath); 
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			
			String line;
			if(br != null)
				while ((line = br.readLine()) != null) {
					Entity obj = parseLineAsObjectFactory(line, type);
					if(obj != null) {
						objectsList.add(obj);
					}
				}
		} catch (FileNotFoundException e) {
			System.err.println(filepath + " NOT FOUND!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(filepath + " NOT FOUND!");
			e.printStackTrace();
		}
		
		return objectsList;
	}
	
	public static Entity parseLineAsObjectFactory(String line, String type) {
		Entity object = null;
		if(type.equals("group")) {
			object = parseLineAsGroup(line);
		}
		
		if(type.equals("user")) {
			object = parseLineAsUser(line);
		}
		
		return object;
	}
	
	private static Entity parseLineAsGroup(String line){
		
		//Ignore comments
		if(line.startsWith("#"))
			return null;
		
		String[] lineParts = line.split(":");
		try {
			List<String> members = new ArrayList<>();
			if(lineParts.length == 4) {
				String[] memberArray = lineParts[3].split(",");
				for(String m: memberArray) {
					members.add(m);
				}					
			}
			Group group = new Group(lineParts[0], Long.parseLong(lineParts[2]), members);
			return group;
		} catch(Exception e) {
			return null;
		}
	}
	
	private static Entity parseLineAsUser(String line){
		
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
	
	
	

}

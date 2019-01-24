package com.braincorps.passwdservice.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.braincorps.passwdservice.models.Group;
import com.braincorps.passwdservice.models.GroupQuery;

@Component
@Service
public class GroupFileRepository implements IGroupRepository{
	
	@Value("${filepath.group}")
	private String groupFilePath;
	
	@Override
	public Group getGroup(long gid) {
		
		File file = new File(groupFilePath); 
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			
			String line;
			if(br != null)
				while ((line = br.readLine()) != null) {
					Group group = parseLineAsGroup(line);
					if(group != null && group.getGid() == gid) {
						br.close();
						return group;							
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
	public List<Group> getAllGroups() {
		File file = new File(groupFilePath); 
		List<Group> groups = new ArrayList<>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			
			String line;
			if(br != null)
				while ((line = br.readLine()) != null) {
					Group group = parseLineAsGroup(line);
					if(group != null)
						groups.add(group);							
				}
		} catch (FileNotFoundException e) {
			// TODO Display relevant error
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return groups;	
	}
	
	
	/**
	 * 
	 * @param line
	 * @return
	 */
	private Group parseLineAsGroup(String line){
		
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

/**
 * 	
 * @param user
 * @param query
 * @return
 */
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

	@Override
	public List<Group> getGroupsByQuery(GroupQuery query) {
		File file = new File(groupFilePath); 
		List<Group> groups = new ArrayList<>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			
			String line;
			if(br != null)
				while ((line = br.readLine()) != null) {
					Group group = parseLineAsGroup(line);
					if(group != null && matchesQuery(group, query))
						groups.add(group);							
				}
		} catch (FileNotFoundException e) {
			// TODO Display relevant error
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return groups;
	}

	@Override
	public List<Group> getGroupsByUser(String member) {
		File file = new File(groupFilePath); 
		List<Group> groups = new ArrayList<>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			
			String line;
			if(br != null)
				while ((line = br.readLine()) != null) {
					Group group = parseLineAsGroup(line);
					if(group != null && group.getMembers().contains(member))
						groups.add(group);							
				}
		} catch (FileNotFoundException e) {
			// TODO Display relevant error
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return groups;
	}
	
}

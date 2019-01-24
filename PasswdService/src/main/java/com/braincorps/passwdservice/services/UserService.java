package com.braincorps.passwdservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braincorps.passwdservice.models.User;
import com.braincorps.passwdservice.models.UserQuery;
import com.braincorps.passwdservice.repository.UserFileRepository;

@Service
public class UserService {
 
    @Autowired
    private UserFileRepository userFileRepository;
 
    public User getUserById(Long uid) {
        return userFileRepository.getUser(uid);
    }
    
    public List<User> getAllUsers(){
        return userFileRepository.getAllUsers();
    }
    
    public List<User> getUsersByQuery(UserQuery userQuery){
        return userFileRepository.getUsersByQuery(userQuery);
    }
}
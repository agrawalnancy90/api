package com.braincorps.passwdservice.repository;

import java.util.List;

import com.braincorps.passwdservice.models.User;
import com.braincorps.passwdservice.models.UserQuery;

public interface IUserRepository {
	User getUser(long uid);
	List<User> getAllUsers();
	List<User> getUsersByQuery(UserQuery query);
}

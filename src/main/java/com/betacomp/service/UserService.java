package com.betacomp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacomp.entity.User;
import com.betacomp.exceptions.UserAlreadyExistsException;
import com.betacomp.exceptions.UserNotFoundException;

@Service
public interface UserService {

	List<User> getAllUsers();

	User createUser(User user) throws UserAlreadyExistsException;

	Optional<User> getUserById(Long id) throws UserNotFoundException;

	User updateUserById(User user, Long id) throws UserNotFoundException;

	void deleteUserById(Long id);
	
	User getUserByUserName(String userName);
	
}

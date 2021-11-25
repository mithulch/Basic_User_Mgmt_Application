package com.betacomp.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.betacomp.entity.User;
import com.betacomp.exceptions.UserAlreadyExistsException;
import com.betacomp.exceptions.UserNotFoundException;
import com.betacomp.repository.UserRepository;
import com.betacomp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) throws UserAlreadyExistsException {
		User user1 = userRepository.findByUserName(user.getUserName());
		if (user1 != null) {
			throw new UserAlreadyExistsException("User already with given name already exists in Database.");
		}
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException("Given user is NOT found in Database.");
		}
		return user;
	}

	@Override
	public User updateUserById(User user, Long id) throws UserNotFoundException {
		Optional<User> user2 = userRepository.findById(id);
		if (!user2.isPresent()) {
			throw new UserNotFoundException("Given user is found in Database to update it");
		}
		user.setUserId(id);
		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(Long id) {

		Optional<User> user2 = userRepository.findById(id);
		if (!user2.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entered User id to delete is not valid.");
		}
		userRepository.deleteById(id);
	}

	@Override
	public User getUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

}

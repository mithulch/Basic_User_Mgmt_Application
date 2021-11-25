package com.betacomp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.betacomp.entity.User;
import com.betacomp.exceptions.UserAlreadyExistsException;
import com.betacomp.exceptions.UserNameNotFoundException;
import com.betacomp.exceptions.UserNotFoundException;
import com.betacomp.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/users")
@Validated
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/getallusers")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping(value = "/createuser")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/createuser/{id}").buildAndExpand(user.getUserId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserAlreadyExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	@GetMapping(value = "/getbyid/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1)	 Long id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@PutMapping(value = "/updateuserbyid/{id}")
	public User updateUserById(@RequestBody User user, @PathVariable Long id) {
		try {
			return userService.updateUserById(user, id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@DeleteMapping(value = "/deleteuserbyid/{id}")
	public String deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
		return "User" + id + "got delete";

	}

	@GetMapping(value = "/byusername/{username}")
	public User findUserByUserName(@PathVariable("username") String username) throws UserNameNotFoundException {
		User user = userService.getUserByUserName(username);
		if (user == null) {
			throw new UserNameNotFoundException("UserName with given name is not found");
		}
		return user;

	}

}

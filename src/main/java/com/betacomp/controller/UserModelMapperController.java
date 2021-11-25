package com.betacomp.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacomp.dtos.UserMmDto;
import com.betacomp.entity.User;
import com.betacomp.exceptions.UserNotFoundException;
import com.betacomp.service.UserService;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping(value = "/{id}")
	public UserMmDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

		Optional<User> optionalUser = userService.getUserById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not Found");
		}

		User user = optionalUser.get();
		UserMmDto userDto = mapper.map(user, UserMmDto.class);

		return userDto;
	}

}

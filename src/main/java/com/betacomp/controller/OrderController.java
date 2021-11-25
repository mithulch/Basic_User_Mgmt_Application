package com.betacomp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacomp.entity.Order;
import com.betacomp.entity.User;
import com.betacomp.exceptions.UserNotFoundException;
import com.betacomp.repository.OrderRepository;
import com.betacomp.repository.UserRepository;

@RestController
@RequestMapping(value = "/api/v1/users")
public class OrderController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private OrderRepository orderRepo;

	@GetMapping(value = "/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {

		Optional<User> userOptional = userRepo.findById(userid);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("Given User is not found in Database");

		return userOptional.get().getOrder();

	}

	@PostMapping(value = "/{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {

		Optional<User> userOptional = userRepo.findById(userid);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("Given User is not found in Database");

		User user = userOptional.get();
		order.setUser(user);
		return orderRepo.save(order); 

	}

}

package com.betacomp.dtos;

import java.util.List;

import com.betacomp.entity.Order;

import lombok.Data;

@Data
public class UserMmDto {

	private Long userId;
	private String userName;
	private String firstName;

	private List<Order> Order ;
}

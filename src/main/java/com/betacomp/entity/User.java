package com.betacomp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "user")
@JsonIgnoreProperties({"firstName","lastName"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@NotEmpty(message = "UserName is Mandatory field.")
	@Column(name = "username", length = 20, nullable = false, unique = true)
	private String userName;

	@Size(min = 2, message = "First name should have atleast 2 characters.")
	@Column(name = "firstname", length = 15)
	private String firstName;

	@Column(name = "lastname", length = 15)
	private String lastName;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "role", length = 30, unique = true)
	private String role;

	@Column(name = "ssn", length = 40)
	@JsonIgnore
	private String ssn;

	@OneToMany(mappedBy = "user")
	private List<Order> order;
	
	
}

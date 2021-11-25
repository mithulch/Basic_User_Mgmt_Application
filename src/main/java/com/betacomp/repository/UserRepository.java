package com.betacomp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacomp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String username);

}

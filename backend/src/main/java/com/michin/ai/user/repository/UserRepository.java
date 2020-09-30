package com.michin.ai.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michin.ai.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByBotKey(String botKey);

}

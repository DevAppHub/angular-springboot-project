package com.cts.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.admin.modal.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String username);
}

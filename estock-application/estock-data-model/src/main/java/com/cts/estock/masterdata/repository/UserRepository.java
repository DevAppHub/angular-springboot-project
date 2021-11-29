package com.cts.estock.masterdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.estock.masterdata.modal.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String username);
}

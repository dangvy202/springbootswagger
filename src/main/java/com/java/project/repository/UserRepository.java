package com.java.project.repository;

import com.java.project.Entity.UserEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity , Long> {

	@Query("SELECT u FROM UserEntity u WHERE u.username = :username")
	public UserEntity findByName(@Param("username") String username);
}

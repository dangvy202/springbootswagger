package com.java.project.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.java.project.Entity.MyUserDetail;
import com.java.project.Entity.UserEntity;
import com.java.project.repository.UserRepository;

public class UserDetailsImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Could Not Found User Name");
		}
		return new MyUserDetail(user);
	}

}

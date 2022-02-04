package com.exam.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.dao.UserRepository;
import com.exam.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user=this.userRepository.findByUsername(username);
		
		if(user==null)
		{
			System.out.println("user not found");
			throw new UsernameNotFoundException("user not found");
		}
	
		return user;
	}

	
}

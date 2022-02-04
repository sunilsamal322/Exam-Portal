package com.exam.service.Impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dao.RoleRepository;
import com.exam.dao.UserRepository;
import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	//creating user by user and role
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User local=this.userRepository.findByUsername(user.getUsername());
		
		if(local!=null)
		{
			System.out.println("this username is already taken");
			throw new UserFoundException();
		}
		else
		{
			for(UserRole userRole:userRoles)
			{
				this.roleRepository.save(userRole.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local=this.userRepository.save(user);
		}
		return local;
	}
	//getting user by username
	@Override
	public User getUser(String username) {
		
		return userRepository.findByUsername(username);
	}
	
	//delete user by username
	@Override
	public void deleteUser(Long id) {
		
		userRepository.deleteById(id);
	}
	//getting all id
	@Override
	public List<User> getAll() {
		
		return userRepository.findAll();
	}
	//updating user by username
	@Override
	public User updateUser(User user) {
		
		return userRepository.save(user);
	}
}

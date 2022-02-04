package com.exam.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception
	{
		user.setProfile("default.png");
		
		//encoding password
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		Role role=new Role();
		role.setRoleId(200L);
		role.setRoleName("NORMAL");
		
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		Set<UserRole> userRoleSet=new HashSet<>();
		userRoleSet.add(userRole);
		
		User user1=this.userService.createUser(user,userRoleSet);
		return user1;
	}
	@GetMapping("/")
	public List<User> getusers()
	{
		return userService.getAll();
	}
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username)
	{
		return this.userService.getUser(username);
	}
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId)
	{
		this.userService.deleteUser(userId);
	}
	@PutMapping("/")
	public User updateUser(@RequestBody User user)
	{
		userService.updateUser(user);
		return user;
	}
}

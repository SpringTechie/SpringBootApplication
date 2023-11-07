package com.spring.aws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.aws.entity.User;
import com.spring.aws.exception.ResourceNotFoundException;
import com.spring.aws.repo.UserRepository;

@RestController
public class UserController
{
	
	@Autowired 
	private UserRepository repo;
	
	@GetMapping("/allusers")
	public List<User> getAllUsers()
	{
		return repo.findAll();
	}
	
	@GetMapping("/getuser/{id}")
	public User getUser(@PathVariable Integer id)
	{
		return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("no user found with this "+id));
	}
	@PostMapping("/saveuser")
	public User saveUser(@RequestBody User user)
	{
		return repo.save(user);
	}
	
	@PutMapping("/update/{id}")
	public  User updateUser(@RequestBody User user ,@PathVariable Integer id)
	{
		User existingUser=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("no user found with this "+id));
		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		return repo.save(existingUser);
	}
	
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer id)
	{
		User existingUser=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("no user found with this "+id));
		 repo.delete(existingUser);
		 return  ResponseEntity.ok().build();
		
	}

}

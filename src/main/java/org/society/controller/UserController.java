package org.society.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.society.service.*;
import java.util.List;
import java.util.Optional;

import org.society.entities.User;
import org.society.exceptions.UserNotFoundException;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServiceImpl serviceimpl;

	public void setUserService(UserServiceImpl serviceimpl)
	{
		this.serviceimpl=serviceimpl;
	}

	@GetMapping(value="/all",produces="application/json")
	public ResponseEntity<List<User>> getUsers()
	{
		return new ResponseEntity<List<User>>(serviceimpl.viewUserList(),HttpStatus.OK);
	}

	@GetMapping(value="/{userId}",produces="application/json")
	public ResponseEntity<User> findUser(@PathVariable("userId")int userId){
		User user = null;

		user = serviceimpl.findByUserId(userId);

		if(user==null) {
			System.out.println("user not found");
			return new ResponseEntity("Sorry! User not found!",HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping(consumes="application/json")
	public ResponseEntity<HttpStatus> addUser(@RequestBody User user)
	{
		serviceimpl.registerUser(user);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@PutMapping(consumes="application/json")
	public ResponseEntity<HttpStatus> modifyUser(@RequestBody User user)
	{
		boolean a=false;

		a=serviceimpl.updateUser(user);

		if(a==false)
		{
			System.out.println("user not found to modify");
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@DeleteMapping(value="/{userId}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId")Integer userId)
	{
		boolean a= false;

		a=serviceimpl.deleteUser(userId);

		if(a==false)
		{
			System.out.println("user not found to delete");
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}


}

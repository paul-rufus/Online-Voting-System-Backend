package org.society.service;

import java.util.List;

import org.society.entities.User;
import org.society.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.society.dao.*;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userimpl;

	@Override
	public User registerUser(User user) {
		return userimpl.registerUser(user);

	}

	@Override
	public boolean updateUser(User user)  {
		boolean a=false;
		try
		{
			a= userimpl.updateUser(user);
		}catch(UserNotFoundException ex)
		{
			System.out.println("user not found");
		}
		return a;
	}

	@Override
	public boolean deleteUser(int userId)  {
		boolean a = false;
		try
		{
			a =  userimpl.deleteUser(userId);
		}catch(UserNotFoundException ex)
		{
			System.out.println("user not found");
		}

		return a;
	}

	@Override
	public List<User> viewUserList() {

		return userimpl.viewUserList();
	}

	@Override
	public User findByUserId(int userId)   {
		User found = null;
		try
		{
			found = userimpl.findByUserId(userId);
		}catch(UserNotFoundException ex)
		{
			System.out.println("user not found");
		}
		return found;
	}

}

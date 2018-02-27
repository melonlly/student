package com.manage.controller;

import com.manage.entity.Result;
import com.manage.entity.User;
import com.manage.repository.UserRepository;
import com.manage.service.UserService;
import com.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	UserRepository userRepository;
	@Autowired
	UserService userService;

	@RequestMapping("/list")
	@ResponseBody
	public Result findUsers(Integer page, Integer size, User user) {
		Result result = new Result();
		try {
			// 分页默认从0页开始
			Page<User> users = userService.findUsers(page - 1, size, user);
			result.setCode(Constants.SUCCESS);
			result.setData(users.getContent());
			result.setTotal(users.getSize());
			result.setPages(users.getTotalPages());
			result.setIndex(page);
			result.setSize(size);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}

	@Modifying
	@RequestMapping("/add")
	@ResponseBody
	public Result saveUser(User user) {
		Result result = new Result();
		try {
			userRepository.save(user);
			result.setCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}

	@Modifying
	@RequestMapping("/update")
	@ResponseBody
	public Result updateUser(User user) {
		Result result = new Result();
		User userEntity = userRepository.findOne(Long.getLong(user.getId() + ""));
		if (userEntity != null) {
			try {
				userRepository.save(user);
				result.setCode(Constants.SUCCESS);
			} catch (Exception e) {
				result.setCode(Constants.FAIL);
				result.setError(e.getMessage());
			}
		}
		return result;
	}

	@Modifying
	@RequestMapping("/remove")
	@ResponseBody
	public Result removeUser(Integer id) {
		Result result = new Result();
		try {
			userRepository.delete(Long.getLong(id + ""));
			result.setCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}
}

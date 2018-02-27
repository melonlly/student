package com.manage.controller;

import com.manage.entity.Result;
import com.manage.entity.User;
import com.manage.repository.UserRepository;
import com.manage.service.UserService;
import com.manage.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

	@Resource
	UserService userService;

	@RequestMapping("/login")
	@ResponseBody
	public Result login(String name, String password) {
		Result result = new Result();
		try	{
			User user = userService.Login(name, password);
			if(user != null){
				List<User> list = new ArrayList<User>();
				list.add(user);
				result.setCode(Constants.SUCCESS);
				result.setData(list);
			}else{
				result.setCode(Constants.FAIL);
				result.setError("用户名或密码错误！");
			}
		}catch (Exception e){
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}

}

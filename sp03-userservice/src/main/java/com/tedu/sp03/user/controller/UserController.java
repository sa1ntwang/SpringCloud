package com.tedu.sp03.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.sp01.pojo.User;
import com.tedu.sp01.service.UserService;
import com.tedu.web.util.JsonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/{userId}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public JsonResult<User> getUser(@PathVariable Integer userId) {
		log.info("get user, userId="+userId);
		User u = userService.getUser(userId);
		return JsonResult.ok(u);
	}
	
	@GetMapping("/{userId}/score") 
	public JsonResult addScore(
			@PathVariable Integer userId, Integer score) {
		userService.addScore(userId, score);
		return JsonResult.ok();
	}
}

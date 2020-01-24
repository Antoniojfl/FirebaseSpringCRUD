package com.antonio.example.controllers;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antonio.example.models.User;
import com.antonio.example.services.Firebase;

@RestController
public class UserController {
	
	@Autowired
	private Firebase service;
	
	public UserController (Firebase service){
		this.service = service;
	}
	
	@PostMapping("/create")
	public String createUser (@RequestBody User user)throws InterruptedException, ExecutionException {
		return service.createUser(user);
	}

}

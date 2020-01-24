package com.antonio.example.services;

import java.util.concurrent.ExecutionException;

import com.antonio.example.models.User;

public interface Firebase {
	
	String createUser(User user) throws InterruptedException, ExecutionException;

}

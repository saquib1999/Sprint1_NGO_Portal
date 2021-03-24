package com.cg.ngoportal.service;

import org.springframework.stereotype.Service;

import com.cg.ngoportal.model.User;
@Service
public interface LoginService {
	
	public String login(User loginCred);

}

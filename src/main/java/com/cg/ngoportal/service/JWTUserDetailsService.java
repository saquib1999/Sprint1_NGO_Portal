package com.cg.ngoportal.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.ngoportal.dao.AdminDao;
import com.cg.ngoportal.dao.DonorDao;
import com.cg.ngoportal.dao.EmployeeDao;
import com.cg.ngoportal.dao.NeedyPeopleDao;
import com.cg.ngoportal.dao.UserDao;
import com.cg.ngoportal.model.Admin;
import com.cg.ngoportal.model.JwtResponse;
import com.cg.ngoportal.model.UserType;

@Service
public class JWTUserDetailsService implements UserDetailsService {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	private UserDao userRepo;
	
	@Autowired
	private AdminDao adminRepo;
	
	@Autowired
	private EmployeeDao employeeRepo;
	
	@Autowired
	private NeedyPeopleDao needyPersonRepo;
	
	@Autowired
	private DonorDao donorRepo;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	      com.cg.ngoportal.model.User user =  userRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not found"+username));
	      
	      
	      
	      return  new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
	      
	
	}
	
	public Admin save(Admin user) {
		Admin newUser = new Admin();
		com.cg.ngoportal.model.User user1 = user.getUserLoginDetails();
		user1.setPassword(bcryptEncoder.encode(user1.getPassword()));
		user1.setUserType(UserType.ADMIN);
		newUser.setUserLoginDetails(user1);
	
		return adminService.addUser(newUser);
	}
	
	public int[] userTypeAndId(String username ) {
		com.cg.ngoportal.model.User user =  userRepo.findByUsername(username).get();
		int userType = user.getUserType().ordinal();
		System.out.println("type " + userType);
		int id = 0;
		switch(userType) {
		case 0 : id = employeeRepo.findByUserLoginDetails(user).get().getId();
			break;
		case 1 : id = donorRepo.findByUserLoginDetails(user).get().getId();
			break;
		case 2 : id = needyPersonRepo.findByUserLoginDetails(user).get().getId();
			break;
		case 3 : id = adminRepo.findByUserLoginDetails(user).get().getId();
			break;
		default : 
			
		}
		
		System.out.println(id);
		int arr[] = {id,userType};
		return arr;
	}

}

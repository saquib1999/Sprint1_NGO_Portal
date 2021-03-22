package com.cg.ngoportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.ngoportal.model.Donation;
import com.cg.ngoportal.model.DonationItem;
import com.cg.ngoportal.model.Donor;
import com.cg.ngoportal.service.DonorServiceImpl;

@RestController
public class DonorController {
	@Autowired
	DonorServiceImpl donorService;
	
	@PostMapping("/donor/register")
	public ResponseEntity<Donor> registerDonor(@RequestBody Donor donor) {		
		
		return new ResponseEntity<Donor>(donorService.registerDonor(donor), HttpStatus.CREATED);
	}
	
	@GetMapping("/donor/login")
	public ResponseEntity<Integer> loginDonor(@RequestBody Login login)  {	
		
		return new ResponseEntity<Integer>(donorService.login(login.getUsername(), login.getPassword()), HttpStatus.ACCEPTED);
	}
	@PostMapping("/donor/donate/{amount}")
	public ResponseEntity<Donation> donateToNGO(@RequestBody DonationItem item,@PathVariable double amount) {	
		
		return new ResponseEntity<Donation>(donorService.donateToNGO(item,amount), HttpStatus.ACCEPTED);
	}
	@GetMapping("donor/thankyou-mail")
	public ResponseEntity<String> sendThankyouMailToDonator(@RequestBody Donor donor) {	
		
		return new ResponseEntity<String>(donorService.sendThankyouMailToDonator(donor), HttpStatus.OK);
	}
	@GetMapping("donor/forgot-password/{username}")
    public ResponseEntity<String> forgotPassword(@PathVariable String username) {	
		
		return new ResponseEntity<String>(donorService.forgotPassword(username), HttpStatus.OK);
	}
	@PostMapping("/donor/reset-password/{username}/{oldPassword}/{newPassword}")
	public ResponseEntity<String> resetPassword(@PathVariable String username,@PathVariable String oldPassword,@PathVariable String newPassword) {	
		
		return new ResponseEntity<String>(donorService.resetPassword(username, oldPassword,newPassword), HttpStatus.ACCEPTED);
	}
	@PostMapping("/donor/email-passwordToDonor/{email}")
	public ResponseEntity<String> emailPasswordToDonor(@PathVariable String email) {	
		
		return new ResponseEntity<String>(donorService.emailPasswordToDonor(email), HttpStatus.OK);
	}	
	@GetMapping("/donor/hii/{name}")
	public String greetings(@PathVariable String name)
	{
		return "Hii "+name;
	}
	
	
}

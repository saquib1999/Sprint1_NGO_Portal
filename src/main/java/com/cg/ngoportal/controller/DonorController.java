package com.cg.ngoportal.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ngoportal.model.Donation;
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
	
	
	@PostMapping("/donate")
	public ResponseEntity<Donation> donateToNGO(@RequestBody Donation donation)   {	
		
		return new ResponseEntity<Donation>(donorService.donateToNGO(donation,new SimpleMailMessage()), HttpStatus.ACCEPTED);
	}
	@GetMapping("/send-certificate")
	public ResponseEntity<String> sendCertificateToDonor() throws MessagingException    {	
		
		return new ResponseEntity<String>(donorService.sendCertificateToDonor(), HttpStatus.OK);
	}
	@GetMapping("/donor/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody LoginCred login) {	
		
		return new ResponseEntity<String>(donorService.forgotPassword(login.getUsername(),new SimpleMailMessage()), HttpStatus.OK);
	}
	@PatchMapping("/donor/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPassword resetPassword) {	
		
		return new ResponseEntity<String>(donorService.resetPassword(resetPassword.getUsername(),resetPassword.getOldpassword(),resetPassword.getNewpassword()), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/receipts/last")
	public ResponseEntity<Donation> getLastReceipt()  {
		return new ResponseEntity<Donation>(donorService.lastDonationReceipt(new SimpleMailMessage()),HttpStatus.OK);
	}
	
	@GetMapping("/receipts/all")
	public ResponseEntity<List<Donation>> getAllReceipt()  {
		return new ResponseEntity<List<Donation>>(donorService.allDonationReceipt(new SimpleMailMessage()),HttpStatus.OK);
	}
	
		
	@GetMapping("/donor/logout")
	public ResponseEntity<String> logoutNeedyPerson() {
		return new ResponseEntity<String>(donorService.logOut(),HttpStatus.OK);

	}
	
	
}

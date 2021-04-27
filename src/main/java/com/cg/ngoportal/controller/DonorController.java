package com.cg.ngoportal.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ngoportal.model.Donation;
import com.cg.ngoportal.model.Donor;
import com.cg.ngoportal.service.DonorServiceImpl;

@RestController
@CrossOrigin
public class DonorController {
	@Autowired
	DonorServiceImpl donorService;
	@PostMapping("/donor/register")
	public ResponseEntity<Donor> registerDonor(@RequestBody Donor donor) {		
		
		return new ResponseEntity<Donor>(donorService.registerDonor(donor), HttpStatus.CREATED);
	}
	
	
	@PostMapping("/donate/{id}")
	public ResponseEntity<Donation> donateToNGO(@RequestBody Donation donation,@PathVariable int id)   {	
		
		return new ResponseEntity<Donation>(donorService.donateToNGO(donation,id,new SimpleMailMessage()), HttpStatus.ACCEPTED);
	}
	@GetMapping("/send-certificate/{id}")
	public ResponseEntity<String> sendCertificateToDonor(@PathVariable int id) throws MessagingException    {	
		
		return new ResponseEntity<String>(donorService.sendCertificateToDonor(id), HttpStatus.OK);
	}
	@GetMapping("/donor/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody LoginCred login) {	
		
		return new ResponseEntity<String>(donorService.forgotPassword(login.getUsername(),new SimpleMailMessage()), HttpStatus.OK);
	}
	@PatchMapping("/donor/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPassword resetPassword) {	
		
		return new ResponseEntity<String>(donorService.resetPassword(resetPassword.getUsername(),resetPassword.getOldpassword(),resetPassword.getNewpassword()), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/receipts/last/{id}")
	public ResponseEntity<Donation> getLastReceipt(@PathVariable int id)  {
		return new ResponseEntity<Donation>(donorService.lastDonationReceipt(new SimpleMailMessage(),id),HttpStatus.OK);
	}
	
	@GetMapping("/receipts/all/{id}")
	public ResponseEntity<List<Donation>> getAllReceipt(@PathVariable int id)  {
		return new ResponseEntity<List<Donation>>(donorService.allDonationReceipt(new SimpleMailMessage(),id),HttpStatus.OK);
	}
	
		
	@GetMapping("/donor/logout")
	public ResponseEntity<String> logoutNeedyPerson() {
		return new ResponseEntity<String>(donorService.logOut(),HttpStatus.OK);

	}
	@GetMapping("/find-donor-by-id/{id}")
	public ResponseEntity<Donor> findDonorById(@PathVariable int id) {
		return new ResponseEntity<Donor>(donorService.getDonorById(id), HttpStatus.OK);
	}
	
}
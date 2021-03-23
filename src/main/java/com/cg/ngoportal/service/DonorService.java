package com.cg.ngoportal.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.cg.ngoportal.exception.DuplicateDonorException;
import com.cg.ngoportal.exception.NoSuchDonorException;
import com.cg.ngoportal.model.Donation;
import com.cg.ngoportal.model.Donor;
@Service
public interface DonorService {
	public Donor registerDonor(Donor donor) throws DuplicateDonorException;
	public int login(String userName,String password) throws NoSuchDonorException;
	public Donation donateToNGO(Donation donation,SimpleMailMessage message);
	public String sendThankyouMailToDonator(SimpleMailMessage message);
	public String forgotPassword(String username,SimpleMailMessage message)throws NoSuchDonorException;
	public String resetPassword(String username,String oldPassword,String newPassword)throws NoSuchDonorException;
	public String emailPasswordToDonor(SimpleMailMessage message)throws NoSuchDonorException;
	public String logOut();
}

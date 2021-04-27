package com.cg.ngoportal.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.cg.ngoportal.exception.DuplicateDonorException;
import com.cg.ngoportal.exception.NoSuchDonorException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.Donation;
import com.cg.ngoportal.model.Donor;
import com.cg.ngoportal.model.User;
@Service
public interface DonorService {
	public Donor registerDonor(Donor donor) throws DuplicateDonorException;
	public String login(User user) throws NoSuchDonorException;
	public Donation donateToNGO(Donation donation,int donorId,SimpleMailMessage message) throws UserNotLoggedInException;
	public String forgotPassword(String username,SimpleMailMessage message)throws NoSuchDonorException;
	public String resetPassword(String username,String oldPassword,String newPassword)throws NoSuchDonorException;
	public String logOut();
	public String sendCertificateToDonor(int donorId) throws MessagingException, UserNotLoggedInException;
	public Donation lastDonationReceipt(SimpleMailMessage message,int donorId) throws UserNotLoggedInException;
	public List<Donation> allDonationReceipt(SimpleMailMessage message,int donorId) throws UserNotLoggedInException;
	public Donor getDonorById(int donorId) throws NoSuchDonorException, UserNotLoggedInException;

}
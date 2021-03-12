package com.cg.ngoportal.service;

import com.cg.ngoportal.exception.DuplicateDonorException;
import com.cg.ngoportal.exception.NoSuchDonorException;
import com.cg.ngoportal.model.Donation;
import com.cg.ngoportal.model.Donor;

public interface DonorService {
	public boolean registerDonor(Donor donor) throws DuplicateDonorException;
	public boolean login(Donor donor) throws NoSuchDonorException;
	public Donation donateToNGO(Donation donation);
	public void sendThankyouMailToDonator(Donor donor);
	public String forgotPassword(String username);
	public String resetPassword(String username);
	public void emailPasswordToDonor(String email);
}

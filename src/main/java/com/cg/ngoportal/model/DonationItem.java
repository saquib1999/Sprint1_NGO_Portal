package com.cg.ngoportal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NDonationItem")
public class DonationItem {
	@Id
	@GeneratedValue(generator = "NITEM_ID", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "NITEM_ID", sequenceName = "NITEMID",allocationSize=1, initialValue = 1)	

	private int id;
	
	private DonationType item;
	private String description;
	
	
	
	public DonationItem() {
		// TODO Auto-generated constructor stub
	}
	
	
	public DonationItem(DonationType item, String description) {
		super();
		this.item = item;
		this.description = description;
	}


	public int getId() {
		return id;
	}
	public DonationType getItem() {
		return item;
	}
	public void setItem(DonationType item) {
		this.item = item;
	}
	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "DonationItem [itemId=" + id + ", item=" + item + ", description=" + description + "]";
	}
	
	
	
	
}

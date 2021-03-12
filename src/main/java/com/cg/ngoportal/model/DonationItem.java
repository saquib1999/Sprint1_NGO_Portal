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
    @SequenceGenerator(name = "NITEM_ID", sequenceName = "NITEMID",allocationSize=1, initialValue = 1000)	

	private int itemId;
	
	private DonationType item;
	private String itemDescription;
	
	
	
	public DonationItem() {
		// TODO Auto-generated constructor stub
	}
	
	
	public DonationItem(DonationType item, String itemDescription) {
		super();
		this.item = item;
		this.itemDescription = itemDescription;
	}


	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public DonationType getItem() {
		return item;
	}
	public void setItem(DonationType item) {
		this.item = item;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	@Override
	public String toString() {
		return "DonationItem [itemId=" + itemId + ", item=" + item + ", itemDescription=" + itemDescription + "]";
	}
	
	
	
	
}

package com.cg.ngoportal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "NEmployee")//,uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class Employee {
	@Id //@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(generator = "NEMPID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "NEMPID", sequenceName = "NEMP_ID",allocationSize=1,initialValue = 100)
	private int employeeId;
	private String employeeName;
	private String email;
	private String phone;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")

	private Address address;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
	private User user;
	
	
	
	public Employee() {
		// TODO Auto-generated constructor stub
		}

	public Employee( String employeeName, String email, String phone, Address address, User user) {
		super();
		
		this.employeeName = employeeName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.user = user;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email + ", phone="
				+ phone + ", address=" + address + ", user=" + user + "]";
	}
	
	
	
	
}

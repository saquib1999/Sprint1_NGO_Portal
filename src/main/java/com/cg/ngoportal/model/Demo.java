package com.cg.ngoportal.model;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cg.ngoportal.dao.EmployeeDaoImpl;
import com.cg.ngoportal.exception.NoSuchEmployeeException;

public class Demo {
	public static void main(String[] args) {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("demo");
//		EntityManager em = factory.createEntityManager();
//		
//		Employee e1 = new Employee(1, "abcd", null, null, null, "abcd", "abcd");
//		em.getTransaction().begin();
//		em.persist(e1);
//		em.getTransaction().commit();
//		System.out.println("created");
//	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Project");
		EntityManager em = emf.createEntityManager();
		EmployeeDaoImpl ed = new EmployeeDaoImpl();
		try {
			ed.login("tabish", "tabishpwd");
		} catch (SQLException | NoSuchEmployeeException e1) {
			// TODO Auto-generated catch block
			System.out.println("catch");
			e1.printStackTrace();
		}
		
		NeedyPeople np = em.find(NeedyPeople.class, 1020);
		DonationItem dItem = new DonationItem(DonationType.BOOKS, "BOOKS TO DONATE");
		Employee e = em.find(Employee.class, 102);
		DonationDistribution dis = new DonationDistribution(np, dItem, e, 10, null, null, DonationDistributionStatus.APPROVED);
		em.getTransaction().begin();
		em.persist(dis);
		em.getTransaction().commit();
		System.out.println(dis);
		System.out.println(ed.helpNeedyPerson(dis));

}
}

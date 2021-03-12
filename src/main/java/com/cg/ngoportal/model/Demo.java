package com.cg.ngoportal.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
		User u = new User("saquib8", "saquib", UserType.EMPLOYEE);
		Address a = new Address("pune", "mah", "411", "pune");
		NeedyPeople np = new NeedyPeople("saquib", "123", 100, u, a);
		em.getTransaction().begin();
		em.persist(np);
		em.getTransaction().commit();
		em.close();
		emf.close();
		System.out.println("done");

}
}

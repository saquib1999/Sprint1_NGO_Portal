package com.cg.ngoportal.dao.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.ngoportal.dao.EmployeeDaoImpl;
import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.model.Address;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.DonationDistributionStatus;
import com.cg.ngoportal.model.DonationItem;
import com.cg.ngoportal.model.DonationType;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.User;
import com.cg.ngoportal.model.UserType;
import com.cg.ngoportal.service.EmployeeServiceImpl;

class EmployeeDaoImplTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("Project");
		em = emf.createEntityManager();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		em.close();
		emf.close();
		
	}

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	

	@Test
	void testLogin() {
//	emf = Persistence.createEntityManagerFactory("Project");
//	em = emf.createEntityManager();
//	User u = new User("tabish", "tabishpwd", UserType.EMPLOYEE);
//	Address a = new Address("akd", "mah", "422", "school");
//	Employee e1 =new Employee("rohit", "rohit@mail.com", "98765", a, u);
//	em.getTransaction().begin();
//	em.persist(e1);
//	em.getTransaction().commit();
//	int b;
	EmployeeDaoImpl ed = new EmployeeDaoImpl();
	try {
		assertEquals(1, ed.login("tabish", "tabishpwd"));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchEmployeeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

//	@Test
//	void testCreateNeedyPerson() {
//		
//		//NeedyPeople np = new NeedyPeople(0, null, null, 0, null)
//		User u = new User("saquibneedy", "saquibpwd", UserType.NEEDYPERSON);
//		Address a = new Address("pune", "mah", "411", "pune");
//		NeedyPeople np = new NeedyPeople("saquibneedy", "123", 100, u, a);
//		//np.setNeedyPersonId(1004);
//		assertEquals(1, new EmployeeDaoImpl().createNeedyPerson(np));
//		
//
//	
//	}

//	@Test
//	void testDeleteNeedyPerson() {
//		User u = new User("saquibneedy", "saquibpwd", UserType.EMPLOYEE);
//		Address a = new Address("pune", "mah", "411", "pune");
//		NeedyPeople np = new NeedyPeople("saquibneedy", "123", 100, u, a);
//		np.setNeedyPersonId(1004);
//		assertEquals(1, new EmployeeDaoImpl().deleteNeedyPerson(np));
//		
//	}

	@Test
	void testReadNeedyPeopleById() {
		
//		emf = Persistence.createEntityManagerFactory("Project");
//		em = emf.createEntityManager();
		NeedyPeople n = em.find(NeedyPeople.class, 1003);
		
		assertEquals(n.getNeedyPersonName(), new EmployeeDaoImpl().readNeedyPeopleById(n.getNeedyPersonId()).getNeedyPersonName());
		
	}

	@Test
	void testReadNeedyPeopleByName() {
		//List<NeedyPeople> nplist = new EmployeeDaoImpl().readAllNeedyPeople();
//		emf = Persistence.createEntityManagerFactory("Project");
//		em = emf.createEntityManager();
		assertEquals(1, new EmployeeDaoImpl().readNeedyPeopleByName("saquibneedy").size());
	}

	@Test
	void testReadAllNeedyPeople() {
		assertEquals(9, new EmployeeDaoImpl().readAllNeedyPeople().size());
	}

	@Test
	void testHelpNeedyPerson() throws Exception, NoSuchEmployeeException {
		EmployeeDaoImpl ed = new EmployeeDaoImpl();
		ed.login("tabish", "tabishpwd");
		
		NeedyPeople np = em.find(NeedyPeople.class, 1020);
		DonationItem dItem = new DonationItem(DonationType.BOOKS, "BOOKS TO DONATE");
		Employee e = em.find(Employee.class, 102);
		DonationDistribution dis = new DonationDistribution(np, dItem, e, 10, null, null, DonationDistributionStatus.APPROVED);
		em.getTransaction().begin();
		em.persist(dis);
		em.getTransaction().commit();
		System.out.println(dis);
		assertEquals("BOOKS",ed.helpNeedyPerson(dis));
		
	}
	
	@Test
	void testLogout() {
		EmployeeDaoImpl ed = new EmployeeDaoImpl();
		assertEquals(false, ed.logout());
	}

}

package com.cg.ngoportal.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.DonationDistributionStatus;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.Request;
import com.cg.ngoportal.model.User;
import com.cg.ngoportal.model.UserType;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private boolean loggedIn = true;
	private int employeeId;
	private EntityManagerFactory emf ;
	private EntityManager em;

	public EmployeeDaoImpl() {
		// TODO Auto-generated constructor stub
		
		emf = Persistence.createEntityManagerFactory("Project");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		
		
	}

	@Override
	public int login(String username,String password) throws SQLException, NoSuchEmployeeException {
		// TODO Auto-generated method stub
//		em.getTransaction().begin();
		Query q = em.createQuery("select u from User u where u.username = :userName");
		q.setParameter("userName", username);
		System.out.println("saquib");
		User user = (User) q.getSingleResult();
		//q = em.createQuery("select e from Employee e");
		//List<Employee> emps = q.getResultList();
		//emps.forEach(System.out::println);
		System.out.println(user);
		if (user == null)
			throw new NoSuchEmployeeException();
		
		if (user.getUserType() == UserType.EMPLOYEE) {
			if(password.equals(user.getPassword())) {
				loggedIn = true;
				q = em.createQuery("select e.employeeId from Employee e where e.user.userId = :id");
				q.setParameter("id", user.getUserId());
				employeeId = (int) q.getSingleResult();
				System.out.println("saquib     " + employeeId);
				return 1;
				
			}
		}
		else {
			throw new NoSuchEmployeeException();
		}
		
		
		
		return 0;
	}

	@Override
	public int createNeedyPerson(NeedyPeople person) {
		if (loggedIn) {
			
			if(person.getUser().getUserType() == UserType.NEEDYPERSON) {
				// TODO Auto-generated method stub
//				em.getTransaction().begin(); // to start inserting in the table
				em.persist(person); //to add object data in the table
				em.getTransaction().commit(); //to commit the table
				
				return 1;   
			}
		}
		return 0;
	}

	@Override
	public int deleteNeedyPerson(NeedyPeople person) {
		// TODO Auto-generated method stub
		
		if (loggedIn) {
//			em.getTransaction().begin();
			NeedyPeople npd = em.find(NeedyPeople.class, person.getNeedyPersonId());
			if(npd == null) {
				System.out.println("No Such Needy Person");
				return 0;
				}
			em.remove(npd);
			em.getTransaction().commit();
			return 1;
			
		}
		return 0;
	}

	@Override
	public NeedyPeople readNeedyPeopleById(int id) {
		// TODO Auto-generated method stub
		NeedyPeople np;
		if (loggedIn) {
			np = em.find(NeedyPeople.class, id);
			return np;
		}
		
		return null;
	}

	@Override
	public List<NeedyPeople> readNeedyPeopleByName(String name) {
		if (loggedIn) {
			// TODO Auto-generated method stub
			Query query = em.createQuery("Select n from NeedyPeople n where n.needyPersonName like :name");
			query.setParameter("name", name);
			List<NeedyPeople> list = query.getResultList();
			System.out.println("333333333333");
			list.forEach(System.out::println);
			return list;
		}
		return null;
	}

	@Override
	public List<NeedyPeople> readAllNeedyPeople() {
		if (loggedIn) {
			// TODO Auto-generated method stub
			Query query = em.createQuery("select n from NeedyPeople n");
			List<NeedyPeople> list = query.getResultList();
			list.forEach(System.out::println);
			return list;
		}
		return null;
	}

	@Override
	public String helpNeedyPerson(DonationDistribution distribute) {
		if (loggedIn) {
			// TODO Auto-generated method stub
			if(distribute.getStatus() == DonationDistributionStatus.APPROVED) {
				System.out.println(distribute.getDistributedBy().getEmployeeId() + "    " + this.employeeId);
				if (distribute.getDistributedBy().getEmployeeId() != employeeId) {
					return "Distributing Employee Does not match";
				}
//				em.getTransaction().begin();
				DonationDistribution dd =  em.find(DonationDistribution.class, distribute.getDistributionId());
				dd.setStatus(DonationDistributionStatus.FUND_DISBURSED);
				em.merge(dd);
				em.getTransaction().commit();
				return dd.getItem().getItem().name();
			}
			else 
				return distribute.getStatus().name();
		}
		return null;
	}

	@Override
	public DonationDistribution approveDonationEmployeeLevel(Request request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		
		this.loggedIn = false;
		this.employeeId = (Integer) null;
		return loggedIn;
		
	}

}

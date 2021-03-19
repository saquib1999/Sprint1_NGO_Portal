
package com.cg.ngoportal.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ngoportal.dao.EmployeeDao;

import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.User;
import com.cg.ngoportal.model.UserType;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao repo ;
	private boolean loggedIn = true;
	private int employeeId;
	@Override
	public boolean login(String username,String password) throws NoSuchEmployeeException {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Project");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
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
				return true;
				
			}
		}
		else {
			throw new NoSuchEmployeeException();
		}
		
		return false;
	}

	@Override
	public NeedyPeople addNeedyPerson(NeedyPeople person) {
		// TODO Auto-generated method stub
		return repo.save(person);
			
	}

	@Override
	public NeedyPeople removeNeedyPerson(NeedyPeople person) {
		// TODO Auto-generated method stub
		repo.delete(person);
		return person;
		
	}

	@Override
	public Optional<NeedyPeople> findNeedyPeopleById(int id) {
		//
		return repo.findById(id);
	}

	@Override
	public List<NeedyPeople> findNeedyPeopleByName(String name) {
		// TODO Auto-generated method stub
		if (loggedIn) {
			// TODO Auto-generated method stub
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Project");
			EntityManager em = emf.createEntityManager();
			Query query = em.createQuery("Select n from NeedyPeople n where n.needyPersonName like :name");
			query.setParameter("name", name);
			List<NeedyPeople> list = query.getResultList();
//			System.out.println("333333333333");
//			list.forEach(System.out::println);
			return list;
		}
		return null;
	}

	@Override
	public List<NeedyPeople> findAllNeedyPeople() {
		// TODO Auto-generated method stub
		return (List<NeedyPeople>) repo.findAll();
	}

	@Override
	public String helpNeedyPerson(DonationDistribution distribute) {
		// TODO Auto-generated method stub
		return repo.helpNeedyPerson(distribute);
	}

	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		return repo.logout();
	}

}

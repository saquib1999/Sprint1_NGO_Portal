package com.cg.ngoportal.dao;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import javax.persistence.*;

import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.DonationDistributionStatus;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.User;
import com.cg.ngoportal.model.UserType;

public class AdminDaoImpl implements AdminDao{
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Project");
	EntityManager entityManager = factory.createEntityManager();
	boolean loggedIn;

	public AdminDaoImpl() {
		loggedIn = true;
	}

	@Override
	public int login(String username, String password) throws SQLException{
		Query q = entityManager.createQuery("select u from User u where u.username = :userName");
		q.setParameter("userName", username);
	
		User user = (User) q.getSingleResult();
	
		if (user == null)
			return 0;
	
		if (user.getUserType() == UserType.ADMIN) {
			if(password.equals(user.getPassword())) {
				loggedIn = true;
				q = entityManager.createQuery("select a.adminId from Admin a where a.user.userId = :id");
				q.setParameter("id", user.getUserId());
				int adminId = (int) q.getSingleResult();
				return 1;
			
			}
		}
			return 0;
	}
	
	
	@Override
	public int createEmployee(Employee employee){
		// TODO Auto-generated method stub
		
			if(loggedIn) {
				entityManager.getTransaction().begin();
				entityManager.persist(employee);
				entityManager.getTransaction().commit();
				
				
				return 1;
			}
			else{
				return 0;
			}
	}
	
	@Override
	public Employee updateEmployee(Employee employee){
		// TODO Auto-generated method stub
		Employee emp;
		if(loggedIn){
			
			String queryString = "UPDATE Employee e SET e = :emp WHERE e.employeeId = :eId";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("eId", employee.getEmployeeId());
			query.setParameter("emp", employee);
			
			entityManager.getTransaction().begin();
			int recordsUpdated = query.executeUpdate();
			entityManager.getTransaction().commit();
			
			if(recordsUpdated == 1)
				return employee;
			else
				return null;
		}
		else{
			return null;
		}	
}
	
	@Override
	public int deleteEmployee(int employeeId){
		// TODO Auto-generated method stub
		
		if(loggedIn){
			
			Employee emp = entityManager.find(Employee.class, employeeId);
			entityManager.getTransaction().begin();
			entityManager.remove(emp);
			entityManager.getTransaction().commit();
			return 1;
			
		}
		else{
			return 0;
		}
		
		
	}

	@Override
	public Employee readEmployeeById(int employeeId){
		// TODO Auto-generated method stub
		Employee emp;
		if(loggedIn){
			emp = entityManager.find(Employee.class, employeeId);
			return emp;
		}
		else{
			return null;
		}
	}

	@Override
	public List<Employee> readEmployeeByName(String name){
		// TODO Auto-generated method stub
		
		if(loggedIn){
			Query query = entityManager.createQuery("Select e from Employee e where e.employeeName = :Name");
			query.setParameter("Name", name);
			List<Employee> elist = query.getResultList();
			return elist;
		}
		else{
			return null;
		}
	}

	@Override
	public List<Employee> readAllEmployees(){
		// TODO Auto-generated method stub
		if(loggedIn){
			Query query = entityManager.createQuery("Select e from Employee e");
			List<Employee> elist = query.getResultList();
			return elist;
		}
		else{
			return null;
		}
	}

	@Override
	public boolean approveDonation(DonationDistribution distribution) {
		
		// TODO Auto-generated method stub
		if(loggedIn){
			int cnt = 0;
			DonationDistribution dd;
			Employee emp;
			NeedyPeople np;
			LocalDate now = java.time.LocalDate.now();
			//NeedyPeople Check
			np = entityManager.find(NeedyPeople.class, distribution.getPerson().getNeedyPersonId());
			
			if(np != null)
				cnt++;
			//Employee Check
			emp = entityManager.find(Employee.class, distribution.getDistributedBy().getEmployeeId());
			
			if(emp != null)
				cnt++;
			if(cnt == 2) {
			Query query = entityManager.createQuery("update DonationDistribution dd set dd.approvalOrRejectedDate = now, dd.status = :app where dd.distributionId = distribution.getDistributionId()");
			query.setParameter("app", DonationDistributionStatus.APPROVED);
			return true;
			}
			else {
				Query query = entityManager.createQuery("update DonationDistribution dd set dd.approvalOrRejectedDate = now, dd.status = :rej where dd.distributionId = distribution.getDistributionId()");
				query.setParameter("rej", DonationDistributionStatus.REJECTED);
				return true;
			}
		}
		else{
			return false;
		}

	}
}
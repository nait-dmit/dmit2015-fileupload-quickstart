package ca.nait.dmit.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ca.nait.dmit.domain.Employee;

@Stateless
public class EmployeeService implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	public EmployeeService() {
		super();
	}

	public void createEmployee(Employee employee) {
		entityManager.persist(employee);
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployee() {
		Query query = entityManager.createQuery("SELECT e FROM Employee e");
		return query.getResultList();
	}
	
	public Employee findById(Integer id) {
		Query query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.id = :idValue");
		query.setParameter("idValue", id);
		return (Employee) query.getSingleResult();
	}
	

}


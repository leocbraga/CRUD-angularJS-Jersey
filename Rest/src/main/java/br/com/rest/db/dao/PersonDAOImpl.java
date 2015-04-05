package br.com.rest.db.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.rest.db.exception.DAOException;
import br.com.rest.db.exception.DAOExceptionEnum;
import br.com.rest.model.Person;

@Named
public class PersonDAOImpl implements PersonDAO{
	
	@Inject
	private EntityManager manager;

	public Person save(Person person) throws DAOException {
		if(person == null){
			throw new DAOException(DAOExceptionEnum.INVALID_PERSON);
		}
		
		manager.getTransaction().begin();
		manager.persist(person);
		manager.getTransaction().commit();
		
		return person;
	}

	public Person update(Person person) throws DAOException {
		if(person == null){
			throw new DAOException(DAOExceptionEnum.INVALID_PERSON);
		}
		if(person.getId() == null || person.getId().equals(0)){
			throw new DAOException(DAOExceptionEnum.PERSON_NOT_FOUND);
		}
		
		manager.getTransaction().begin();
		manager.merge(person);
		manager.getTransaction().commit();
		
		return person;
	}

	public List<Person> findByName(String name) throws DAOException {
		if(name == null || name.isEmpty()){
			throw new IllegalArgumentException("Invalid Parameter");
		}
		
		List<Person> persons = null;
		
		try{
			TypedQuery<Person> query = manager.createQuery("select p from Person p where p.name like :name", Person.class);
			query.setParameter("name", "%" + name + "%");
			persons = query.getResultList();
		}catch(NoResultException ex){
			throw new DAOException(DAOExceptionEnum.NO_RECORDS_FOUND);
		}
		
		return persons;
	}

	public Person findById(Integer id) throws DAOException {
		if(id == null || id.equals(0)){
			throw new IllegalArgumentException("Invalid Parameter");
		}
		
		Person person = null;
		
		try{
			person = manager.find(Person.class, id);
		}catch(NoResultException ex){
			throw new DAOException(DAOExceptionEnum.NO_RECORDS_FOUND);
		}
		
		return person;
	}

	public void delete(Person person) throws DAOException {
		if(person == null){
			throw new DAOException(DAOExceptionEnum.INVALID_PERSON);
		}
		if(person.getId() == null || person.getId().equals(0)){
			throw new DAOException(DAOExceptionEnum.PERSON_NOT_FOUND);
		}
		
		manager.getTransaction().begin();
		manager.remove(person);
		manager.getTransaction().commit();
	}

	public List<Person> findAll() throws DAOException {
		List<Person> persons = null;
		
		try{
			TypedQuery<Person> query = manager.createQuery("select p from Person p order by p.name", Person.class);
			persons = query.getResultList();
		}catch(NoResultException ex){
			throw new DAOException(DAOExceptionEnum.NO_RECORDS_FOUND);
		}
		
		return persons;
	}
}

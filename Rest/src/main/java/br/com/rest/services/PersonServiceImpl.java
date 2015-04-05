package br.com.rest.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.rest.db.dao.PersonDAO;
import br.com.rest.db.exception.DAOException;
import br.com.rest.model.Person;

@Path("/person")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@RequestScoped
public class PersonServiceImpl implements PersonService{
	
	@Inject
	private PersonDAO dao; 
	
	public PersonDAO getDao() {
		return dao;
	}
	public void setDao(PersonDAO dao) {
		this.dao = dao;
	}
	
	@POST
	public Response save(Person person) {
		try{
			
			validatePerson(person);
			
			person = dao.save(person);
		}catch(DAOException ex){
			handleException(ex);
		}
		
		return Response.ok().entity(person).build();
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		Person person = null;
		try{
			person = dao.findById(id);
			dao.delete(person);
		}catch(DAOException ex){
			handleException(ex);
		}
	}

	@PUT
	public Person update(Person person) {
		try{
			validatePerson(person);
			
			person = dao.update(person);
		}catch(DAOException ex){
			handleException(ex);
		}
			
		return person;
	}

	@GET
	@Path("{id}")
	public Person findById(@PathParam("id") Integer id) {
		Person person = null;
		
		try{
			person = dao.findById(id);
		}catch(DAOException ex){
			handleException(ex);
		}
		
		return person;
	}

	@GET
	public List<Person> findAll() {
		List<Person> persons = null;
		
		try{
			persons = dao.findAll();
		}catch(DAOException ex){
			handleException(ex);
		}
		return persons;
	}

	@GET
	@Path("name/{name}")
	public List<Person> findByName(@PathParam("name") String name) {
		List<Person> persons = null;
		
		try{
			persons = dao.findByName(name);
		}catch(DAOException ex){
			handleException(ex);
		}
		
		return persons;
	}
	
	/**
	 * Método responsável por manipular as exceções lançadas pelo sistema e adicionar em uma WebApplicationException
	 * @param ex
	 * @throws WebApplicationException
	 */
	private void handleException(DAOException ex) throws WebApplicationException{
		switch (ex.getType()){
			case NO_RECORDS_FOUND:
				throw new WebApplicationException(Status.NOT_FOUND);
			case ONE_MORE_PERSON_FOUND:
				throw new WebApplicationException(Status.CONFLICT);
			case INVALID_PERSON:
				throw new WebApplicationException(Status.NOT_ACCEPTABLE);
			default:
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Método responsável por validar os dados de uma pessoa
	 * @param person
	 * @throws WebApplicationException
	 */
	private void validatePerson(Person person) throws WebApplicationException{
		if(person.getName() == null || person.getName().isEmpty()){
			throw new WebApplicationException(Status.NOT_ACCEPTABLE);
		}
		
		if(person.getAge() == null || person.getAge() <= 0){
			throw new WebApplicationException(Status.NOT_ACCEPTABLE);
		}

	}
}

package br.com.rest.services;

import java.util.List;

import javax.ws.rs.core.Response;

import br.com.rest.model.Person;

/**
 * Interface responsável por especificar as operações do recurso pessoa
 * @author leonardo
 *
 */
public interface PersonService {
	/**
	 * Salvar nova pessoa
	 * @param Person
	 * @return
	 */
	public Response save(Person Person);
	
	/**
	 * Excluir uma pessoa
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * Atualizar uma pessoa
	 * @param Person
	 * @return
	 */
	public Person update(Person Person);
	
	/**
	 * Buscar uma pessoa pelo seu identificador
	 * @param id
	 * @return
	 */
	public Person findById(Integer id);
	
	/**
	 * Buscar todas as pessoas
	 * @return
	 */
	public List<Person> findAll();
	
	/**
	 * Buscar uma pessoa pelo nome
	 * @param nome
	 * @return
	 */
	public List<Person> findByName(String nome);
}

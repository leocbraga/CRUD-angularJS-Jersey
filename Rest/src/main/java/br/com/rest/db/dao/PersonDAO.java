package br.com.rest.db.dao;

import java.util.List;

import br.com.rest.db.exception.DAOException;
import br.com.rest.model.Person;

/**
 * Interface responsável por definir as operações de banco de dados para a entidade pessoa
 * @author leonardo
 *
 */
public interface PersonDAO {
	/**
	 * Salvar nova pessoa
	 * @param person
	 * @return
	 * @throws DAOException
	 */
	public Person save(Person person) throws DAOException;
	
	/**
	 * Atualizar uma pessoa
	 * @param person
	 * @return
	 * @throws DAOException
	 */
	public Person update(Person person) throws DAOException;
	
	/**
	 * Buscar uma pessoa pelo nome
	 * @param name
	 * @return
	 * @throws DAOException
	 */
	public List<Person> findByName(String name) throws DAOException;
	
	/**
	 * Buscar uma pessoa pelo seu identificador
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	public Person findById(Integer id) throws DAOException;
	
	/**
	 * Buscar todas as pessoas
	 * @return
	 * @throws DAOException
	 */
	public List<Person> findAll() throws DAOException;
	
	/**
	 * Excluir uma pessoa
	 * @param person
	 * @throws DAOException
	 */
	public void delete(Person person) throws DAOException;
}

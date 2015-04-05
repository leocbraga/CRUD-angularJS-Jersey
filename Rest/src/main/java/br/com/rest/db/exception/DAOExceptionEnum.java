package br.com.rest.db.exception;

/**
 * Enum para definir os tipos das exceções lançadas pela camada de banco de dados
 * 
 * @author leonardo
 *
 */
public enum DAOExceptionEnum {
	NO_RECORDS_FOUND(1, "No records found"),
	INVALID_PERSON(2, "Invalid Person"),
	PERSON_NOT_FOUND(3, "Person not found"),
	ONE_MORE_PERSON_FOUND(4, "One more person found");
	
	/**
	 * Identificador
	 */
	private Integer id;
	
	/**
	 * Descrição da exceção
	 */
	private String description;
	
	DAOExceptionEnum(Integer id, String description){
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
}

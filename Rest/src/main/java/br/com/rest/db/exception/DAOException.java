package br.com.rest.db.exception;

/**
 * Classe que representa uma exceção da camada de banco de dados
 * 
 * @author leonardo
 *
 */
public class DAOException extends RuntimeException{
	private static final long serialVersionUID = -2399423290674016460L;
	
	private DAOExceptionEnum type;

	public DAOException(String message){
		super(message);
	}
	
	public DAOException(DAOExceptionEnum exceptionEnum){
		this(exceptionEnum.getDescription());
		
		this.type = exceptionEnum;
	}
	
	public DAOExceptionEnum getType() {
		return type;
	}
}

package br.com.rest.db;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe singleton para a obtenção de entity manager (mock)
 * @author leonardo
 *
 */
public class EntityManagerSingleton implements Serializable{

	private static final long serialVersionUID = -3280837903773282488L;
	
	private static final EntityManagerFactory factory;
	
	private static final String PERSISTENCE_UNIT = "restUnit";
	
	static{
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	}

	@Produces
    public static EntityManager getInstance(){
    	if(!factory.isOpen()){
    		throw new RuntimeException("The entity manager factory is closed");
    	}
    	
    	return factory.createEntityManager();
    }
    
    public static void closeInstace(){
    	if(!factory.isOpen()){
    		factory.close();
    	}
    }
}

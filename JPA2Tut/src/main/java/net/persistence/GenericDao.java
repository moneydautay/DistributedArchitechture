package net.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by greenlucky on 1/3/17.
 */
public abstract class GenericDao {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Users");

    public  EntityManager createEntityManager(){
        return factory.createEntityManager();
    }

    public void closeEntityManager(){
        factory.close();
    }
}

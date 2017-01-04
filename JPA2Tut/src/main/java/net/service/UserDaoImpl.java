package net.service;

import net.domain.User;
import net.persistence.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by greenlucky on 1/3/17.
 */
public class UserDaoImpl extends GenericDao{

    /** The application logger */
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(UserDaoImpl.class);
    
    public List<User> createUser(List<User> users){
        LOGGER.debug("Creating users");
        EntityManager em = createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(users);
            tx.commit();
        }catch (Exception ex){
            if(tx != null){
                LOGGER.error("Some thing was wrong. Discard all partial change");
                tx.rollback();
            }
        }finally {
            em.close();
        }
        LOGGER.debug("Created users {}", users);
        return users;
    }

    public User createUser(String firstName, String lastName, Date added){
        LOGGER.debug("Creating user");
        User user = new User(firstName, lastName, added);
        System.out.printf(user.toString());
        EntityManager em = createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(user);
            tx.commit();
        }catch (Exception ex){
            if(tx != null){
                LOGGER.error("Some thing was wrong. Discard all partial change");
                tx.rollback();
            }
        }finally {
            em.close();
        }

        LOGGER.debug("Created user {}", user);
        return user;
    }

    public List<User> findAll(){
        List<User> users = null;
        EntityManager em = createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Query query = em.createQuery("select u from User u");
            users = query.getResultList();
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                LOGGER.error("Some thing was wrong. Discard all partial change");
                tx.rollback();
            }
        } finally {
            em.close();
        }
        return users;
    }

    public User findById(int userId) {
        User user = null;
        EntityManager em = createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            user = em.find(User.class, userId);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                LOGGER.error("Some thing was wrong. Discard all partial change");
                tx.rollback();
            }
        } finally {
            em.close();
        }
        return user;
    }

    public void delete(int userId){
        User user = null;
        EntityManager em = createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            user = em.find(User.class, userId);
            em.remove(user);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                LOGGER.error("Some thing was wrong. Discard all partial change");
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }
}

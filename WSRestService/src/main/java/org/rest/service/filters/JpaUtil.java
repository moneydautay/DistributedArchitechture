package org.rest.service.filters;

import javax.persistence.EntityManager;

/**
 * Created by greenlucky on 1/6/17.
 */
public class JpaUtil {

    public static final ThreadLocal<EntityManager> ENTITY_MANAGER = new ThreadLocal<>();

    /**
     * Return entity manager
     * @return
     */
    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER.get();
    }
}

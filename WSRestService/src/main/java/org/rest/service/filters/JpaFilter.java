package org.rest.service.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import java.io.IOException;

/**
 * Created by greenlucky on 1/6/17.
 */
public class JpaFilter implements Filter{

    /** The application logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(JpaFilter.class);

    private static EntityManagerFactory factory = null;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        LOGGER.info("INTERCEPT REQUEST");
        EntityManager em = null;
        try {
            em = factory.createEntityManager();
            JpaUtil.ENTITY_MANAGER.set(em);
            chain.doFilter(request,response);
            JpaUtil.ENTITY_MANAGER.remove();
        }finally {
            try {
                if(em != null)
                    em.close();
            }catch (Throwable t){
                LOGGER.error("While closing an Entity Manager {}", t);
            }
        }
    }

    @Override
    public void destroy() {
        if(factory != null)
            factory.close();
    }

    @Override
    public void init(FilterConfig config){
        LOGGER.info("Destroy Currently Entity Manager Factory");
        destroy();
        LOGGER.info("Create Entity Manager Factory");
        factory = Persistence.createEntityManagerFactory("Albums");
    }
}

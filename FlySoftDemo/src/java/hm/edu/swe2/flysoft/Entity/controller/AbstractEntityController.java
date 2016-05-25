package hm.edu.swe2.flysoft.entity.controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Represents the abstract form of a entity controller.
 * @author Philipp Chavaroche
 * @version 05.05.2016
 */
public abstract class AbstractEntityController implements Serializable {
    
    private final EntityManagerFactory entityFactory =
        Persistence.createEntityManagerFactory("FlySoftDemoPU");
    
    private EntityManager emIni = entityFactory.createEntityManager();
    
    public EntityManager getEntityManager() {
        if(!emIni.isOpen()){
            return entityFactory.createEntityManager();
        }
        else{
            return emIni;
        }
    }
}

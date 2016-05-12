package hm.edu.swe2.flysoft.controller;

import hm.edu.swe2.flysoft.controller.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.entity.City;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

/**
 * Represents the abstract form of a entity controller.
 * @author Philipp Chavaroche
 * @version 05.05.2016
 */
public abstract class AbstractEntityController implements Serializable {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("FlySoftDemoPU");
    private EntityManager emIni = emf.createEntityManager();
    
    public EntityManager getEntityManager() {
        if(!emIni.isOpen()){
            return emf.createEntityManager();
        }
        else{
            return emIni;
        }
    }
}

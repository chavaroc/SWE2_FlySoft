package hm.edu.swe2.flysoft.entity.controller;

import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.entity.Airport;
import hm.edu.swe2.flysoft.interfaces.IAirport;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Represents the airport entity controller to handle airports in the database.
 * @author Zwen
 * @version 02.6.16
 */
public class AirportEntityController extends AbstractEntityController {
    
    public AirportEntityController(){
        super();
    }
    
    public void createIfNotExist(final IAirport airport){
        Optional<IAirport> optExistingAirline = findAirport(airport.getShortName());
        if(!optExistingAirline.isPresent()){
            // The airline does not exist
            create(airport);
        }
    }
    
    public void create(final IAirport airport){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(airport);
            em.getTransaction().commit();
            System.out.println(airport.toString() + " created.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    public void destroy(final String shortname) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            IAirport airport;
            try {
                airport = em.getReference(Airport.class, shortname);
                airport.getShortName();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The city with id " + shortname + " no longer exists.", enfe);
            }
            em.remove(airport);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public Optional<IAirport> findAirport(final String shortname){
        EntityManager em = getEntityManager();
        IAirport searchedAirport;
        try {
            searchedAirport = em.find(Airport.class, shortname);
        } finally {
            em.close();
        }
        return Optional.ofNullable(searchedAirport);
    } 
    
    public List<IAirport> findAirportEntities() {
        return AirportEntityController.this.findAirportEntities(true, -1, -1);
    }

    public List<IAirport> findAirportEntities(int maxResults, int firstResult) {
        return AirportEntityController.this.findAirportEntities(false, maxResults, firstResult);
    }

    private List<IAirport> findAirportEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Airport.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}

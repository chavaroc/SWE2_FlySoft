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
 * @author Sven Schulz
 * @version 02.6.16
 */
public class AirportEntityController extends AbstractEntityController {
    
    public AirportEntityController(){
        super();
    }
    
    /**
     * Looks after this airport in the database. If it not exists, it would be
     * written in the database.
     * @param airport - new Airport.
     */
    public void createIfNotExist(final IAirport airport){
        Optional<IAirport> optExistingAirline = findAirport(airport.getShortName());
        if(!optExistingAirline.isPresent()){
            // The airline does not exist
            create(airport);
        }
    }
    
    /**
     * Creates a new entry in the database.
     * @param airport - new Airport
     */
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
    
    /**
     * Delets the entry from the database.
     * @param shortname  - shortname of the airport
     * @throws NonexistentEntityException - if no matching airport.
     */
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
    
    /**
     * Searches after the aiport in the database.
     * @param shortname - shortname of the airport.
     * @return the matching airport
     */
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
    
    /**
     * Looks after all airports.
     * @return List of all Airports
     */
    public List<IAirport> findAirportEntities() {
        return AirportEntityController.this.findAirportEntities(true, -1, -1);
    }

    /**
     * Looks after all airports.
     * @return List of all Airports
     */
    public List<IAirport> findAirportEntities(int maxResults, int firstResult) {
        return AirportEntityController.this.findAirportEntities(false, maxResults, firstResult);
    }
     /**
     * Looks after all airports.
     * @return List of all Airports
     */
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

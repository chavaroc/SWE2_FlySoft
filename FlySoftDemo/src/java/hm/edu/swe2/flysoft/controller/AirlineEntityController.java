package hm.edu.swe2.flysoft.controller;

import hm.edu.swe2.flysoft.entity.City;
import hm.edu.swe2.flysoft.controller.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.entity.Airline;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Represents the airline entity controller to handle airlines in the database.
 * @author Philipp Chavaroche
 */
public class AirlineEntityController extends AbstractEntityController {

    public AirlineEntityController() {
        super();
    }
    
    public void createIfNotExist(Airline newAirline){
        Optional<Airline> optExistingAirline = findAirline(newAirline.getAirlineId());
        if(!optExistingAirline.isPresent()){
            // The airline does not exist
            create(newAirline);
        }
        else{
            // airline already exist.
        }
    }

    public void create(Airline airline) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(airline);
            em.getTransaction().commit();
            System.out.println(airline.toString() + " created.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void edit(Airline airline) throws NonexistentEntityException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            airline = em.merge(airline);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = airline.getAirlineId();
                if (findAirline(id) == null) {
                    throw new NonexistentEntityException("The city with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Airline airline;
            try {
                airline = em.getReference(Airline.class, id);
                airline.getAirlineId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The city with id " + id + " no longer exists.", enfe);
            }
            em.remove(airline);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<City> findAirlineEntities() {
        return AirlineEntityController.this.findAirlineEntities(true, -1, -1);
    }

    public List<City> findAirlineEntities(int maxResults, int firstResult) {
        return AirlineEntityController.this.findAirlineEntities(false, maxResults, firstResult);
    }

    private List<City> findAirlineEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Airline.class));
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

    public Optional<Airline> findAirline(Integer id) {
        EntityManager em = getEntityManager();
        Airline searchedAirline;
        try {
            searchedAirline = em.find(Airline.class, id);
        } finally {
            em.close();
        }
        return Optional.ofNullable(searchedAirline);
    }

    public int getAirlineCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Airline> rootTable = cq.from(Airline.class);
            cq.select(em.getCriteriaBuilder().count(rootTable));
            Query query = em.createQuery(cq);
            return ((Long) query.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

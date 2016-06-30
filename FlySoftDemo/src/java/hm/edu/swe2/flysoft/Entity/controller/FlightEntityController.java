package hm.edu.swe2.flysoft.entity.controller;

import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.entity.Flight;
import hm.edu.swe2.flysoft.interfaces.IFlight;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Represents the flight entity controller to handle flights in the database.
 * @author Philipp Chavaroche
 * @version 02.6.16
 */
public class FlightEntityController extends AbstractEntityController {
    
    private final FlightEndPointEntityController endponitController;
    private int counter;
    
    /**
     * Consstruct a new flight enitiy controller
     * @param endponitController 
     */
    public FlightEntityController(FlightEndPointEntityController endponitController) {
        this.endponitController = endponitController;
        counter = 0;
    }
    
    /**
     * Create all given flights.
     * @param flights The flights that should be created.
     */
    public void createAll(final List<IFlight> flights) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        for(int i= 0; i < flights.size(); i++){
            IFlight flight = flights.get(i);
            em.persist(flight);
            counter++;
        }
        em.getTransaction().commit();
    }    
    
    /**
     * Create the given flight.
     * @param flight The flight that should be created.
     */
    public void create(IFlight flight) {
        EntityManager em = getEntityManager();
        
        em.getTransaction().begin();
        em.persist(flight);
        em.getTransaction().commit();        
    }

    /**
     * Destroy a flight with a given id.
     * @param id The id of the flight, that should be deleted.
     * @throws NonexistentEntityException If the flight that should be deleted does not exist.
     */
    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Flight flight;
            try {
                flight = em.getReference(Flight.class, id);
                flight.getFlightId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The flight with id " + id + " no longer exists.", enfe);
            }
            em.remove(flight);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Find a flight with the given id.
     * @param id The id of the flight, that should be found.
     * @return The flight with id or Optional.empty.
     */
    public Optional<IFlight> findFlight(Integer id) {
        EntityManager em = getEntityManager();
        IFlight flight;
        try {
            flight = em.find(Flight.class, id);
        } finally {
            em.close();
        }
        return Optional.ofNullable(flight);
    }

    /**
     * Get the count of all flights.
     * @return The count of all existing flights.
     */
    public int getFlightCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IFlight> rootTable = cq.from(Flight.class);
            cq.select(em.getCriteriaBuilder().count(rootTable));
            Query query = em.createQuery(cq);
            return ((Long) query.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

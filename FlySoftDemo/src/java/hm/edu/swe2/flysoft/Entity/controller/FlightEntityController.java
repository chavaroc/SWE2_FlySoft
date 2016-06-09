package hm.edu.swe2.flysoft.entity.controller;

import hm.edu.swe2.flysoft.Entity.FlightIntoDB;
import hm.edu.swe2.flysoft.entity.City;
import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.entity.Flight;
import hm.edu.swe2.flysoft.interfaces.IFlight;
import hm.edu.swe2.flysoft.interfaces.IFlightEndPoints;
import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import java.util.ArrayList;
import java.util.Map;
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
    
    public void createAll(final ArrayList<FlightIntoDB> flights) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            for(int i= 0; i < flights.size(); i++){
                IFlightEndPoints endpoint = flights.get(i).getEndpoint();
                IFlight flight = flights.get(i).getFlight();
                
                em.persist(endpoint);
                em.flush();
                flight.setFlightEndPointId(endpoint.getFlightEndPointId());
                em.persist(flight);
                System.out.println(counter);
                counter++;
            }
            em.getTransaction().commit();
        } finally {
        }
    }    
    
    public void create(IFlight flight, IFlightEndPoints endPoints) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(endPoints);
            em.flush();
            flight.setFlightEndPointId(endPoints.getFlightEndPointId());
            em.persist(flight);
            em.getTransaction().commit();
            //System.out.println(flight.toString() + " with endpoint "
            //    + endpoints.toString() + " created.");
        } finally {
            /*if (em != null && em.isOpen()) {
                em.close();
            }*/
        }
    }

    public void edit(IFlight city) throws NonexistentEntityException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

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

package hm.edu.swe2.flysoft.controller;

import hm.edu.swe2.flysoft.entity.City;
import hm.edu.swe2.flysoft.controller.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.entity.Flight;
import hm.edu.swe2.flysoft.interfaces.IFlight;
import hm.edu.swe2.flysoft.interfaces.IFlightEndPoints;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Philipp Chavaroche
 */
public class FlightEntityController extends AbstractEntityController {
    
    private FlightEndpointEntityController endponitController;

    public FlightEntityController(FlightEndpointEntityController endponitController) {
        this.endponitController = endponitController;
    }    
    
    public void create(IFlight flight, IFlightEndPoints endpoints) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(endpoints);
            em.flush();
            flight.setFlightendpointId(endpoints.getFlightendpointId());
            em.persist(flight);
            em.getTransaction().commit();
            //System.out.println(flight.toString() + " with endpoint "
            //    + endpoints.toString() + " created.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void edit(IFlight city) throws NonexistentEntityException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        throw new UnsupportedOperationException("Not supported yet.");
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
            Root<City> rt = cq.from(Flight.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

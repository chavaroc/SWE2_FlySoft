package hm.edu.swe2.flysoft.entity.controller;

import hm.edu.swe2.flysoft.entity.City;
import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.entity.Flight;
import hm.edu.swe2.flysoft.interfaces.IFlight;
import hm.edu.swe2.flysoft.interfaces.IFlightEndPoints;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Represents the flight entity controller to handle flights in the database.
 * @author Philipp Chavaroche
 * @version 02.6.16
 */
public class FlightEntityController extends AbstractEntityController {
    
    private FlightEndPointEntityController endPointController;

    public FlightEntityController(FlightEndPointEntityController endPointController) {
        this.endPointController = endPointController;
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

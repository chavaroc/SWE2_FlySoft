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
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Philipp Chavaroche
 */
public class FlightEntityController extends AbstractEntityController {
    
    private final FlightEndpointEntityController endponitController;
    private int counter;
    public FlightEntityController(FlightEndpointEntityController endponitController) {
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
                flight.setFlightendpointId(endpoint.getFlightendpointId());
                em.persist(flight);
                System.out.println(counter);
                counter++;
            }
            em.getTransaction().commit();
        } finally {
        }
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

package hm.edu.swe2.flysoft.controller;

import hm.edu.swe2.flysoft.controller.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.entity.Flightendpoint;
import hm.edu.swe2.flysoft.interfaces.IFlightEndPoints;
import java.util.Optional;
import javax.persistence.EntityManager;

/**
 * Represents the flight end point entity controller to handle flight end points
 * in the database.
 * @author Philipp Chavaroche
 * @version 10.05.16
 */
public class FlightEndpointEntityController extends AbstractEntityController {
    
    public void create(IFlightEndPoints flightendpoint) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(flightendpoint);
            em.getTransaction().commit();
            System.out.println(flightendpoint.toString() + " created.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void edit(IFlightEndPoints flightendpoint) throws NonexistentEntityException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Optional<IFlightEndPoints> findFlightEndpoint(Integer id) {
        EntityManager em = getEntityManager();
        IFlightEndPoints flight;
        try {
            flight = em.find(Flightendpoint.class, id);
        } finally {
            em.close();
        }
        return Optional.ofNullable(flight);
    }
}

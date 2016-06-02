package hm.edu.swe2.flysoft.entity.controller;

import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.entity.FlightEndPoint;
import hm.edu.swe2.flysoft.interfaces.IFlightEndPoints;
import java.util.Optional;
import javax.persistence.EntityManager;

/**
 * Represents the flight end point entity controller to handle flight end points
 * in the database.
 * @author Philipp Chavaroche
 * @version 10.05.16
 */
public class FlightEndPointEntityController extends AbstractEntityController {
    
    public void create(IFlightEndPoints flightEndPoint) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(flightEndPoint);
            em.getTransaction().commit();
            System.out.println(flightEndPoint.toString() + " created.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void edit(IFlightEndPoints flightEndPoint) throws NonexistentEntityException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Optional<IFlightEndPoints> findFlightEndpoint(Integer id) {
        EntityManager em = getEntityManager();
        IFlightEndPoints flight;
        try {
            flight = em.find(FlightEndPoint.class, id);
        } finally {
            em.close();
        }
        return Optional.ofNullable(flight);
    }
}

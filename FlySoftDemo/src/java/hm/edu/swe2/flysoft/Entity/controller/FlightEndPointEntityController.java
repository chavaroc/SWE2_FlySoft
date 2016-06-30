package hm.edu.swe2.flysoft.entity.controller;

import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.entity.FlightEndPoint;
import hm.edu.swe2.flysoft.interfaces.IFlightEndPoints;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            FlightEndPoint flightEndPoint;
            try {
                flightEndPoint = em.getReference(FlightEndPoint.class, id);
                flightEndPoint.getFlightEndPointId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The flightendpoint with id " + id + " no longer exists.", enfe);
            }
            em.remove(flightEndPoint);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public int getFlightEndPointCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IFlightEndPoints> rootTable = cq.from(FlightEndPoint.class);
            cq.select(em.getCriteriaBuilder().count(rootTable));
            Query query = em.createQuery(cq);
            return ((Long) query.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

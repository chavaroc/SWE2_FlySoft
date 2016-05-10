package hm.edu.swe2.flysoft.controller;

import hm.edu.swe2.flysoft.entity.City;
import hm.edu.swe2.flysoft.controller.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.entity.Flight;
import hm.edu.swe2.flysoft.interfaces.IFlight;
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

    
    public void createIfNotExist(IFlight newFlight){
        Optional<IFlight> optExistingAirline = findFlight(newFlight.getFlightId());
        if(!optExistingAirline.isPresent()){
            // The airline does not exist
            create(newFlight);
        }
    }
    
    public void create(IFlight flight) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(flight);
            em.getTransaction().commit();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void edit(IFlight city) throws NonexistentEntityException, Exception {
        
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        
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

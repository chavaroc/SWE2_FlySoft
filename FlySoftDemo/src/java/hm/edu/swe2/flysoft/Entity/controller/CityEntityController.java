package hm.edu.swe2.flysoft.entity.controller;

import hm.edu.swe2.flysoft.entity.City;
import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.interfaces.ICity;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Represents the city entity controller to handle cities in the database.
 * @author Betina Hientz
 * @version 02.6.16
 */
public class CityEntityController extends AbstractEntityController {

    public CityEntityController() {
        super();
    }
    
    public void createIfNotExist(final ICity newCity){
        Optional<ICity> optCity = findCity(newCity.getCityId());
        if(!optCity.isPresent()){
            // The airline does not exist
            create(newCity);
        }
    }

    public void create(ICity city) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(city);
            em.getTransaction().commit();
            System.out.println(city.toString() + " created.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            City city;
            try {
                city = em.getReference(City.class, id);
                city.getCityId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The city with id " + id + " no longer exists.", enfe);
            }
            em.remove(city);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ICity> findCityEntities() {
        return findCityEntities(true, -1, -1);
    }

    public List<ICity> findCityEntities(int maxResults, int firstResult) {
        return findCityEntities(false, maxResults, firstResult);
    }

    private List<ICity> findCityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(City.class));
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

    public Optional<ICity> findCity(Integer id) {
        EntityManager em = getEntityManager();
        ICity foundCity;
        try {
            foundCity = em.find(City.class, id);
        } finally {
            em.close();
        }
        return Optional.ofNullable(foundCity);
    }

    public int getCityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<City> rt = cq.from(City.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

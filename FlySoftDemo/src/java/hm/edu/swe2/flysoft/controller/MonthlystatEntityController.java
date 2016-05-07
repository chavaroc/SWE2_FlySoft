/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.controller;

import hm.edu.swe2.flysoft.controller.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.entity.Monthlystat;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Zwen
 */
public class MonthlystatEntityController extends AbstractEntityController{
    
    public MonthlystatEntityController(){
        super();
    }
    
    public void createIfNotExist(Monthlystat newMonthlystat){
        Optional<Monthlystat> optExistingAirline = findMonthlystat
        (newMonthlystat.getMonthlyStatId());
        if(!optExistingAirline.isPresent()){
            // The airline does not exist
            create(newMonthlystat);
        }
        else{
            // airline already exist.
        }
    }

    public void create(Monthlystat monthlystat) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(monthlystat);
            em.getTransaction().commit();
            System.out.println(monthlystat.toString() + " created.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void edit(Monthlystat monthlystat) throws NonexistentEntityException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            monthlystat = em.merge(monthlystat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = monthlystat.getMonthlyStatId();
                if (findMonthlystat(id) == null) {
                    throw new NonexistentEntityException("The city with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Monthlystat monthlystat;
            try {
                monthlystat = em.getReference(Monthlystat.class, id);
                monthlystat.getMonthlyStatId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The city with id " + id + " no longer exists.", enfe);
            }
            em.remove(monthlystat);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Monthlystat> findMmonthlystatEntities() {
        return MonthlystatEntityController.this.findMmonthlystatEntities(true, -1, -1);
    }

    public List<Monthlystat> findMmonthlystatEntities(int maxResults, int firstResult) {
        return MonthlystatEntityController.this.findMmonthlystatEntities(false, maxResults, firstResult);
    }

    private List<Monthlystat> findMmonthlystatEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Monthlystat.class));
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

    public Optional<Monthlystat> findMonthlystat(Integer id) {
        EntityManager em = getEntityManager();
        Monthlystat searchedAirline;
        try {
            searchedAirline = em.find(Monthlystat.class, id);
        } finally {
            em.close();
        }
        return Optional.ofNullable(searchedAirline);
    }
}

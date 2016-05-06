/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.controller;

import hm.edu.swe2.flysoft.controller.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.entity.Airport;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 * @author Zwen
 */
public class AirportEntityController extends AbstractEntityController{
    
    public AirportEntityController(){
        super();
    }
    
    public void create(final Airport airport){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(airport);
            em.getTransaction().commit();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    public void edit(Airport airport) throws NonexistentEntityException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            airport = em.merge(airport);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String shortname = airport.getShortName();
                if (findAirport(shortname) == null) {
                    throw new NonexistentEntityException("The Airport with the shortname " 
                            + shortname + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }   
    }
    
    public void destroy(final String shortname) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Airport airport;
            try {
                airport = em.getReference(Airport.class, shortname);
                airport.getShortName();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The city with id " + shortname + " no longer exists.", enfe);
            }
            em.remove(airport);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public Airport findAirport(final String shortname){
                EntityManager em = getEntityManager();
        try {
            return em.find(Airport.class, shortname);
        } finally {
            em.close();
        }
    } 
    
    public List<Airport> findAirlineEntities() {
        return findAirportEntities(true, -1, -1);
    }

    public List<Airport> findAirlineEntities(int maxResults, int firstResult) {
        return findAirportEntities(false, maxResults, firstResult);
    }

    private List<Airport> findAirportEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Airport.class));
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
}

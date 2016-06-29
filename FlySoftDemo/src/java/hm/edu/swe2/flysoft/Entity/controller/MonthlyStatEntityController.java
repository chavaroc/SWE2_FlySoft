package hm.edu.swe2.flysoft.entity.controller;

import hm.edu.swe2.flysoft.entity.Airline;
import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.entity.MonthlyStat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Represents the monthly stat entity controller to handle monthly statitics in the database.
 * @author Zwen
 * @version 02.6.16
 */
public class MonthlyStatEntityController extends AbstractEntityController{
    
    private ArrayList<MonthlyStat> monthlystatsList;
    private AirlineEntityController airlineController;
    private AirportEntityController airportController;
    private CityEntityController cityController;
    
    public MonthlyStatEntityController(){
        super();
       monthlystatsList = new ArrayList<>();
       airlineController = new AirlineEntityController();
       airportController = new AirportEntityController();
       cityController = new CityEntityController();
    }
    
    public void createAll(List<MonthlyStat> stats) {
       boolean finish = false;
       for(int i = 0; i < stats.size(); i++){
           if(i == stats.size()-1){
               finish = true;
           }
       }
    }
    
    public void createIfNotExist(MonthlyStat newMonthlyStat){
        Optional<MonthlyStat> optExistingAirline = findMonthlyStat
        (newMonthlyStat.getMonthlyStatId());
        if(!optExistingAirline.isPresent()){
            // The airline does not exist
            create(newMonthlyStat);
        }
        else{
            // airline already exist.
        }
    }

    public void create(MonthlyStat monthlyStat) {
        airlineController.createIfNotExist(new Airline(monthlyStat.getAirlineId()
                ,monthlyStat.getCarrierName(),monthlyStat.getCarrierNameShort()));
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(monthlyStat);
            em.getTransaction().commit();
            System.out.println(monthlyStat.toString() + " created."+ monthlyStat.getYearMonth());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            MonthlyStat monthlyStat;
            try {
                monthlyStat = em.getReference(MonthlyStat.class, id);
                monthlyStat.getMonthlyStatId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The city with id " + id + " no longer exists.", enfe);
            }
            em.remove(monthlyStat);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MonthlyStat> findMonthlystatEntities() {
        return MonthlyStatEntityController.this.findMonthlystatEntities(true, -1, -1);
    }

    public List<MonthlyStat> findMonthlystatEntities(int maxResults, int firstResult) {
        return MonthlyStatEntityController.this.findMonthlystatEntities(false, maxResults, firstResult);
    }

    private List<MonthlyStat> findMonthlystatEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MonthlyStat.class));
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

    public Optional<MonthlyStat> findMonthlyStat(Integer id) {
        EntityManager em = getEntityManager();
        MonthlyStat searchedAirline;
        try {
            searchedAirline = em.find(MonthlyStat.class, id);
        } finally {
            em.close();
        }
        return Optional.ofNullable(searchedAirline);
    }
}

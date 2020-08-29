package facades;

import entities.BankCustomer;
import dto.CustomerDTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private FacadeExample() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public BankCustomer getCustomerByID(int id){
        EntityManager em = getEntityManager();
        try {
            BankCustomer customer = em.find(BankCustomer.class, id);
            return customer;
        } finally {
            em.close();
        }
    }

    public List<CustomerDTO> getCustomerByName(String name){
        EntityManager em = getEntityManager();
        try{
            Query query = em.createQuery("SELECT c FROM BankCustomer c WHERE c.firstname = :name", CustomerDTO.class);
            query.setParameter("firstname", name);
            List<CustomerDTO> result = query.getResultList();
            return result;
        } finally {
            em.close();
        }
    }

    public BankCustomer addCustomer(BankCustomer cust){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        }finally {
            em.close();
        }
    }

    public List<BankCustomer> getAllBankCustomers(){
        EntityManager em = getEntityManager();
        try{
            Query query = em.createQuery("SELECT c FROM BankCustomer c");
            List<BankCustomer> customers = query.getResultList();
            return customers;
        } finally {
            em.close();
        }
    }

}

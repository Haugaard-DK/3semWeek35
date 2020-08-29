package entities;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Mathias
 */
public class MakeDataTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        BankCustomer bc1 = new BankCustomer("Mathias", "Nielsen", "123", 100000);
        BankCustomer bc2 = new BankCustomer("Nicklas", "Nielsen", "321", 50000);
        BankCustomer bc3 = new BankCustomer("Nikolaj", "Larsen", "213", 999999);
        BankCustomer bc4 = new BankCustomer("TestMand", "Tester", "1234", 756432);
        BankCustomer bc5 = new BankCustomer("TestKvinde", "Tester", "4321", 123456);
        try {
            em.getTransaction().begin();
            em.persist(bc1);
            em.persist(bc2);
            em.persist(bc3);
            em.persist(bc4);
            em.persist(bc5);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
}

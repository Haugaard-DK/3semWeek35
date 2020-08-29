/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Mathias
 */
public class CustomerFacadeTest {

    public CustomerFacadeTest() {
    }

    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("pu");
    private static final CustomerFacade CF = CustomerFacade.getCustomerFacade(EMF);

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @Test
    public void testAddCustomer() {
        Customer expected = new Customer("Mathias", "Nielsen");

        Customer actual = CF.addCustomer("Mathias", "Nielsen");

        assertEquals(expected, actual);
    }

    /**
     * Test of findByID method, of class CustomerFacade.
     */
    @Test
    public void testFindByID() {
        int id = 1;
        Customer expected = new Customer("Mathias", "Nielsen");
        expected.setId(id);

        Customer actual = CF.findByID(id);

        assertEquals(expected, actual);
    }

    /**
     * Test of findByLastName method, of class CustomerFacade.
     */
    @Test
    public void testFindByLastName() {

        List<Customer> expected = new ArrayList();
        Customer customer = new Customer("Mathias", "Nielsen");
        expected.add(customer);

        List<Customer> actual = CF.findByLastName("Nielsen");

        assertEquals(expected, actual);
    }

    /**
     * Test of getNumberOfCustomers method, of class CustomerFacade.
     */
    @Test
    public void testGetNumberOfCustomers() {
        int expected = 1;

        int actual = CF.getNumberOfCustomers();

        assertEquals(expected, actual);
    }

    /**
     * Test of allCustomers method, of class CustomerFacade.
     */
    @Test
    public void testAllCustomers() {

        List<Customer> customers = new ArrayList();
        customers.add(new Customer("Mathias", "Nielsen"));
        List<Customer> expected = customers;

        List<Customer> actual = CF.allCustomers();

        assertEquals(expected, actual);
    }

}

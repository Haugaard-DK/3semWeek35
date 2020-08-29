package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class FacadeExampleTest {
    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
    private static final FacadeExample FE = FacadeExample.getFacadeExample(ENF);
    public FacadeExampleTest() {
    }

    @Test
    public void testAddCustomer() {
        BankCustomer expected = new BankCustomer("Mathias", "Nielsen", "123", 1000000);
        
        BankCustomer actual = FE.addCustomer(expected);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetCustomerByID() {
        BankCustomer expected = new BankCustomer("TestMand", "Tester", "1234", 756432.0);
        int id = 1;
        
        BankCustomer actual = FE.getCustomerByID(id);
        
        assertEquals(expected, actual);
    }
/*
    @Test
    public void testGetCustomerByName() {
        BankCustomer bc = new BankCustomer("TestMand", "Tester", "1234", 756432.0);
        int i = 1;
        
        List<CustomerDTO> expected = new ArrayList();
        bc.setId(i);
        CustomerDTO customer = new CustomerDTO(bc);
        expected.add(customer);
        
        List<CustomerDTO> actual = FE.getCustomerByName("TestMand");
        
        assertEquals(expected, actual);

    }

    @Test
    public void testGetAllbankCustomers() {
        int expected = 6;
        
        int actual = FE.getAllBankCustomers().size();
        
        assertEquals(expected, actual);
    } 
*/
    
}

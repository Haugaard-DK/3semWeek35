/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.EmployeeDTO;
import entities.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Mathias
 */
public class EmployeeFacadeTest {

    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("pu");
    private static final EmployeeFacade EF = EmployeeFacade.getEmployeeFacade(EMF);

    public EmployeeFacadeTest() {
    }
    
    @Test
    public void getEmployeeByIdTest() {
        long id = 2;
        Employee expected = new Employee("Mathias", "Hovedbanegården", 100);
        expected.setId(id);
        
        Employee actual = EF.getEmployeeById(id);
        
        assertEquals(expected, actual);
    }

    @Test
    public void getEmployeesByNameTest() {
        String name = "TestMand";
        long i = 1;
        
        List<Employee> expected = new ArrayList();
        Employee employee = new Employee(name, "Bornholm", 321);
        employee.setId(i);
        expected.add(employee);
        
        List<EmployeeDTO> actual = EF.getEmployeesByName(name);
        
        assertEquals(expected, actual);

    }

    @Test
    public void getAllEmployeesTest() {
        int expected = 3;
        
        int actual = EF.getAllEmployees().size();
        
        assertEquals(expected, actual);
    }

    @Test
    public void getEmployeesWithHighestSalaryTest() {
        Employee em = new Employee("TestMand", "Bornholm", 321);
        em.setId(Integer.toUnsignedLong(1));
        
        List<Employee> expected = new ArrayList();
        expected.add(em);
        
        List<EmployeeDTO> actual = EF.getEmployeesWithHighestSalary();
        
        assertEquals(expected, actual);
    }

    @Test
    public void createEmployeeTest() {
        String name = "TestKvinde";
        String address = "Samsø";
        int salary = 123;
        
        Employee expected = new Employee("TestKvinde", "Samsø", 123);
        
        Employee actual = EF.createEmployee(name, address, salary);
        
        assertEquals(expected, actual);
    }

}

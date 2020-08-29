package facades;

import dto.EmployeeDTO;
import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Mathias
 */
public class EmployeeFacade {
    
    private static EntityManagerFactory emf;
    private static EmployeeFacade instance;
    
    private EmployeeFacade(){
    }
    
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Employee getEmployeeById(long id){
        EntityManager em = getEntityManager();
        try {
            Employee employee = em.find(Employee.class, id);
            return employee;
        } finally {
            em.close();
        }
    }
    
    public List<EmployeeDTO> getEmployeesByName(String name){
        EntityManager em = getEntityManager();
        try{
            Query query = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", EmployeeDTO.class);
            query.setParameter("name", name);
            List<EmployeeDTO> employee = query.getResultList();
            return employee;
        } finally {
            em.close();
        }
    }
    public List<EmployeeDTO> getAllEmployees(){
        EntityManager em = getEntityManager();
        try{
            Query query = em.createQuery("SELECT e FROM Employee e", EmployeeDTO.class);
            List<EmployeeDTO> employees = query.getResultList();
            return employees;
        } finally {
            em.close();
        }
    }
    public List<EmployeeDTO> getEmployeesWithHighestSalary(){
        EntityManager em = getEntityManager();
        try{
            Query query = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)", EmployeeDTO.class);
            List<EmployeeDTO> employees = query.getResultList();
            return employees;
        } finally {
            em.close();
        }
    }
    public Employee createEmployee(String name, String address, int salary){
        Employee employee = new Employee(name, address, salary);
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        }finally {
            em.close();
        }
    }    
}

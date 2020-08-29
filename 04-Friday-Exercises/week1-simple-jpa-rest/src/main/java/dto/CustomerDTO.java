package dto;

import entities.BankCustomer;

/**
 *
 * @author Mathias
 */
public class CustomerDTO {

    private int customerID;
    private String fullName;
    private String accountNumber;
    private double balance;

    public CustomerDTO() {
    }

    public CustomerDTO(BankCustomer bc){
        customerID = bc.getId();
        fullName = bc.getFirstName() + bc.getLastName();
        accountNumber = bc.getAccountNumber();
        balance = bc.getBalance();
    }
    
    

}

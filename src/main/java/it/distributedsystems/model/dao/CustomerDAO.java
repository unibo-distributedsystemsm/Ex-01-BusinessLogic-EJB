package it.distributedsystems.model.dao;

import java.util.List;

public interface CustomerDAO {

    public int insertCustomer(Customer customer);

    public int removeCustomerByName(String name);

    public int removeCustomerById(int id);

    public Customer findCustomerByName(String name);

    public Customer findCustomerById(int id);

    public List<Customer> getAllCustomers();
}

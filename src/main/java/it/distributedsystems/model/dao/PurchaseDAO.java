package it.distributedsystems.model.dao;

import java.util.List;

public interface PurchaseDAO {

    public int insertPurchase(Purchase purchase);

    //public int removePurchaseByNumber(int purchaseNumber);

    public int removePurchaseById(int id);

    public Purchase findPurchaseByNumber(int purchaseNumber);

    public Purchase findPurchaseById(int id);

    public List<Purchase> getAllPurchases();

    public List<Purchase> findAllPurchasesByCustomer(Customer customer);

    public List<Purchase> findAllPurchasesByProduct(Product product);
}

package it.distributedsystems.model.dao;

import java.util.List;

public interface ProductDAO {

    public int insertProduct(Product product);

    public int removeProductByNumber(int productNumber);

    public int removeProductById(int id);

    public Product findProductByNumber(int productNumber);

    public Product findProductById(int id);

    public List<Product> getAllProducts();

    public List<Product> getAllProductsByProducer(Producer producer);

    public List<Product> getAllProductsByPurchase(Purchase purchase);

}

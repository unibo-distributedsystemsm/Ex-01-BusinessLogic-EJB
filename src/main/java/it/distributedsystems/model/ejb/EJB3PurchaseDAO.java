package it.distributedsystems.model.ejb;

//import it.distributedsystems.model.logging.OperationLogger;
import it.distributedsystems.model.dao.Customer;
import it.distributedsystems.model.dao.Product;
import it.distributedsystems.model.dao.Purchase;
import it.distributedsystems.model.dao.PurchaseDAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@Stateless
@Local(PurchaseDAO.class)
//@Remote(PurchaseDAO.class)  //-> TODO: serve nella versione clustering???
 public class EJB3PurchaseDAO implements PurchaseDAO {

    @PersistenceContext(unitName = "distributed-systems-demo")
    EntityManager em;

//    @Interceptors(OperationLogger.class)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int insertPurchase(Purchase purchase) {

        //riattacco il customer al contesto di persistenza
        if (purchase.getCustomer()!= null && purchase.getCustomer().getId() > 0)
            purchase.setCustomer(em.merge(purchase.getCustomer()));

        //riattacco i product al contesto di persistenza
        Set<Product> products = new HashSet<Product>();

        if (purchase.getProducts()!= null ){
            for (Product product : purchase.getProducts()){
                if(product != null && product.getId() > 0)
                    products.add(em.merge(product));
            }
            purchase.setProducts(products);
        }

        em.persist(purchase);
        return purchase.getId();
    }

    /*
    @Override
    public int removePurchaseByNumber(int purchaseNumber) {
        Purchase purchase = (Purchase) em.createQuery("DELETE FROM Purchase p WHERE p.purchaseNumber LIKE :num").
                setParameter("num", purchaseNumber).getSingleResult();
        if (purchase!=null){
            int id = purchase.getId();
            //Cancello le associazioni tra l'autore da rimuovere e i libri da lui scritti
            //dalla tabella di associazione Book_Author
            em.createNativeQuery("DELETE FROM Customer WHERE purchase_id ="+id+" ;").executeUpdate();

            em.remove(purchase);

            return id;
        }
        else
            return 0;
    }
    */

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int removePurchaseById(int id){
        Purchase purchase = em.find(Purchase.class, id);
        if (purchase!=null){
            //Cancello le associazioni tra l'ordine da rimuovere e i prodotti inseriti
            //dalla tabella di associazione Purchase_Product
            em.createNativeQuery("DELETE FROM Purchase_Product WHERE purchase_id="+purchase.getId()+" ;").executeUpdate();

            em.remove(purchase);

            return id;
        }
        else
            return 0;
    }

    @Override
    public Purchase findPurchaseByNumber(int purchaseNumber) {
        return (Purchase) em.createQuery("select p from Purchase p where p.purchaseNumber = :num").
                setParameter("num", purchaseNumber).getSingleResult();
    }

    @Override
    public Purchase findPurchaseById(int id) {
        return em.find(Purchase.class, id);
        /*
        return (Purchase) em.createQuery("FROM Purchase p WHERE p.id = :purchaseId").
			setParameter("purchaseId", id).getSingleResult();
         */
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Purchase> getAllPurchases() {
        return em.createQuery("FROM Purchase p ").getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Purchase> findAllPurchasesByCustomer(Customer customer) {
        //Non è stato necessario usare una fetch join (nonostante Purchase.customer fosse mappato LAZY)
        //perché gli id delle entità LAZY collegate vengono comunque mantenuti e sono accessibili
        return em.createQuery("FROM Purchase p WHERE :customerId = p.customer.id").
                setParameter("customerId", customer.getId()).getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Purchase> findAllPurchasesByProduct(Product product) {
        if (product != null){
            em.merge(product); // riattacco il product al contesto di persistenza con una merge
            return em.createQuery("SELECT DISTINCT (p) FROM Purchase p JOIN FETCH p.products JOIN FETCH p.customer WHERE :product MEMBER OF p.products").
                    setParameter("product", product).getResultList();
        }
        else
            return em.createQuery("SELECT DISTINCT (p) FROM Purchase p JOIN FETCH p.products JOIN FETCH p.customer").getResultList();

    }


}


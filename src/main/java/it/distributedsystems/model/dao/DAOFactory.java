package it.distributedsystems.model.dao;

import it.distributedsystems.model.ejb.EJB3DaoFactory;

public abstract class DAOFactory {

    // ---------------------------------------------------------------------------

    public static DAOFactory getDAOFactory(String whichFactory) {
        try {
            return (DAOFactory) new EJB3DaoFactory(); //Class.forName(whichFactory).newInstance();
        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    // ---------------------------------------------------------------------------

    public abstract CustomerDAO getCustomerDAO();

    public abstract PurchaseDAO getPurchaseDAO();

    public abstract ProductDAO getProductDAO();

    public abstract ProducerDAO getProducerDAO();
}

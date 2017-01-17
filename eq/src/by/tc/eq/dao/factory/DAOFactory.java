package by.tc.eq.dao.factory;

import by.tc.eq.dao.*;
import by.tc.eq.dao.impl.*;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final BrandDAO brandDAO = new BrandDAOImpl();
    private final CatalogDAO catalogDAO = new CatalogDAOImpl();
    private final CategoryDAO categoryDAO = new CategoryDAOImpl();
    private final ContractDAO contractDAO = new ContractDAOImpl();
    private final GoodsDAO goodsDAO = new GoodsDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final UserDAO userDAO = new UserDAOImpl();



    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return instance;
    }

    public BrandDAO getBrandDAO() {
        return brandDAO;
    }

    public CatalogDAO getCatalogDAO() {
        return catalogDAO;
    }

    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    public ContractDAO getContractDAO() {
        return contractDAO;
    }

    public GoodsDAO getGoodsDAO() {
        return goodsDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }


}

package by.tc.eq.service;

import by.tc.eq.bean.*;
import by.tc.eq.dao.exception.DAOException;
import by.tc.eq.service.exception.ServiceException;

public interface ShopService {
    void addBrand (Brand brand) throws ServiceException, DAOException;
    void deleteBrand (Brand brand) throws ServiceException, DAOException;
    void changeBrand (Brand newBrand, Brand oldBrand) throws ServiceException, DAOException;


    void addCatalog (Catalog catalog) throws ServiceException, DAOException;
    void deleteCatalog (Catalog catalog) throws ServiceException, DAOException;

    void addCategory (Category category) throws ServiceException, DAOException;
    void deleteCategory (Category category) throws ServiceException, DAOException;


    void addContract (Contract contract) throws ServiceException, DAOException;
    void deleteContract (Contract contract) throws ServiceException, DAOException;
    Contract searchContract (String contractNumber) throws ServiceException, DAOException;


    void addGoods (Goods goods) throws DAOException, ServiceException;
    void deleteGoods (Goods goods) throws DAOException, ServiceException;
    void changeGoods (Goods newGoods, Goods oldGoods) throws ServiceException, DAOException;
    Goods searchGoods (String name, Category category, Brand brand) throws ServiceException, DAOException;


    void addOrder (Order order) throws ServiceException, DAOException;
    void deleteOrder (Order order) throws ServiceException, DAOException;
    Order searchOrder (Contract contract, Goods goods) throws ServiceException, DAOException;


    void addUser (User user) throws DAOException, ServiceException;
    void deleteUser (User user) throws DAOException, ServiceException;
    void changeUser (User oldUser, User newUser) throws DAOException, ServiceException;
    User searchUser (String passportID) throws DAOException, ServiceException;

    void returnOrder (User user, Contract contract, Order order) throws DAOException, ServiceException;

}

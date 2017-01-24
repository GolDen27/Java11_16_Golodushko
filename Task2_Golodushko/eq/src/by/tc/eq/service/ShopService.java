package by.tc.eq.service;

import by.tc.eq.bean.*;
import by.tc.eq.service.exception.ServiceException;

public interface ShopService {
    void addBrand (Brand brand) throws ServiceException;
    void deleteBrand (Brand brand) throws ServiceException;
    Brand searchBrand (String title) throws ServiceException;


    void addCatalog (Catalog catalog) throws ServiceException;
    void deleteCatalog (Catalog catalog) throws ServiceException;

    void addCategory (Category category) throws ServiceException;
    void deleteCategory (Category category) throws ServiceException;
    Category searchCategory (String title, String category) throws ServiceException;


    void addContract (Contract contract) throws ServiceException;
    void deleteContract (Contract contract) throws ServiceException;
    Contract searchContract (String contractNumber) throws ServiceException;


    void addGoods (Goods goods) throws ServiceException;
    void deleteGoods (Goods goods) throws ServiceException;
    void changeGoods (Goods newGoods, Goods oldGoods) throws ServiceException;
    Goods searchGoods (String name, Category category, Brand brand) throws ServiceException;


    void addOrder (Order order) throws ServiceException;
    void deleteOrder (Order order) throws ServiceException;
    Order searchOrder (Contract contract, Goods goods) throws ServiceException;


    void addUser (User user) throws ServiceException;
    void deleteUser (User user) throws ServiceException;
    void changeUser (User oldUser, User newUser) throws ServiceException;
    User searchUser (String passportID) throws ServiceException;

    void returnOrder (User user, Contract contract, Order order) throws ServiceException;

}

package by.tc.eq.dao;

import by.tc.eq.bean.Contract;
import by.tc.eq.bean.Goods;
import by.tc.eq.bean.Order;
import by.tc.eq.dao.exception.DAOException;

public interface OrderDAO {
    void addOrder (Order order) throws DAOException;
    void deleteOrder (Order order) throws DAOException;
    Order searchOrder (Contract contract, Goods goods) throws DAOException;
    void updateOrder (Order newOrder, Order oldOrder) throws DAOException;

}

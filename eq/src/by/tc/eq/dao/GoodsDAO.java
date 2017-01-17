package by.tc.eq.dao;

import by.tc.eq.bean.Brand;
import by.tc.eq.bean.Category;
import by.tc.eq.bean.Goods;
import by.tc.eq.dao.exception.DAOException;

public interface GoodsDAO {
    void addGoods (Goods goods) throws DAOException;
    void deleteGoods (Goods goods) throws DAOException;
    void changeGoods (Goods newGoods, Goods oldGoods) throws DAOException;
    Goods searchGoods (String name, Category category, Brand brand) throws DAOException;
}

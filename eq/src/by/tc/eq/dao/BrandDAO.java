package by.tc.eq.dao;

import by.tc.eq.bean.Brand;
import by.tc.eq.dao.exception.DAOException;

public interface BrandDAO {
    void addBrand (Brand brand) throws DAOException;
    void deleteBrand (Brand brand) throws DAOException;
    void changeBrand (Brand newBrand, Brand oldBrand) throws DAOException;
    Brand searchBrand (String title) throws DAOException;
}

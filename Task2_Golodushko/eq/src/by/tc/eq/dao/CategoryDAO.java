package by.tc.eq.dao;

import by.tc.eq.bean.Catalog;
import by.tc.eq.bean.Category;
import by.tc.eq.dao.exception.DAOException;

public interface CategoryDAO {
    void addCategory (Category category) throws DAOException;
    void deleteCategory (Category category) throws DAOException;
    Category searchCategory (String title, Catalog catalog) throws DAOException;
    Category searchCategory (int idCategory) throws DAOException;
}

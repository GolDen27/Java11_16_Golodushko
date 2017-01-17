package by.tc.eq.dao;

import by.tc.eq.bean.Catalog;
import by.tc.eq.dao.exception.DAOException;

public interface CatalogDAO {
    void addCatalog (Catalog catalog) throws DAOException;
    void deleteCatalog (Catalog catalog) throws DAOException;
    Catalog searchCatalog (String title) throws DAOException;
}

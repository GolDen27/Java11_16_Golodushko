package by.tc.eq.dao.impl;

import by.tc.eq.bean.Catalog;
import by.tc.eq.dao.CatalogDAO;
import by.tc.eq.dao.connection.ConnectionFactory;
import by.tc.eq.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CatalogDAOImpl implements CatalogDAO {
    @Override
    public void addCatalog(Catalog catalog) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "insert into `catalog` (title) values (?)";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1,catalog.getTitle());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error insert catalog");
        }

        connectionFactory.getConnectionPool().putback(connection);

    }

    @Override
    public void deleteCatalog(Catalog catalog) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "delete from `catalog` where `title` = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1,catalog.getTitle());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error delete catalog");
        }

        connectionFactory.getConnectionPool().putback(connection);

    }

    @Override
    public Catalog searchCatalog(String title) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "SELECT `title` from `catalog` where `title`=?";

        Catalog catalog = null;

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1,title);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    catalog = new Catalog();
                    catalog.setTitle(rs.getString("title"));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("error search catalog");
        }

        connectionFactory.getConnectionPool().putback(connection);


        return catalog;
    }
}

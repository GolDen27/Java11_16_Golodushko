package by.tc.eq.dao.impl;

import by.tc.eq.bean.Catalog;
import by.tc.eq.bean.Category;
import by.tc.eq.dao.CategoryDAO;
import by.tc.eq.dao.connection.ConnectionFactory;
import by.tc.eq.dao.exception.DAOException;
import by.tc.eq.dao.factory.DAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public void addCategory(Category category) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "insert into `category` (`title`,`catalog_title`) values (?,?)";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1,category.getTitle());
            st.setString(2, category.getCatalog().getTitle());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error insert category");
        }

        connectionFactory.getConnectionPool().putback(connection);

    }

    @Override
    public void deleteCategory(Category category) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "delete from `category` where `id_category` = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setInt(1,category.getIdCategory());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error delete category");
        }

        connectionFactory.getConnectionPool().putback(connection);

    }


    @Override
    public Category searchCategory(String title, Catalog catalog) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "SELECT `id_category`, `title`, `catalog_title` from `cetegory` where `title`=? and `catalog_title`=?";

        Category category = null;

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1,title);
            st.setString(2,catalog.getTitle());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    category = new Category();
                    category.setIdCategory(rs.getInt("id_category"));
                    category.setTitle(rs.getString("title"));
                    category.setCatalog(DAOFactory.getInstance().getCatalogDAO().searchCatalog(rs.getString("catalog_title")));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("error search category");
        }

        connectionFactory.getConnectionPool().putback(connection);

        return category;

    }

    @Override
    public Category searchCategory(int idCategory) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "SELECT `id_category`, `title`, `catalog_title` from `cetegory` where `id_category`=?";

        Category category = null;

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setInt(1,idCategory);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    category = new Category();
                    category.setIdCategory(rs.getInt("id_category"));
                    category.setTitle(rs.getString("title"));
                    category.setCatalog(DAOFactory.getInstance().getCatalogDAO().searchCatalog(rs.getString("catalog_title")));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("error search category");
        }

        connectionFactory.getConnectionPool().putback(connection);

        return category;
    }
}

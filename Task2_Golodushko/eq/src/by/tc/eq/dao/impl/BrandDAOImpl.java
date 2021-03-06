package by.tc.eq.dao.impl;

import by.tc.eq.bean.Brand;
import by.tc.eq.dao.BrandDAO;
import by.tc.eq.dao.connection.ConnectionFactory;
import by.tc.eq.dao.exception.DAOException;

import java.sql.*;

public class BrandDAOImpl implements BrandDAO {
    @Override
    public void addBrand(Brand brand) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "insert into `brand` (title) values (?)";// это не локальная переменная

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1,brand.getTitle());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error insert brand");
        }

        connectionFactory.getConnectionPool().putback(connection);
        // коннекшн в пул должен возвращаться всегда, а у тебя не всегда
        // опять над кодом даже не подумал

    }

    @Override
    public void deleteBrand(Brand brand) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "delete from `brand` where `title` = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1,brand.getTitle());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error delete brand");
        }

        connectionFactory.getConnectionPool().putback(connection);

    }



    @Override
    public Brand searchBrand(String title) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "SELECT `title` from `brand` where `title`=?";

        Brand brand = null;

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1,title);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    brand = new Brand();
                    brand.setTitle(rs.getString("title"));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("error search brand");
        }

        connectionFactory.getConnectionPool().putback(connection);

        return brand;

    }
}

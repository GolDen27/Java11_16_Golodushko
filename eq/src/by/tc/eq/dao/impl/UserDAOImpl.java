package by.tc.eq.dao.impl;

import by.tc.eq.bean.Brand;
import by.tc.eq.bean.User;
import by.tc.eq.dao.UserDAO;
import by.tc.eq.dao.connection.ConnectionFactory;
import by.tc.eq.dao.exception.DAOException;
import by.tc.eq.dao.factory.DAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public void addUser(User user) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "insert into `user` (`passportID`,`surname`,`name`,`phone`) values (?,?,?,?)";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1, user.getPassportID());
            st.setString(2, user.getSurname());
            st.setString(3, user.getName());
            st.setString(4, user.getPhone());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error insert user");
        }

        connectionFactory.getConnectionPool().putback(connection);

    }

    @Override
    public void deleteUser(User user) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "delete from `user` where `passportID` = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1,user.getPassportID());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error delete user");
        }

        connectionFactory.getConnectionPool().putback(connection);

    }

    @Override
    public void changeUser(User oldUser, User newUser) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "update dom set id= ? name= ? where id= ? ";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setInt(1,23);
            st.setString(2,"sd");
            st.setInt(3,12);
            int a = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionFactory.getConnectionPool().putback(connection);

    }

    @Override
    public User searchUser(String passportID) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "SELECT `passportID`, `surname`, `name`, `phone` from `user` where `passportID`=?";

        User user = null;

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1,passportID);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    user = new User();
                    user.setPassportID(rs.getString("passportID"));
                    user.setSurname(rs.getString("surname"));
                    user.setName(rs.getString("name"));
                    user.setPhone(rs.getString("phone"));

                }
            }
        } catch (SQLException e) {
            throw new DAOException("error search user");
        }

        connectionFactory.getConnectionPool().putback(connection);

        return user;

    }
}

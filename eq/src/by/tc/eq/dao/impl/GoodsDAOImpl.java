package by.tc.eq.dao.impl;

import by.tc.eq.bean.Brand;
import by.tc.eq.bean.Category;
import by.tc.eq.bean.Goods;
import by.tc.eq.dao.GoodsDAO;
import by.tc.eq.dao.connection.ConnectionFactory;
import by.tc.eq.dao.exception.DAOException;
import by.tc.eq.dao.factory.DAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsDAOImpl implements GoodsDAO {

    @Override
    public void addGoods(Goods goods) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "insert into `goods` (`name`,`quantity`,`available_quantity`, `id_category`, `rent_cost`, `fine_cost`,  `brand_title`) values (?,?,?,?,?,?,?,?)";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1, goods.getName());
            st.setInt(2, goods.getQuantity());
            st.setInt(3, goods.getAvailableQuantity());
            st.setInt(4, goods.getCategory().getIdCategory());
            st.setBigDecimal(5, goods.getRentCost());
            st.setBigDecimal(6, goods.getFineCost());
            st.setString(7, goods.getBrand().getTitle());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error insert goods");
        }

        connectionFactory.getConnectionPool().putback(connection);

    }

    @Override
    public void deleteGoods(Goods goods) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "delete from `goods` where `name` = ? and `id_category` = ? and `brand_title` = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1,goods.getName());
            st.setInt(1,goods.getCategory().getIdCategory());
            st.setString(1,goods.getBrand().getTitle());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error delete goods");
        }

        connectionFactory.getConnectionPool().putback(connection);

    }

    @Override
    public void changeGoods(Goods newGoods, Goods oldGoods) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "update `goods` set `name= ?`,`quantity`= ?,`available_quantity`= ?, `id_category`= ?, `rent_cost`= ?, `fine_cost`= ?,  `brand_title`= ?  where `name` = ? and `id_category` = ? and `brand_title` = ? ";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1, newGoods.getName());
            st.setInt(2, newGoods.getQuantity());
            st.setInt(3, newGoods.getAvailableQuantity());
            st.setInt(4, newGoods.getCategory().getIdCategory());
            st.setBigDecimal(5, newGoods.getRentCost());
            st.setBigDecimal(6, newGoods.getFineCost());
            st.setString(7, newGoods.getBrand().getTitle());

            st.setString(8, oldGoods.getName());
            st.setInt(9, oldGoods.getCategory().getIdCategory());
            st.setString(10, oldGoods.getBrand().getTitle());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error update goods");
        }

        connectionFactory.getConnectionPool().putback(connection);

    }

    @Override
    public Goods searchGoods(String name, Category category, Brand brand) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "SELECT `name`, `quantity`, `available_quantity`, `id_category`, `rent_cost`, `fine_cost`,  `brand_title` from `goods` where `name`=? and `id_category`=? and `brand_title`=?";

        Goods goods = null;

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1,name);
            st.setInt(2,category.getIdCategory());
            st.setString(3,brand.getTitle());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    goods = new Goods();
                    goods.setName(rs.getString("name"));
                    goods.setQuantity(rs.getInt("quantity"));
                    goods.setAvailableQuantity(rs.getInt("available_quantity"));
                    goods.setCategory(DAOFactory.getInstance().getCategoryDAO().searchCategory(rs.getInt("id_category")));
                    goods.setRentCost(rs.getBigDecimal("rent_cost"));
                    goods.setFineCost(rs.getBigDecimal("fine_cost"));
                    goods.setBrand(DAOFactory.getInstance().getBrandDAO().searchBrand(rs.getString("brand_title")));

                }
            }
        } catch (SQLException e) {
            throw new DAOException("error search goods");
        }

        connectionFactory.getConnectionPool().putback(connection);

        return goods;

    }
}

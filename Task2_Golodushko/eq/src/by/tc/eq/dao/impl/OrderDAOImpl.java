package by.tc.eq.dao.impl;

import by.tc.eq.bean.Contract;
import by.tc.eq.bean.Goods;
import by.tc.eq.bean.Order;
import by.tc.eq.dao.OrderDAO;
import by.tc.eq.dao.connection.ConnectionFactory;
import by.tc.eq.dao.exception.DAOException;
import by.tc.eq.dao.factory.DAOFactory;

import java.math.BigDecimal;
import java.sql.*;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public void addOrder(Order order) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "insert into `order` (`contract_number`,`start_time`,`end_time`, `return_time`, `discount`, `goods_name`, `goods_category`, `goods_brand`, `is_at_rent`) values (?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1, order.getContract().getContractNumber());
            st.setDate(2, (java.sql.Date) order.getStartTime());
            st.setDate(3, (java.sql.Date) order.getEndTime());
            st.setDate(4, (java.sql.Date) order.getReturnTime());
            st.setDouble(5,  order.getDiscount());
            st.setString(6, order.getGoods().getName());
            st.setInt(7, order.getGoods().getCategory().getIdCategory());
            st.setString(8, order.getGoods().getBrand().getTitle());
            st.setBoolean(9, order.isAtRent());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error insert order");
        }

        Long rentTimeMillis = order.getEndTime().getTime()-order.getEndTime().getTime();
        int rentTimeHour = (int) (rentTimeMillis/3600000);
        BigDecimal cost = order.getGoods().getRentCost().multiply(new BigDecimal(rentTimeHour)).multiply(new BigDecimal(1-order.getDiscount()));

        Contract oldContract = order.getContract();
        Contract newContract = order.getContract();
        newContract.setTotalCost(newContract.getTotalCost().add(cost));


        connectionFactory.getConnectionPool().putback(connection);

    }

    @Override
    public void deleteOrder(Order order) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "delete from `order` where `contract_number` = ? and `goods_name` = ? and `goods_category` = ? and `goods_brand` = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1, order.getContract().getContractNumber());
            st.setString(2, order.getGoods().getName());
            st.setInt(3, order.getGoods().getCategory().getIdCategory());
            st.setString(4, order.getGoods().getBrand().getTitle());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error delete order");
        }

        connectionFactory.getConnectionPool().putback(connection);

    }



    @Override
    public Order searchOrder(Contract contract, Goods goods) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "SELECT `contract_number`, `start_time`, `end_time`, `return_time`, `discount`, `goods_name`, `goods_category`, `goods_brand`, `is_at_rent` from `order` where `contract_number`=? and `goods_name`=? and `goods_category`=? and `goods_brand`=?";

        Order order = null;

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1, contract.getContractNumber());
            st.setString(2, goods.getName());
            st.setInt(3, goods.getCategory().getIdCategory());
            st.setString(4, goods.getBrand().getTitle());

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    order = new Order();
                    order.setContract(DAOFactory.getInstance().getContractDAO().searchContract(rs.getString("contract_number")));
                    order.setStartTime(rs.getDate("srart_time"));
                    order.setEndTime(rs.getDate("end_time"));
                    order.setReturnTime(rs.getDate("return_time"));
                    order.setDiscount(rs.getDouble("discount"));
                    order.setGoods(DAOFactory.getInstance().getGoodsDAO().searchGoods(rs.getString("goods_name"),DAOFactory.getInstance().getCategoryDAO().searchCategory(rs.getInt("goods_category")),DAOFactory.getInstance().getBrandDAO().searchBrand(rs.getString("goods_brand"))));
                    order.setAtRent(rs.getBoolean("is_at_rent"));

                }
            }
        } catch (SQLException e) {
            throw new DAOException("error search order");
        }

        connectionFactory.getConnectionPool().putback(connection);

        return order;

    }

    @Override
    public void updateOrder(Order newOrder, Order oldOrder) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "update `order` set `contract_number= ?`,`start_time`= ?,`end_time`= ?, `return_time`= ?, `discount`= ?, `goods_name`= ?,  `goods_category`= ?, `goods_brand`=?, `is_at_rent`=?  where `contract_number` = ? and `goods_name` = ? and `goods_category` = ? and `goods_brand`=?";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1, newOrder.getContract().getContractNumber());
            st.setDate(2, (Date) newOrder.getStartTime());
            st.setDate(3, (Date) newOrder.getEndTime());
            st.setDate(4, (Date) newOrder.getReturnTime());
            st.setDouble(5, newOrder.getDiscount());
            st.setString(6, newOrder.getGoods().getName());
            st.setInt(7, newOrder.getGoods().getCategory().getIdCategory());
            st.setString(8, newOrder.getGoods().getBrand().getTitle());
            st.setBoolean(9, newOrder.isAtRent());
            st.setString(10, oldOrder.getContract().getContractNumber());
            st.setString(11, oldOrder.getGoods().getName() );
            st.setInt(12,oldOrder.getGoods().getCategory().getIdCategory() );
            st.setString(13,  oldOrder.getGoods().getBrand().getTitle());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error update order");
        }

        connectionFactory.getConnectionPool().putback(connection);

    }
}

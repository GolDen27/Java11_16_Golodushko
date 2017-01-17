package by.tc.eq.dao.impl;

import by.tc.eq.bean.Contract;
import by.tc.eq.dao.ContractDAO;
import by.tc.eq.dao.connection.ConnectionFactory;
import by.tc.eq.dao.exception.DAOException;
import by.tc.eq.dao.factory.DAOFactory;

import java.sql.*;

public class ContractDAOImpl implements ContractDAO {

    @Override
    public void addContract(Contract contract) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "insert into `contract` (`contract_number`,`user_passportID`, `total_cost`, `conclusion_date`) values (?,?,?,?,?)";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1,contract.getContractNumber());
            st.setString(2, contract.getUser().getPassportID());
            st.setString(3,null);
            st.setDate(4, (java.sql.Date) contract.getConclusionDate());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error insert contract");
        }

        connectionFactory.getConnectionPool().putback(connection);

    }

    @Override
    public void deleteContract(Contract contract) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "delete from `contract` where `contract_number` = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1,contract.getContractNumber());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error delete contract");
        }

        connectionFactory.getConnectionPool().putback(connection);

    }


    @Override
    public Contract searchContract(String contractNumber) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "SELECT `contract_number`, `user_passportID`,  `total_cost`, `conclusion_date` from `contract` where `contract_number`=?";

        Contract contract = null;

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1,contractNumber);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    contract = new Contract();
                    contract.setContractNumber(rs.getString("contrct_number"));
                    contract.setUser(DAOFactory.getInstance().getUserDAO().searchUser(rs.getString("user_passportID")));
                    contract.setTotalCost(rs.getBigDecimal("total_cost"));
                    contract.setConclusionDate(rs.getDate("conclusion_date"));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("error search contract");
        }

        connectionFactory.getConnectionPool().putback(connection);

        return contract;

    }

    @Override
    public void chandeContract(Contract newContract, Contract oldContract) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = "update `contract` set `contract_number`=?, `user_passportID`=?, `total_cost`=?, `conclusion_date`=?  where `contract_number`=?";

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1, newContract.getContractNumber());
            st.setString(2, newContract.getUser().getPassportID());
            st.setBigDecimal(3, newContract.getTotalCost());
            st.setDate(4, (Date) newContract.getConclusionDate());
            st.setString(5, oldContract.getContractNumber());
            int a = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error update contract");
        }

        connectionFactory.getConnectionPool().putback(connection);

    }
}

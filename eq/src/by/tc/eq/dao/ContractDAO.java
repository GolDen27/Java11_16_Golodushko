package by.tc.eq.dao;

import by.tc.eq.bean.Contract;
import by.tc.eq.dao.exception.DAOException;

public interface ContractDAO {
    void addContract (Contract contract) throws DAOException;
    void deleteContract (Contract contract) throws DAOException;
    Contract searchContract (String contractNumber) throws DAOException;
    void chandeContract (Contract newContract, Contract oldContract) throws DAOException;
}

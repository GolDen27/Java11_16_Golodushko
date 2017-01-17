package by.tc.eq.service.impl;

import by.tc.eq.bean.*;
import by.tc.eq.dao.exception.DAOException;
import by.tc.eq.dao.factory.DAOFactory;
import by.tc.eq.service.ShopService;
import by.tc.eq.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Date;

public class ShopServiceImpl implements ShopService {

    @Override
    public void addBrand(Brand brand) throws ServiceException, DAOException {
        DAOFactory.getInstance().getBrandDAO().addBrand(brand);
    }

    @Override
    public void deleteBrand(Brand brand) throws ServiceException, DAOException {
        DAOFactory.getInstance().getBrandDAO().deleteBrand(brand);
    }

    @Override
    public void changeBrand(Brand newBrand, Brand oldBrand) throws ServiceException, DAOException {
        DAOFactory.getInstance().getBrandDAO().changeBrand(newBrand,oldBrand);
    }

    @Override
    public void addCatalog(Catalog catalog) throws ServiceException, DAOException {
        DAOFactory.getInstance().getCatalogDAO().addCatalog(catalog);
    }

    @Override
    public void deleteCatalog(Catalog catalog) throws ServiceException, DAOException {
        DAOFactory.getInstance().getCatalogDAO().deleteCatalog(catalog);
    }

    @Override
    public void addCategory(Category category) throws ServiceException, DAOException {
        DAOFactory.getInstance().getCategoryDAO().addCategory(category);
    }

    @Override
    public void deleteCategory(Category category) throws ServiceException, DAOException {
        DAOFactory.getInstance().getCategoryDAO().deleteCategory(category);
    }

    @Override
    public void addContract(Contract contract) throws ServiceException, DAOException {
        DAOFactory.getInstance().getContractDAO().addContract(contract);
    }

    @Override
    public void deleteContract(Contract contract) throws ServiceException, DAOException {
        DAOFactory.getInstance().getContractDAO().deleteContract(contract);
    }

    @Override
    public Contract searchContract(String contractNumber) throws ServiceException, DAOException {
        return DAOFactory.getInstance().getContractDAO().searchContract(contractNumber);
    }

    @Override
    public void addGoods(Goods goods) throws ServiceException, DAOException {
        DAOFactory.getInstance().getGoodsDAO().addGoods(goods);
    }

    @Override
    public void deleteGoods(Goods goods) throws ServiceException, DAOException {
        DAOFactory.getInstance().getGoodsDAO().deleteGoods(goods);
    }

    @Override
    public void changeGoods(Goods newGoods, Goods oldGoods) throws ServiceException, DAOException {
        DAOFactory.getInstance().getGoodsDAO().changeGoods(newGoods, oldGoods);
    }

    @Override
    public Goods searchGoods(String name, Category category, Brand brand) throws ServiceException, DAOException {
        return DAOFactory.getInstance().getGoodsDAO().searchGoods(name, category, brand);
    }

    @Override
    public void addOrder(Order order) throws ServiceException, DAOException {
        DAOFactory.getInstance().getOrderDAO().addOrder(order);
    }

    @Override
    public void deleteOrder(Order order) throws ServiceException, DAOException {
        DAOFactory.getInstance().getOrderDAO().deleteOrder(order);
    }

    @Override
    public Order searchOrder(Contract contract, Goods goods) throws ServiceException, DAOException {
        return DAOFactory.getInstance().getOrderDAO().searchOrder(contract, goods);
    }

    @Override
    public void addUser(User user) throws ServiceException, DAOException {
        DAOFactory.getInstance().getUserDAO().addUser(user);
    }

    @Override
    public void deleteUser(User user) throws ServiceException, DAOException {
        DAOFactory.getInstance().getUserDAO().deleteUser(user);
    }

    @Override
    public void changeUser(User oldUser, User newUser) throws ServiceException, DAOException {
        DAOFactory.getInstance().getUserDAO().changeUser(oldUser, newUser);
    }

    @Override
    public User searchUser(String passportID) throws ServiceException, DAOException {
        return DAOFactory.getInstance().getUserDAO().searchUser(passportID);
    }

    @Override
    public void returnOrder(User user, Contract contract, Order order) throws DAOException, ServiceException {
        try {
            Order workOrder = DAOFactory.getInstance().getOrderDAO().searchOrder(order.getContract(), order.getGoods());
            workOrder.setReturnTime(new Date());
            workOrder.setAtRent(false);
            DAOFactory.getInstance().getOrderDAO().updateOrder(workOrder, order);

            BigDecimal fine = new BigDecimal(0);
            if (order.getReturnTime().getTime() - order.getEndTime().getTime() > 0) {
                fine = order.getGoods().getRentCost().multiply(new BigDecimal((order.getReturnTime().getTime() - order.getEndTime().getTime()) / 3600000));
            }

            Contract workContract = DAOFactory.getInstance().getContractDAO().searchContract(contract.getContractNumber());
            workContract.setTotalCost(workContract.getTotalCost().add(fine));
            DAOFactory.getInstance().getContractDAO().chandeContract(workContract, contract);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }


}

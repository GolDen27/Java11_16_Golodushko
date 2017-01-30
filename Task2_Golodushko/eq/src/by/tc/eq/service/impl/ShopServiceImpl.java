package by.tc.eq.service.impl;

import by.tc.eq.bean.*;
import by.tc.eq.dao.exception.DAOException;
import by.tc.eq.dao.factory.DAOFactory;
import by.tc.eq.service.ShopService;
import by.tc.eq.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Date;

public class ShopServiceImpl implements ShopService {

    // методы сервисов не имеют право отказаться от валидации параметров
    // тебе эта фраза из конспекта или объяснения хоть что-то говорит?????
    // и если говорит, тогда вопрос - ГДЕ?
    @Override
    public void addBrand(Brand brand) throws ServiceException{
        try {
            DAOFactory.getInstance().getBrandDAO().addBrand(brand);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());// ну где же вы насмотрелись такого безобразия
            // я вам что, примеры таких оборачиваний исклюений показывала
            // ну что за бред
            // включай голову и спрашивай объявнения у одногруппников
            // на твоем проекте уже комменты писать надоело
        }
    }

    @Override
    public void deleteBrand(Brand brand) throws ServiceException{
        try {
            DAOFactory.getInstance().getBrandDAO().deleteBrand(brand);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Brand searchBrand(String title) throws ServiceException {
        Brand brand = null;
        try {
             brand = DAOFactory.getInstance().getBrandDAO().searchBrand(title);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());
        }
        return brand;
    }


    @Override
    public void addCatalog(Catalog catalog) throws ServiceException {
        try {
            DAOFactory.getInstance().getCatalogDAO().addCatalog(catalog);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public void deleteCatalog(Catalog catalog) throws ServiceException {
        try {
            DAOFactory.getInstance().getCatalogDAO().deleteCatalog(catalog);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public void addCategory(Category category) throws ServiceException {
        try {
            DAOFactory.getInstance().getCategoryDAO().addCategory(category);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public void deleteCategory(Category category) throws ServiceException {
        try {
            DAOFactory.getInstance().getCategoryDAO().deleteCategory(category);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public Category searchCategory(String title, String catalog) throws ServiceException {
        Category category = null;
        try {
            DAOFactory.getInstance().getCategoryDAO().searchCategory(title,DAOFactory.getInstance().getCatalogDAO().searchCatalog(catalog));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());
        }
        return category;
    }

    @Override
    public void addContract(Contract contract) throws ServiceException {
        try {
            DAOFactory.getInstance().getContractDAO().addContract(contract);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public void deleteContract(Contract contract) throws ServiceException {
        try {
            DAOFactory.getInstance().getContractDAO().deleteContract(contract);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public Contract searchContract(String contractNumber) throws ServiceException {
        try {
            return DAOFactory.getInstance().getContractDAO().searchContract(contractNumber);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public void addGoods(Goods goods) throws ServiceException {
        try {
            DAOFactory.getInstance().getGoodsDAO().addGoods(goods);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public void deleteGoods(Goods goods) throws ServiceException {
        try {
            DAOFactory.getInstance().getGoodsDAO().deleteGoods(goods);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public void changeGoods(Goods newGoods, Goods oldGoods) throws ServiceException {
        try {
            DAOFactory.getInstance().getGoodsDAO().changeGoods(newGoods, oldGoods);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public Goods searchGoods(String name, Category category, Brand brand) throws ServiceException {
        try {
            return DAOFactory.getInstance().getGoodsDAO().searchGoods(name, category, brand);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public void addOrder(Order order) throws ServiceException {
        try {
            DAOFactory.getInstance().getOrderDAO().addOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public void deleteOrder(Order order) throws ServiceException {
        try {
            DAOFactory.getInstance().getOrderDAO().deleteOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public Order searchOrder(Contract contract, Goods goods) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderDAO().searchOrder(contract, goods);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public void addUser(User user) throws ServiceException {
        try {
            DAOFactory.getInstance().getUserDAO().addUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public void deleteUser(User user) throws ServiceException {
        try {
            DAOFactory.getInstance().getUserDAO().deleteUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public void changeUser(User oldUser, User newUser) throws ServiceException {
        try {
            DAOFactory.getInstance().getUserDAO().changeUser(oldUser, newUser);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public User searchUser(String passportID) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().searchUser(passportID);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e.getCause());

        }
    }

    @Override
    public void returnOrder(User user, Contract contract, Order order) throws ServiceException {
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
            throw new ServiceException(e.getMessage(),e.getCause());

        }

    }


}

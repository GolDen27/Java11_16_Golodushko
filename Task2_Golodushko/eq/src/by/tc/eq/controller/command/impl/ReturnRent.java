package by.tc.eq.controller.command.impl;

import by.tc.eq.bean.Contract;
import by.tc.eq.bean.Goods;
import by.tc.eq.bean.Order;
import by.tc.eq.bean.User;
import by.tc.eq.controller.command.Command;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.factory.ServiceFactory;

public class ReturnRent implements Command {
    @Override
    public String execute(String request) {
        String response = null;

        String[] s = request.split(" ");

        User user = new User();
        Contract contract = new Contract();
        Order order = new Order();
        Goods goods = new Goods();

        try {

            user = ServiceFactory.getInstance().getShopService().searchUser(s[1]);

            contract = ServiceFactory.getInstance().getShopService().searchContract(s[2]);

            goods = ServiceFactory.getInstance().getShopService().searchGoods(s[3], ServiceFactory.getInstance().getShopService().searchCategory(s[4],s[5]), ServiceFactory.getInstance().getShopService().searchBrand(s[6]));

            order = ServiceFactory.getInstance().getShopService().searchOrder(contract, goods);


            ServiceFactory.getInstance().getShopService().returnOrder(user, contract, order);
            response = "ok";
        } catch (ServiceException e) {
            response = e.getMessage();
        }

        return response;

    }
}

package by.tc.eq.controller.command.impl;

import by.tc.eq.bean.Contract;
import by.tc.eq.bean.Goods;
import by.tc.eq.bean.Order;
import by.tc.eq.bean.User;
import by.tc.eq.controller.command.Command;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.factory.ServiceFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GiveRent implements Command {
    @Override
    public String execute(String request) {
        String response = null;

        String[] s = request.split(" ");

        User user = new User();
        Contract contract = new Contract();
        Goods goods = new Goods();
        Order order = new Order();

        try {
            user = ServiceFactory.getInstance().getShopService().searchUser(s[1]);

            contract.setConclusionDate(new SimpleDateFormat("yyyy-MM-dd").parse(s[2]));
            contract.setContractNumber(contract.getConclusionDate().toString()+s[3]);
            contract.setUser(user);

            goods = ServiceFactory.getInstance().getShopService().searchGoods(s[4], ServiceFactory.getInstance().getShopService().searchCategory(s[5],s[6]), ServiceFactory.getInstance().getShopService().searchBrand(s[7]));

            order.setGoods(goods);
            order.setContract(contract);
            order.setStartTime(new Date());
            order.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(s[8]));
            order.setAtRent(true);

            contract.setTotalCost(BigDecimal.valueOf((order.getEndTime().getTime()-order.getStartTime().getTime()) / 3600000).multiply(goods.getRentCost()));

            response = "ok";
        } catch (ServiceException e) {
            response = e.getMessage();
        } catch (ParseException e) {
            response = "err date format";
        }

        return response;
    }
}

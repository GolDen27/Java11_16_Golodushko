package by.tc.eq.controller.command.impl;

import by.tc.eq.bean.Order;
import by.tc.eq.controller.command.Command;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.factory.ServiceFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddOrder implements Command {
    @Override
    public String execute(String request) {
        String response = null;

        String[] s = request.split(" ");

        Order order = new Order();
        order.setAtRent(true);
        try {
            order.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(s[1]));
            order.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(s[2]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setDiscount(Double.parseDouble(s[3]));
        try {
            order.setContract(ServiceFactory.getInstance().getShopService().searchContract(s[3]));
        } catch (ServiceException e) {
            e.printStackTrace();
        }


        try {
            ServiceFactory.getInstance().getShopService().addOrder(order);
            response = "ok";
        } catch (ServiceException e) {
            response = e.getMessage();
        }

        return response;
    }
}

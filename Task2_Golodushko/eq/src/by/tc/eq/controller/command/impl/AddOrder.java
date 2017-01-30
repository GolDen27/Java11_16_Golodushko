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
            order.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(s[1]));// что мы должны делать с константными строками?
            order.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(s[2]));
        } catch (ParseException e) {
            e.printStackTrace();// опять 25
            // ну ты хосле написание кода просматривал его с включеннной головой?
        }
        order.setDiscount(Double.parseDouble(s[3]));
        try {
            order.setContract(ServiceFactory.getInstance().getShopService().searchContract(s[3]));
            // у тебя за строчки кода IDE денег запросила?
            // зачем вызовы в такую кучу лепить?
        } catch (ServiceException e) {
            e.printStackTrace();// и т.д.
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

package by.tc.eq.controller.command.impl;

import by.tc.eq.bean.User;
import by.tc.eq.controller.command.Command;
import by.tc.eq.dao.exception.DAOException;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.factory.ServiceFactory;

public class AddUser implements Command{
    @Override
    public String execute(String request) {
        String response = null;

        String[] s = request.split(" ");

        User user = new User();
        user.setPhone(s[1]);
        user.setName(s[2]);
        user.setSurname(s[3]);
        user.setPassportID(s[4]);

        try {
            ServiceFactory.getInstance().getShopService().addUser(user);
            response = "ok";
        } catch (ServiceException e) {
            response = e.getMessage();
        } catch (DAOException e) {
            response = e.getMessage();
        }

        return response;
    }
}

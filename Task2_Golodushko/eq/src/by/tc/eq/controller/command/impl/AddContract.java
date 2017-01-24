package by.tc.eq.controller.command.impl;

import by.tc.eq.bean.Contract;
import by.tc.eq.controller.command.Command;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.factory.ServiceFactory;

import java.util.Date;

public class AddContract implements Command {
    @Override
    public String execute(String request) {
        String response = null;

        String[] s = request.split(" ");

        Contract contract = new Contract();
        contract.setContractNumber(s[1]);
        try {
            contract.setUser(ServiceFactory.getInstance().getShopService().searchUser(s[2]));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        contract.setConclusionDate(new Date());



        try {
            ServiceFactory.getInstance().getShopService().addContract(contract);
            response = "ok";
        } catch (ServiceException e) {
            response = e.getMessage();
        }

        return response;
    }
}

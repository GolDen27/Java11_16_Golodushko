package by.tc.xml.main;

import by.tc.xml.service.exception.ServiceExceptinon;
import by.tc.xml.service.factory.ServiceFactory;

public class Main {
    public static void main(String[] args) {
        try {
            ServiceFactory.getInstance().getxmlService().setFile("src/resources/example.xml");
            String toConsole = "";
            while (!toConsole.equals("end of file")){
                toConsole = ServiceFactory.getInstance().getxmlService().getNext();
                System.out.println(toConsole);
            }

        } catch (ServiceExceptinon serviceExceptinon) {
            System.out.println(serviceExceptinon.getMessage()+ serviceExceptinon.getCause());
        }




    }
}

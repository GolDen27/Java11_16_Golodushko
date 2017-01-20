package by.tc.eq.service.factory;

import by.tc.eq.service.ShopService;
import by.tc.eq.service.impl.ShopServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final ShopService shopService = new ShopServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public ShopService getShopService() {
        return shopService;
    }

}

package factory;

import serviceImpl.CommodityServiceImpl;
import serviceImpl.CounterServiceImpl;
import serviceImpl.OrdersServiceImpl;
import serviceImpl.UserServiceImpl;

public class ServiceFactory {
    public static UserServiceImpl getUserService() {
        return UserServiceImpl.getInstance();
    }

    public static CommodityServiceImpl getCommodityService() {
        return CommodityServiceImpl.getInstance();
    }

    public static OrdersServiceImpl getOrdersService() {
        return OrdersServiceImpl.getInstance();
    }

    public static CounterServiceImpl getCounterService() {
        return CounterServiceImpl.getInstance();
    }

}

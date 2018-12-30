package serviceImpl;

import entity.Orders;
import factory.DaoFactory;
import service.OrdersService;

import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    private static OrdersServiceImpl ordersService = new OrdersServiceImpl();
    public static OrdersServiceImpl getInstance(){
        return ordersService;
    }
    @Override
    public void addOrders(List<Orders> orders) {
        DaoFactory.getOrdersDao().saveOrders(orders);
    }
}

package serviceImpl;

import dao.ordersDao;
import entity.Orders;
//import factory.DaoFactory;
import factory.EJBFactory;
import javaBean.ordersBean;
import service.OrdersService;

import java.util.List;

import static factory.EJBFactory.getEJB;

public class OrdersServiceImpl implements OrdersService {
    private static OrdersServiceImpl ordersService = new OrdersServiceImpl();
    public static OrdersServiceImpl getInstance(){
        return ordersService;
    }
    @Override
    public void addOrders(List<Orders> orders) {
//        DaoFactory.getOrdersDao().saveOrders(orders);
        ordersBean myOrder = (ordersBean) EJBFactory.getEJB("ejb:/ejb/ordersBean!dao.ordersDao");
        myOrder.saveOrders(orders);
    }
}

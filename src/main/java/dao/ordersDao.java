package dao;

import entity.Orders;
import entity.OrdersEntity;

import java.util.List;

public interface ordersDao {
    public void saveOrders(List<OrdersEntity> orders);

    public List<OrdersEntity> findOrders(String username);
}

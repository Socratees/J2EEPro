package dao;

import entity.Orders;

import java.util.List;

public interface ordersDao {
    public void saveOrders(List<Orders> orders);

    public List<Orders> findOrders(String username);
}

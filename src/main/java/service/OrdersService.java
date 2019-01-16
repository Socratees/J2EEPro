package service;

import entity.Orders;
import entity.OrdersEntity;

import java.util.List;

public interface OrdersService {
    public void addOrders(List<OrdersEntity> orders);
}

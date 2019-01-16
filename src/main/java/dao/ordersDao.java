package dao;

import entity.Orders;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

@Remote
public interface ordersDao extends Serializable{
    public void saveOrders(List<Orders> orders);

    public List<Orders> findOrders(String username);
}

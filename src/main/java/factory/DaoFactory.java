package factory;

import dao.commodityDao;
import dao.ordersDao;
import dao.userDao;
import daoImpl.commodityDaoImpl;
import daoImpl.counterDaoImpl;
import daoImpl.ordersDaoImpl;
import daoImpl.userDaoImpl;

public class DaoFactory {
    public static userDaoImpl getUserDao(){
        return userDaoImpl.getInstance();
    }

    public static ordersDaoImpl getOrdersDao(){
        return ordersDaoImpl.getInstance();
    }

    public static commodityDaoImpl getCommodityDao(){
        return commodityDaoImpl.getInstance();
    }

    public static counterDaoImpl getCounterDao(){
        return counterDaoImpl.getInstance();
    }
}

package serviceImpl;

import entity.Commodity;
//import factory.DaoFactory;
import factory.EJBFactory;
import factory.ServiceFactory;
import javaBean.commodityBean;
import service.CommodityService;

import java.util.List;

public class CommodityServiceImpl implements CommodityService {
    private static CommodityServiceImpl commodityService = new CommodityServiceImpl();
    public static CommodityServiceImpl getInstance(){
        return commodityService;
    }
    @Override
    public List<Commodity> getAllCommodity() {
//        return DaoFactory.getCommodityDao().findCommodity();
        commodityBean myCommodity = (commodityBean) EJBFactory.getEJB("ejb:/ejb/commodityBean!dao.commodityDao");
        return myCommodity.findCommodity();
    }
}

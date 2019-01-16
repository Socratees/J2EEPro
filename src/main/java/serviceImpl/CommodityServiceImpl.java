package serviceImpl;

import entity.Commodity;
import entity.CommodityEntity;
import factory.DaoFactory;
import factory.ServiceFactory;
import service.CommodityService;

import java.util.List;

public class CommodityServiceImpl implements CommodityService {
    private static CommodityServiceImpl commodityService = new CommodityServiceImpl();
    public static CommodityServiceImpl getInstance(){
        return commodityService;
    }
    @Override
    public List<CommodityEntity> getAllCommodity() {
        return DaoFactory.getCommodityDao().findCommodity();
    }
}

package dao;

import entity.Commodity;
import entity.CommodityEntity;

import java.util.List;

public interface commodityDao {
    public List<CommodityEntity> findCommodity();

    public void saveCommodity(CommodityEntity commodity);

    public void deleteCommodity(int commodityId);
}

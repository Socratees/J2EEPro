package dao;

import entity.Commodity;

import java.util.List;

public interface commodityDao {
    public List<Commodity> findCommodity();

    public void saveCommodity(Commodity commodity);

    public void deleteCommodity(int commodityId);
}

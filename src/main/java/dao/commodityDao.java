package dao;

import entity.Commodity;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

@Remote
public interface commodityDao extends Serializable{
    public List<Commodity> findCommodity();

    public void saveCommodity(Commodity commodity);

    public void deleteCommodity(int commodityId);
}

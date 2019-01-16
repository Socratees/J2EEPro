package dao;

import entity.Counter;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

@Remote
public interface counterDao extends Serializable{
    public List<Counter> getCounter();

    public void saveCounter(Counter counter);
}

package dao;

import entity.Counter;
import entity.CounterEntity;

import java.util.List;

public interface counterDao {
    public List<CounterEntity> getCounter();

    public void saveCounter(CounterEntity counter);
}

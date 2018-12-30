package dao;

import entity.Counter;

import java.util.List;

public interface counterDao {
    public List<Counter> getCounter();

    public void saveCounter(Counter counter);
}

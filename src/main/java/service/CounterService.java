package service;

import entity.Counter;
import entity.CounterEntity;

import java.util.List;

public interface CounterService {
    public List<CounterEntity> getCounterNum();

    public void addCounterNum(CounterEntity counter);
}

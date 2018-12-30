package service;

import entity.Counter;

import java.util.List;

public interface CounterService {
    public List<Counter> getCounterNum();

    public void addCounterNum(Counter counter);
}

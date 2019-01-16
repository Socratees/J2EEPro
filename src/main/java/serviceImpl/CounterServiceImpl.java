package serviceImpl;

import entity.Counter;
import entity.CounterEntity;
import factory.DaoFactory;
import service.CounterService;

import java.util.List;

public class CounterServiceImpl implements CounterService{
    private static CounterServiceImpl counterService = new CounterServiceImpl();

    public static CounterServiceImpl getInstance(){
        return counterService;
    }

    @Override
    public List<CounterEntity> getCounterNum() {
        return DaoFactory.getCounterDao().getCounter();
    }

    @Override
    public void addCounterNum(CounterEntity counter) {
        DaoFactory.getCounterDao().saveCounter(counter);
    }
}

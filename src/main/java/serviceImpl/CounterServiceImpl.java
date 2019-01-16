package serviceImpl;

import entity.Counter;
import factory.EJBFactory;
import javaBean.counterBean;
import service.CounterService;

import java.util.List;

public class CounterServiceImpl implements CounterService{
    private static CounterServiceImpl counterService = new CounterServiceImpl();

    public static CounterServiceImpl getInstance(){
        return counterService;
    }

    @Override
    public List<Counter> getCounterNum() {
//        return DaoFactory.getCounterDao().getCounter();
        counterBean myCounter = (counterBean) EJBFactory.getEJB("ejb:/ejb/counterBean!dao.counterDao");
        return myCounter.getCounter();
    }

    @Override
    public void addCounterNum(Counter counter) {
//        DaoFactory.getCounterDao().saveCounter(counter);
        counterBean myCounter = (counterBean) EJBFactory.getEJB("ejb:ejb/counterBean!dao.counterDao");
        myCounter.saveCounter(counter);
    }
}

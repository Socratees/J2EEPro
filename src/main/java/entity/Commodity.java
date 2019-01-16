package entity;

import java.io.Serializable;

public class Commodity implements Serializable{
    private int commodityId;
    private String commodityName;
    private double price;
    private double amount;

    public int getCommodityId() {
        return commodityId;
    }
    public void setCommodityId(int commodityId){this.commodityId = commodityId;}

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}

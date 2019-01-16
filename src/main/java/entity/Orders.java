package entity;

import java.io.Serializable;

public class Orders implements Serializable{
    private int orderId;
    private String username;
    private String commodityName;
    private int commodityId;
    private double quantity;

    public void setCommodityId(int commodityId){
        this.commodityId = commodityId;
    }
    public int getCommodityId(){
        return commodityId;
    }

    public int getOrderId(){
        return orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}

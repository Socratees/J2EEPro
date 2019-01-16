package entity;

import javax.persistence.*;

@Entity
@Table(name = "commodity", schema = "j2ee", catalog = "")
public class CommodityEntity {
    private int commodityId;
    private String commodityName;
    private double price;
    private double amount;

    @Id
    @Column(name = "commodityId", nullable = false)
    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    @Basic
    @Column(name = "commodityName", nullable = false, length = 30)
    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommodityEntity that = (CommodityEntity) o;

        if (commodityId != that.commodityId) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (Double.compare(that.amount, amount) != 0) return false;
        if (commodityName != null ? !commodityName.equals(that.commodityName) : that.commodityName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = commodityId;
        result = 31 * result + (commodityName != null ? commodityName.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

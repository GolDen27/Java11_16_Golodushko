package by.tc.eq.bean;

import java.util.Date;

public class Order {
    private Contract contract;
    private Goods goods;
    private Date startTime;
    private Date endTime;
    private Date returnTime;
    private double discount;
    private boolean isAtRent;

    public Order() {
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isAtRent() {
        return isAtRent;
    }

    public void setAtRent(boolean atRent) {
        isAtRent = atRent;
    }


}

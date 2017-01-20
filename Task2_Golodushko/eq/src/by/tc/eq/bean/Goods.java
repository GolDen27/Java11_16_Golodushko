package by.tc.eq.bean;

import java.math.BigDecimal;

public class Goods {
    private String name;
    private int quantity;
    private int availableQuantity;
    private BigDecimal rentCost;
    private BigDecimal fineCost;
    private Category category;
    private Brand brand;


    public Goods() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public BigDecimal getRentCost() {
        return rentCost;
    }

    public void setRentCost(BigDecimal rentCost) {
        this.rentCost = rentCost;
    }


    public BigDecimal getFineCost() {
        return fineCost;
    }

    public void setFineCost(BigDecimal fineCost) {
        this.fineCost = fineCost;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

}

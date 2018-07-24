package com.epam.shop.dto;

import java.math.BigDecimal;

public class ProductDTO {
    private Integer count;
    private String name;
    private BigDecimal price;

    public int getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        if(count!=null){
            return "count:"+count;
        }
        if (name!=null && price!=null){
            return "name:"+name+"| price:"+price;
        }
        return "ProductDTO{" +
                "count=" + count +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

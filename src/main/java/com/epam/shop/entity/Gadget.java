package com.epam.shop.entity;

import com.epam.shop.annotations.TranslationService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Gadget implements Serializable {
    @TranslationService(key = "gadget.serial")
    private String serialNumber;
    @TranslationService(key = "gadget.firm")
    private String firm;
    @TranslationService(key = "gadget.price")
    private BigDecimal price;
    @TranslationService(key = "gadget.model")
    private String model;

    public Gadget() {
    }

    public Gadget(String serialNumber, String firm, BigDecimal price, String model) {
        this.serialNumber = serialNumber;
        this.firm = firm;
        this.price = price;
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gadget)) return false;
        Gadget that = (Gadget) o;
        return price == that.price &&
                Objects.equals(serialNumber, that.serialNumber) &&
                Objects.equals(firm, that.firm) &&
                Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, firm, price, model);
    }

    @Override
    public String toString() {
        return "Gadget{" +
                "serialNumber='" + serialNumber + '\'' +
                ", firm='" + firm + '\'' +
                ", price=" + price +
                ", model='" + model + '\'' +
                '}';
    }
}

package com.epam.shop.entity;

import com.epam.shop.annotations.TranslationService;

import java.math.BigDecimal;
import java.util.Objects;

public class MobileGadget extends Gadget {
    @TranslationService(key = "mobile.battery")
    private String batteryCapacity;

    public MobileGadget() {
    }

    public MobileGadget(String serialNumber, String firm, BigDecimal price, String model, String screenDiagonal) {
        super(serialNumber, firm, price, model);
        this.batteryCapacity = screenDiagonal;
    }

    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setDiagonal(String diagonal) {
        this.batteryCapacity = diagonal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MobileGadget)) return false;
        if (!super.equals(o)) return false;
        MobileGadget that = (MobileGadget) o;
        return Objects.equals(batteryCapacity, that.batteryCapacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), batteryCapacity);
    }


    @Override
    public String toString() {
        return "MobileGadget{" +
                "serialNumber='" + getSerialNumber() + '\'' +
                ", firm='" + getFirm() + '\'' +
                ", price=" + getPrice() +
                ", model='" + getModel() + '\'' +
                ", batteryCapacity='" + batteryCapacity + '\'' +
                '}';
    }
}

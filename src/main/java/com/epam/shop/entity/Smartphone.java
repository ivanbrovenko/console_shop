package com.epam.shop.entity;

import com.epam.shop.annotations.TranslationService;

import java.math.BigDecimal;
import java.util.Objects;

public class Smartphone extends MobileGadget {
    @TranslationService(key = "smartphone.volume")
    private String frontalSpeakerVolume;

    public Smartphone() {
    }

    public Smartphone(String serialNumber, String firm, BigDecimal price, String model, String screenDiagonal, String frontalSpeakerVolume) {
        super(serialNumber, firm, price, model, screenDiagonal);
        this.frontalSpeakerVolume = frontalSpeakerVolume;
    }

    public String getFrontalSpeakerVolume() {
        return frontalSpeakerVolume;
    }

    public void setFrontalSpeakerVolume(String frontalSpeakerVolume) {
        this.frontalSpeakerVolume = frontalSpeakerVolume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Smartphone)) return false;
        if (!super.equals(o)) return false;
        Smartphone that = (Smartphone) o;
        return Objects.equals(frontalSpeakerVolume, that.frontalSpeakerVolume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), frontalSpeakerVolume);
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "serialNumber='" + getSerialNumber() + '\'' +
                ", firm='" + getFirm() + '\'' +
                ", price=" + getPrice() + '\'' +
                ", model='" + getModel() + '\'' +
                ", batteryCapacity='" + getBatteryCapacity() + '\'' +
                ", frontalSpeakerVolume=" + frontalSpeakerVolume + '\'' +
                '}';
    }
}

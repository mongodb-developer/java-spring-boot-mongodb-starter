package com.mongodb.starter.models;

import java.util.Objects;

public class CarEntity {
    private String brand;
    private String model;
    private Float maxSpeedKmH;

    public CarEntity() {
    }

    public CarEntity(String brand, String model, Float maxSpeedKmH) {
        this.brand = brand;
        this.model = model;
        this.maxSpeedKmH = maxSpeedKmH;
    }

    public String getBrand() {
        return brand;
    }

    public CarEntity setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarEntity setModel(String model) {
        this.model = model;
        return this;
    }

    public Float getMaxSpeedKmH() {
        return maxSpeedKmH;
    }

    public CarEntity setMaxSpeedKmH(Float maxSpeedKmH) {
        this.maxSpeedKmH = maxSpeedKmH;
        return this;
    }

    @Override
    public String toString() {
        return "Car{" + "brand='" + brand + '\'' + ", model='" + model + '\'' + ", maxSpeedKmH=" + maxSpeedKmH + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEntity carEntity = (CarEntity) o;
        return Objects.equals(brand, carEntity.brand) && Objects.equals(model, carEntity.model) && Objects.equals(
                maxSpeedKmH, carEntity.maxSpeedKmH);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, maxSpeedKmH);
    }
}

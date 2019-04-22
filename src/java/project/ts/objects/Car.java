package project.ts.objects;


import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rozma
 */
public class Car {
    
    private int idCar;
    private String brand;
    private String model;
    private int yearOfProduction;
    private String fuelType;
    private double engineCapacity;
    private int horsepower;
    private int doors;

    public Car(int idCar, String brand, String model, int yearOfProduction, String fuelType, double engineCapacity, int horsepower, int doors) {
        this.idCar = idCar;
        this.brand = brand;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.fuelType = fuelType;
        this.engineCapacity = engineCapacity;
        this.horsepower = horsepower;
        this.doors = doors;
    }

    public Car() {
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (this.yearOfProduction != other.yearOfProduction) {
            return false;
        }
        if (!Objects.equals(this.brand, other.brand)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.fuelType, other.fuelType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car{" + "idCar=" + idCar + ", brand=" + brand + ", model=" + model + ", yearOfProduction=" + yearOfProduction + ", fuelType=" + fuelType + ", engineCapacity=" + engineCapacity + ", horsepower=" + horsepower + ", doors=" + doors + '}';
    }

    

    
    
    
}

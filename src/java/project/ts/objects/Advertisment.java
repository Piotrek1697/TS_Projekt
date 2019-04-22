package project.ts.objects;

import java.awt.Image;
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rozma
 */
public class Advertisment {
    
    private int idAdvertisment;
    private User idUser;
    private Car idCar;
    private int carMileage;
    private boolean demaged;
    private String vin;
    private BufferedImage image;
    private String description;
    private double price;

    public Advertisment(int idAdvertisment, User idUser, Car idCar, int carMileage, boolean demaged, String vin, BufferedImage image, String description, double price) {
        this.idAdvertisment = idAdvertisment;
        this.idUser = idUser;
        this.idCar = idCar;
        this.carMileage = carMileage;
        this.demaged = demaged;
        this.vin = vin;
        this.image = image;
        this.description = description;
        this.price = price;
    }

    public Advertisment(int idAdvertisment, User idUser, Car idCar, int carMileage, boolean demaged, String vin, String description, double price) {
        this.idAdvertisment = idAdvertisment;
        this.idUser = idUser;
        this.idCar = idCar;
        this.carMileage = carMileage;
        this.demaged = demaged;
        this.vin = vin;
        this.description = description;
        this.price = price;
    }   

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getIdAdvertisment() {
        return idAdvertisment;
    }

    public void setIdAdvertisment(int idAdvertisment) {
        this.idAdvertisment = idAdvertisment;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Car getIdCar() {
        return idCar;
    }

    public void setIdCar(Car idCar) {
        this.idCar = idCar;
    }

    public int getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(int carMileage) {
        this.carMileage = carMileage;
    }

    public boolean isDemaged() {
        return demaged;
    }

    public void setDemaged(boolean demaged) {
        this.demaged = demaged;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Advertisment other = (Advertisment) obj;
        if (this.idAdvertisment != other.idAdvertisment) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.idCar != other.idCar) {
            return false;
        }
        return true;
    }
    
    
    
}

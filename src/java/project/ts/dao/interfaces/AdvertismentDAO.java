/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ts.dao.interfaces;

import java.util.List;
import project.ts.objects.Advertisment;

/**
 *
 * @author Rozma
 */
public interface AdvertismentDAO {
    
   void addAdvertisment (Advertisment advertisment);
    
    Advertisment getAdvertisment(Advertisment advertisment);
    
    List<Advertisment> getAdvertisments();
    
    void updateAdvertisment(Advertisment advertisment);
    
    void deleteAdvertisment(int idAdvertisment);
    
    List<Advertisment> getBrandAdvertisment(String brand);
    
    List<Advertisment> getBrandModalAdvertisment(String brand, String model);
    
    List<Advertisment> getUserAdverts(int idUser);
    
    Advertisment getAdvertisment(int idAdvert);
    
}

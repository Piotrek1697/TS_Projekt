package util;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import javax.imageio.*;
import javax.imageio.stream.*;

/**
 *
 * @author Rozma
 */
public class LoadImage {

    public static BufferedImage readScaled(String path) throws IOException{
        return scaleImage(ImageIO.read(new FileInputStream(path)));
    }
    
    public static BufferedImage readScaled(InputStream inputStream) throws IOException{
        return scaleImage(ImageIO.read(inputStream));
    }
    
    public static BufferedImage read(String path) throws IOException{
        return ImageIO.read(new FileInputStream(path));
    }
    
    public static BufferedImage read(InputStream inputStream) throws IOException{
        return ImageIO.read(inputStream);
    }
    
    public static BufferedImage scaleImage(BufferedImage bufferedImage) {
        double boundSize = 1300;
        int origWidth = bufferedImage.getWidth();
        int origHeight = bufferedImage.getHeight();
        double scale;
        if (origHeight > origWidth) {
            scale = boundSize / origHeight;
        } else {
            scale = boundSize / origWidth;
        }
        //* Don't scale up small images.
        if (scale > 1.0) {
            return (bufferedImage);
        }
        int scaledWidth = (int) (scale * origWidth);
        int scaledHeight = (int) (scale * origHeight);
        Image scaledImage = bufferedImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        // new ImageIcon(image); // load image
        // scaledWidth = scaledImage.getWidth(null);
        // scaledHeight = scaledImage.getHeight(null);
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaledBI.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(scaledImage, 0, 0, null);
        g.dispose();
        return (scaledBI);
    }
}

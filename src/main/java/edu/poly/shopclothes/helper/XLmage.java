/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopclothes.helper;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author pc
 */
public class XLmage {
    public static Image getAppIcon(){
      URL url =  XLmage.class.getResource("/edu/poly/shopclothes/icon/icons8-online-store-48.png");
        return  new ImageIcon(url).getImage();
    }

    
  
    
    public static ImageIcon read(String fileName){
        File path= new File("logos",fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
   
    
    public static void save(File src){
        File dst= new File("logos",src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to =Paths.get(dst.getAbsolutePath());
            Files.copy(from,to,StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    

   
    
}

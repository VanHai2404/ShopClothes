/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopclothes.helper;


import edu.poly.shopclothes.entity.NhanVien;

/**
 *
 * @author pc
 */
public class ShereHelper {
    public static NhanVien USER=null;
    
    
    public static void clear(){
        ShereHelper.USER=null;
    }
    public static boolean isLogin(){
        return ShereHelper.USER !=null;
        
    }
    public static boolean isManager(){
        return ShereHelper.isLogin()&& USER.isVaiTro();
        
    }
}

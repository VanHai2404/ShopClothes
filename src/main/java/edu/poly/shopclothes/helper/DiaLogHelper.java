/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopclothes.helper;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author pc
 */
public class DiaLogHelper {
    public static void alert(Component parent, String content){
        JOptionPane.showMessageDialog(parent,content, "HỆ THÔNG BÀN HÀNG SHOP",JOptionPane.INFORMATION_MESSAGE);
        
    }
    public static boolean confirm(Component parent,String content){
    int result= JOptionPane.showConfirmDialog(parent, content,"HỆ THÔNG BÀN HÀNG SHOP",
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                     return result ==JOptionPane.YES_OPTION;
        
    }
    public static void prompt(Component parent,String content){
        JOptionPane.showInputDialog(parent, content, "HỆ THÔNG BÀN HÀNG SHOP",JOptionPane.INFORMATION_MESSAGE);
    }
       public static void showErrorDialog( Component parent, String content, String title){
        JOptionPane.showMessageDialog(parent,content,title,JOptionPane.ERROR_MESSAGE);
        
    }
        public static void validateEmpty(JTextField field , StringBuilder sb , String errorMessage){
        if(field.getText().equals("")){
            sb.append(errorMessage).append("\n");
            field.setBackground(Color.red);
            field.requestFocus();
            return;
            
            
        } else{
            field.setBackground(Color.white);
        }
    }
    
    
      public static int showConfirmDialog( Component parent, String content, String title){
        int choose =JOptionPane.showConfirmDialog(parent,content,title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        
        return choose;
      }
    
    
    
    public static void validateEmpty(JPasswordField field , StringBuilder sb , String errorMessage){
        String password = new String(field.getPassword());
        
        if(password.equals("")){
            sb.append(errorMessage).append("\n");
            field.setBackground(Color.red);
            field.requestFocus();
            
            
        } else{
            field.setBackground(Color.white);
        }
    }
}

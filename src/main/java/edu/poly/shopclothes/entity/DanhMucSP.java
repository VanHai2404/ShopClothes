/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopclothes.entity;

/**
 *
 * @author pc
 */
public class DanhMucSP {
    
    private int ID_DanhMuc ;
    private String TenDM ;

    public int getID_DanhMuc() {
        return ID_DanhMuc;
    }

    public void setID_DanhMuc(int ID_DanhMuc) {
        this.ID_DanhMuc = ID_DanhMuc;
    }

    public String getTenDM() {
        return TenDM;
    }

    public void setTenDM(String TenDM) {
        this.TenDM = TenDM;
    }

    @Override
    public String toString() {
        return this.TenDM;
    }
    
    
    
}

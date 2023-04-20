/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopclothes.entity;

import edu.poly.shopclothes.helper.XDate;
import java.util.Date;

/**
 *
 * @author pc
 */
public class HoaDon {
    private int MaHD ;
    private String MaKH,GhiChu;
    private String HinhThucTT;
    private String MaNV;
    private float SoLuong,TongTien,TienKhachTra,TienThua;
     private Date NgayLap =XDate.now() ;

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

  

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getHinhThucTT() {
        return HinhThucTT;
    }

    public void setHinhThucTT(String HinhThucTT) {
        this.HinhThucTT = HinhThucTT;
    }

  

    

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public float getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(float SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

    public float getTienKhachTra() {
        return TienKhachTra;
    }

    public void setTienKhachTra(float TienKhachTra) {
        this.TienKhachTra = TienKhachTra;
    }

    public float getTienThua() {
        return TienThua;
    }

    public void setTienThua(float TienThua) {
        this.TienThua = TienThua;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

  
    
    
    
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopclothes.dao;

import edu.poly.shopclothes.entity.HoaDonCT;
import edu.poly.shopclothes.helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class HoaDonCTDao extends ClothesDao<HoaDonCT, Integer> {
    
    String INSERT = "INSERT INTO HDChiTiet (MaHD,MaSP,SoLuong,GiaTien,tenSp) VALUES ((SELECT TOP 1  MaHD FROM DONHANG ORDER BY MAHD DESC),?, ?,?,?)";
    //  String UPDATE ="UPDATE HDChiTiet SET TENSP= ?,SOLUONG=?,SIZE=?,NHASANXUAT=?,HINH=?,GIANHAP=?,GIABAN=?,CHATLIEU=?,MOTA=?,TRANGTHAI=? ,ID_DanhMuc= ? where MASP=?";
    String DELETE = "DELETE FROM HDChiTiet where MaHD= ?";
    String SELECT_ALL = " SELECT * FROM HDChiTiet ";
    String SELECT_BY_ID = "SELECT * FROM HDChiTiet where MaHD= ?";
    
    private int MAHD, MaSP, SoLuong;
    private float giaTien;

    @Override
    public void insert(HoaDonCT entity) {
        JdbcHelper.update(INSERT, entity.getMaSP(), entity.getSoLuong(), entity.getGiaTien(), entity.getTenSP());
    }
    
    @Override
    public void update(HoaDonCT entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<HoaDonCT> SelcetTheoHD(Integer HD) {
        String TimTheoMaHD = "SELECT * FROM HDChiTiet where MaHD=?";
        return this.selectBySql(TimTheoMaHD, HD);
        
    }
    
    @Override
    public void delete(Integer id) {
        JdbcHelper.update(DELETE, id);
    }
    
    @Override
    public HoaDonCT selectById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public List<HoaDonCT> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    protected List<HoaDonCT> selectBySql(String sql, Object... args) {
        List<HoaDonCT> list = new ArrayList<HoaDonCT>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HoaDonCT entity = new HoaDonCT();
                
                entity.setMAHD(rs.getInt("MaHD"));
                entity.setMaSP(rs.getInt("maSP"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setGiaTien(rs.getFloat("Giatien"));
                entity.setTenSP(rs.getString("tenSP"));
                list.add(entity);
                
            }
            rs.getStatement().getConnection().close();
            return list;
            
        } catch (Exception e) {
            
            throw new RuntimeException(e);
        }
    }
    
}

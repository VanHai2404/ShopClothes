/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopclothes.dao;

import edu.poly.shopclothes.entity.NhanVien;
import edu.poly.shopclothes.helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class NhanVienDao extends ClothesDao<NhanVien, String>{
    String INSERT="INSERT INTO NHANVIEN (MANV,MATKHAU,HOTEN,GIOITINH,VAITRO,DIENTHOAI,EMAIL,TRANGTHAI,Hinh ) VALUES (?,?,?,?,?,?,?,?,?)";
   String UPDATE="UPDATE  NHANVIEN SET MATKHAU=?,HOTEN=?,GIOITINH=? ,VAITRO=? ,DIENTHOAI= ?,EMAIL=?,TRANGTHAI=?, Hinh =? WHERE MANV=?";
    String DELETE ="DELETE FROM NHANVIEN WHERE MANV=? ";
    String SELECT_ALL="SELECT * FROM NHANVIEN";
    String SELECT_BY_ID ="SELECT * FROM NHANVIEN WHERE MANV =?";


    @Override
    public void insert(NhanVien entity) {
        JdbcHelper.update(INSERT,entity.getMaNV(),entity.getMatKhau(),entity.getHoTen(),entity.isGioiTinh(),entity.isVaiTro(),entity.getDienThoai(),entity.getEmail(),entity.getTrangThai(),entity.getHinh());
        
    }

    @Override
    public void update(NhanVien entity) {
                JdbcHelper.update(UPDATE,entity.getMatKhau(),entity.getHoTen(),entity.isGioiTinh(),entity.isVaiTro(),entity.getDienThoai(),entity.getEmail(),entity.getTrangThai(),entity.getHinh(),entity.getMaNV());

    }
     public List<NhanVien> selectByKeyWord(String keyword){
        String sql="SELECT * FROM NHANVIEN Where HoTen Like ? ";
        return this.selectBySql(sql,"%" + keyword +"%");
    }
     
       public List<NhanVien> selectGioiTinh(boolean key,boolean kiy){
        String sql="SELECT * FROM NHANVIEN Where GIOITINH= ? and VAITRO =?";
        return this.selectBySql(sql,key,kiy);
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE,id);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien>list =this.selectBySql(SELECT_BY_ID,id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectAll() {
        
        return this.selectBySql(SELECT_ALL);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                entity.setDienThoai(rs.getString("DienThoai"));
                entity.setEmail(rs.getString("Email"));
                entity.setTrangThai(rs.getString("TrangThai"));
                entity.setHinh(rs.getString("Hinh"));
                
                
                list.add(entity);
                
                
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopclothes.dao;

import edu.poly.shopclothes.entity.KhachHang;
import edu.poly.shopclothes.helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */


// private boolean GioiTinh =false;
//    
//    
//    private String MaKH,TenKH,SoDT,Email,DiaChi,TrangThai;
public class KhachHangDao extends ClothesDao<KhachHang,String>{
    
    
     String INSERT="INSERT INTO KHACHHANG (MAKH ,TENKH,SODT,GIOITINH,EMAIL,DIACHI,TRANGTHAI) VALUES (?,?,?,?,?,?,?)";
     String UPDATE="UPDATE KHACHHANG SET TENKH=?,SODT=?,GIOITINH=?,EMAIL=?,DIACHI=?,TRANGTHAI=? WHERE MAKH=?";
     String DELETE="DELETE FROM KHACHHANG WHERE MAKH = ? ";
     String SELCET_ALL ="SELECT * FROM KHACHHANG ";
     String SELECT_BY_ID ="SELECT *FROM KHACHHANG WHERE MAKH = ?  ";
     

    @Override
    public void insert(KhachHang entity) {
        JdbcHelper.update(INSERT,entity.getMaKH(),entity.getTenKH(),entity.getSoDT(),entity.isGioiTinh(),entity.getEmail(),entity.getDiaChi(),entity.getTrangThai());
    }

    @Override
    public void update(KhachHang entity) {
        JdbcHelper.update(UPDATE,entity.getTenKH(),entity.getSoDT(),entity.isGioiTinh(),entity.getEmail(),entity.getDiaChi(),entity.getTrangThai(),entity.getMaKH());
    }
      public List<KhachHang> selectByKeyWord(String keyword){
        String sql="SELECT * FROM KHACHHANG Where TenKH Like ? ";
        return this.selectBySql(sql,"%" + keyword +"%");
    }
        public List<KhachHang> selectByDiaChi(String keyw){
        String sql="SELECT * FROM KHACHHANG Where DIACHI Like ? ";
        return this.selectBySql(sql,"%" + keyw +"%");
    }
       
      

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE,id);
    }

    @Override
    public KhachHang selectById(String id) {
       List<KhachHang> list= this.selectBySql(SELECT_BY_ID,id);
       if(list.isEmpty()){
           return null;
       }
       return list.get(0);
        
      
    }
  

    @Override
    public List<KhachHang> selectAll() {
        return this.selectBySql(SELCET_ALL);
    }

    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
       List<KhachHang> list = new ArrayList<KhachHang>();
       
       
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
               KhachHang entity = new KhachHang();
               entity.setMaKH(rs.getString("MaKH"));
               entity.setTenKH(rs.getString("TenKH"));
               entity.setSoDT(rs.getString("SoDT"));
               entity.setGioiTinh(rs.getBoolean("GioiTinh"));
               entity.setEmail(rs.getString("Email"));
               entity.setDiaChi(rs.getString("DiaChi"));
               entity.setTrangThai(rs.getString("TrangThai"));
               
               list.add(entity);
                
            }
          
          rs.getStatement().getConnection().close();
          return list;
            
            
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
               
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopclothes.dao;

import edu.poly.shopclothes.entity.HoaDon;
import edu.poly.shopclothes.helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class HoaDonDao extends ClothesDao<HoaDon,Integer>{
    String INSERT ="INSERT INTO DONHANG (MANV,SOLUONG,MAKH,TONGTIEN,TIENKHACHTRA,TIENTHUA,HINHTHUCTT,GHICHU,NGAYLAP) VALUES (?,?,?,?,?,?,?,?,?)";
    String UPDATE ="UPDATE DONHANG SET MANV=?,SOLUONG=?,MAKH=?,TONGTIEN=?,TIENKHACHTRA=?,TIENTHUA=?,HINHTHUCTT=?,GHICHU=?,NGAYLAP=? where MAHD=?";
    String DELETE ="DELETE FROM DONHANG where MAHD=?";
    String SELECT_ALL=" SELECT * FROM DONHANG";
    String SELECT_BY_ID="SELECT * FROM DONHANG where MAHD= ?";
      

    @Override
    public void insert(HoaDon entity) {
              JdbcHelper.update(INSERT,entity.getMaNV(),entity.getSoLuong(),entity.getMaKH(),entity.getTongTien(),entity.getTienKhachTra()
                ,entity.getTienThua(),entity.getHinhThucTT(),entity.getGhiChu(),entity.getNgayLap());
    }

    @Override
    public void update(HoaDon entity) {
        JdbcHelper.update(UPDATE,entity.getMaNV(),entity.getSoLuong(),entity.getMaKH(),entity.getTongTien(),entity.getTienKhachTra()
                ,entity.getTienThua(),entity.getHinhThucTT(),entity.getGhiChu(),entity.getNgayLap(),entity.getMaHD());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(DELETE,id);
    }
    
          public List<HoaDon> selectByKeyWord(Integer keyword){
        String sql="SELECT * FROM DONHANG Where MaHD Like ? ";
        return this.selectBySql(sql,"%" + keyword +"%");
    }

    @Override
    public HoaDon selectById(Integer id) {
        List<HoaDon>list=this.selectBySql(SELECT_BY_ID, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HoaDon> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
//        MANV,SOLUONG,MAKH,TONGTIEN,TIENKHACHTRA,TIENTHUA,HINHTHUCTT,GHICHU,TRANGTHAI,NGAYLAP
        List<HoaDon> list=new ArrayList<>();
        try {
            ResultSet rs =JdbcHelper.query(sql, args);
            while (rs.next()) {
            HoaDon entity = new HoaDon();
            entity.setMaHD(rs.getInt("MAHD"));
            entity.setMaNV(rs.getString("MaNV"));
            entity.setMaKH(rs.getString("MaKH"));
            entity.setTongTien(rs.getFloat("TongTien"));
            entity.setTienKhachTra(rs.getFloat("TIENKHACHTRA"));
            entity.setTienThua(rs.getFloat("TIENTHUA"));
            entity.setHinhThucTT(rs.getString("HINHTHUCTT"));
            entity.setGhiChu(rs.getString("GhiChu"));
            entity.setNgayLap(rs.getDate("NgayLap"));
              
            list.add(entity);
                
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        
    }
    
}

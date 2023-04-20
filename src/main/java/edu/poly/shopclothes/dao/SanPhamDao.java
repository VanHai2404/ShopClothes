/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopclothes.dao;

import edu.poly.shopclothes.entity.SanPham;
import edu.poly.shopclothes.helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
// private int MaSP,ID_DanhMuc ,SoLuong;
//    private String TenSP,Size,NhaSanXuat,Hinh,ChatLieu,MoTa,TrangThai;
//    private float GiaNhap,GiaBan;
public class SanPhamDao extends ClothesDao<SanPham, Integer> {

    String INSERT = "INSERT INTO SANPHAM (TENSP,SOLUONG,SIZE,NHASANXUAT,HINH,GIANHAP,GIABAN,CHATLIEU,MOTA,TRANGTHAI,ID_DanhMuc) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    String UPDATE = "UPDATE SANPHAM SET TENSP= ?,SOLUONG=?,SIZE=?,NHASANXUAT=?,HINH=?,GIANHAP=?,GIABAN=?,CHATLIEU=?,MOTA=?,TRANGTHAI=? ,ID_DanhMuc= ? where MASP=?";
    String DELETE = "DELETE FROM SANPHAM where MASP=?";
    String SELECT_ALL = " SELECT * FROM SANPHAM";
    String SELECT_BY_ID = "SELECT * FROM SANPHAM where MASP= ?";

    @Override
    public void insert(SanPham entity) {

        JdbcHelper.update(INSERT, entity.getTenSP(), entity.getSoLuong(), entity.getSize(),
                 entity.getNhaSanXuat(), entity.getHinh(), entity.getGiaNhap(), entity.getGiaBan(), entity.getChatLieu(), entity.getMoTa(), entity.getTrangThai(), entity.getID_DanhMuc());
    }

    @Override
    public void update(SanPham entity) {
        JdbcHelper.update(UPDATE, entity.getTenSP(), entity.getSoLuong(), entity.getSize(),
                 entity.getNhaSanXuat(), entity.getHinh(), entity.getGiaNhap(), entity.getGiaBan(), entity.getChatLieu(), entity.getMoTa(), entity.getTrangThai(), entity.getID_DanhMuc(), entity.getMaSP());

    }

    public List<SanPham> TimKiemSP(String keyword) {
        String TimKiem = "select * from SanPham Where TenSP like ?";
        return this.selectBySql(TimKiem, "%" + keyword + "%");

    }

    public List<SanPham> TimkiemTheoID_DanhMuc(Integer id) {
        String ID = "select * from SanPham where ID_DANHMUC= ?";
        return this.selectBySql(ID, id);
    }

    public void UpdateSL(SanPham entity) {
        String capnhat = "UPDATE SANPHAM SET SoLuong =? WHERE MaSP = ?";
        JdbcHelper.update(capnhat, entity.getSoLuong(), entity.getMaSP());

    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(DELETE, id);
    }

    @Override
    public SanPham selectById(Integer id) {
        List<SanPham> list = this.selectBySql(SELECT_BY_ID, id);
        if (list.isEmpty()) {
            return null;

        }
        return list.get(0);
    }

    @Override
    public List<SanPham> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }

    @Override
    protected List<SanPham> selectBySql(String sql, Object... args) {

        List<SanPham> list = new ArrayList<SanPham>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                SanPham entity = new SanPham();
                entity.setMaSP(rs.getInt("maSP"));
                entity.setTenSP(rs.getString("TenSP"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setSize(rs.getString("Size"));
                entity.setNhaSanXuat(rs.getString("NhaSanXuat"));
                entity.setHinh(rs.getString("Hinh"));
                entity.setGiaNhap(rs.getFloat("GiaNhap"));
                entity.setGiaBan(rs.getFloat("GiaBan"));
                entity.setChatLieu(rs.getString("ChatLieu"));
                entity.setMoTa(rs.getString("MoTa"));
                entity.setTrangThai(rs.getString("TrangThai"));
                entity.setID_DanhMuc(rs.getInt("ID_DanhMuc"));

                list.add(entity);

            }
            rs.getStatement().getConnection().close();
            return list;

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

}

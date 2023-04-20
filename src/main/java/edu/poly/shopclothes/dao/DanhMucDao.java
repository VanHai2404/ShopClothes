/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopclothes.dao;

import edu.poly.shopclothes.entity.DanhMucSP;
import edu.poly.shopclothes.helper.JdbcHelper;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class DanhMucDao extends ClothesDao<DanhMucSP,Integer >{
    String INSERT ="INSERT INTO DANHMUCSP (TENDM) VALUES (?)";
    String UPDATE ="UPDATE DANHMUCSP SET TENDM = ? WHERE ID_DANHMUC=? ";
   String DELETE ="DELETE FROM DANHMUCSP WHERE ID_DANHMUC=? ";
    String SELECT_ALL ="SELECT * FROM DANHMUCSP  ";
    String  SELECT_BY_ID="SELECT * FROM DANHMUCSP WHERE ID_DANHMUC =?";
    String DELETE_Ten ="DELETE FROM DANHMUCSP WHERE TenDM= ?";


    @Override
    public void insert(DanhMucSP entity) {
        JdbcHelper.update(INSERT, entity.getTenDM());
    }

    @Override
    public void update(DanhMucSP entity) {
        JdbcHelper.update(UPDATE,entity.getTenDM(),entity.getID_DanhMuc());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(DELETE, id);
    }
     public void deleteTen(String id) {
        JdbcHelper.update(DELETE, id);
    }
    

    @Override
    public DanhMucSP selectById(Integer id) {
         List<DanhMucSP> list = this.selectBySql(SELECT_BY_ID, id);
        if (list.isEmpty()) {
            return null;
            
        } return list.get(0);
    }

    @Override
    public List<DanhMucSP> selectAll() {
       return this.selectBySql(SELECT_ALL);
    }

    @Override
    protected List<DanhMucSP> selectBySql(String sql, Object... args) {
        List<DanhMucSP> list= new ArrayList<DanhMucSP>();
        
        ResultSet rs;
        try {
            rs = JdbcHelper.query(sql, args);
            while (rs.next()) { 
                DanhMucSP entiyi = new DanhMucSP();
                entiyi.setID_DanhMuc(rs.getInt("ID_DanhMuc"));
                entiyi.setTenDM(rs.getString("TenDM"));
                list.add(entiyi);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
             throw new RuntimeException(ex);
        }
       
    }
    
}

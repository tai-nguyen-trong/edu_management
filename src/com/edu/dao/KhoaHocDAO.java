/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.dao;

import com.edu.entity.KhoaHoc;
import com.edu.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, String> {

    @Override
    public void insert(KhoaHoc entity) {
        String sql = "INSERT INTO KhoaHoc(MaKH, MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao) VALUES (?,?,?,?,?,?,?,?)";
        XJdbc.update(sql,
                entity.getMaKH(),
                entity.getMaCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getNgayKG(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayTao());
    }

    @Override
    public void update(KhoaHoc entity) {
        String sql = "UPDATE KhoaHoc SET MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao WHERE MaKH=?";
        XJdbc.update(sql,
                entity.getMaCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getNgayKG(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayTao(),
                entity.getMaKH());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM KhoaHoc WHERE MaKH=?";
        XJdbc.update(sql, id);
    }

    @Override
    public KhoaHoc selectById(String id) {
        String sql = "SELECT * FROM KhoaHoc WHERE MaKH=?";
        List<KhoaHoc> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<KhoaHoc> selectAll() {
        String sql = "SELECT * FROM KhoaHoc";
        return selectBySql(sql);
    }

    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    KhoaHoc entity = new KhoaHoc();
                    entity.setMaKH(rs.getInt("MaKH"));
                    entity.setMaCD(rs.getString("MaCD"));
                    entity.setHocPhi(rs.getDouble("HocPhi"));
                    entity.setThoiLuong(rs.getInt("ThoiLuong"));
                    entity.setNgayKG(rs.getDate("NgayKG"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setNgayTao(rs.getDate("NgayTao"));
                    list.add(entity);

                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    //Hiển thị những khóa học phụ thuộc vào chuyên đề được chọn
    public List<KhoaHoc> selectByChuyenDe(String maCD){
        String sql="SELECT * FROM KhoaHoc WHERE MaCD=?";
        return selectBySql(sql,maCD);
    }
    
    //Danh sách các năm của những khóa học
    public List<Integer> selectYears(){
        String sql = "SELECT DISTINCT year(NgayKG) FROM KhoaHoc ORDER BY year(NgayKG) DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql);
            while (rs.next()) {                
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

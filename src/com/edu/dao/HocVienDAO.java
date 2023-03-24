/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.dao;

import com.edu.entity.HocVien;
import com.edu.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class HocVienDAO extends EduSysDAO<HocVien, Integer> {

    @Override
    public void insert(HocVien entity) {
        String sql = "INSERT INTO HocVien( MaKH, MaNH, Diem) VALUES (?,?,?)";
        XJdbc.update(sql,
                entity.getMaKH(),
                entity.getMaNH(),
                entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        String sql = "UPDATE HocVien SET MaKH=?, MaNH=?, Diem=? WHERE MaHV=?";
        XJdbc.update(sql,
                entity.getMaKH(),
                entity.getMaNH(),
                entity.getDiem(),
                entity.getMaHV());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM HocVien WHERE MaHV=?";
        XJdbc.update(sql, id);
    }

    @Override
    public HocVien selectById(Integer id) {
        String sql = "SELECT * FROM HocVien WHERE MaHV=?";
        List<HocVien> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<HocVien> selectAll() {
        String sql = "SELECT * FROM HocVien";
        return selectBySql(sql);
    }

    @Override
    protected List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    HocVien entity = new HocVien();
                    entity.setMaHV(rs.getInt("MaHV"));
                    entity.setMaKH(rs.getInt("MaKH"));
                    entity.setMaNH(rs.getString("MaNH"));
                    entity.setDiem(rs.getDouble("Diem"));
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

    //lấy học viên của khóa học đó
    public List<HocVien> selectByKhoaHoc(int maKH) {
        String sql = "SELECT * FROM HocVien WHERE MaKH = ?";
        return this.selectBySql(sql, maKH);
    }
}

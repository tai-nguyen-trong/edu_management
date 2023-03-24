package com.edu.dao;

import com.edu.utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    //
    private List<Object[]> getListOfArray(String sql, String[] cols, Object...args){
        try {
            List<Object[]> list=new ArrayList<>(); //tạo 1 danh sách chứa các mảng
            ResultSet rs = XJdbc.query(sql, args); //Gọi query đọc qua từ ResultSet (Xem lại bài 3, Stored Procedure)
            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for(int i=0; i<cols.length; i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    //{CALL sp_BangDiem(@MaKH)}
    //{MaNH, HoTen, Diem)}
    public List<Object[]> getBangDiem(Integer makh){
        String sql = "{CALL sp_BangDiem (?)}";
        String[] cols = {"MaNH", "HoTen", "Diem"};
        return this.getListOfArray(sql, cols, makh);
    }
    
    //{CALL sp_LuongNguoiHoc()}
    //{Nam, SoLuong, DauTien, CuoiCung)}
    public List<Object[]> getLuongNguoiHoc(){
        String sql = "{CALL sp_LuongNguoiHoc}";
        String[] cols = {"Nam", "SoLuong", "DauTien", "CuoiCung"};
        return this.getListOfArray(sql, cols);
    }
    
    //{CALL sp_DiemChuyenDe()}
    //{ChuyenDe, SoHV, ThapNhat, CaoNhat, TrungBinh)}
    public List<Object[]> getDiemChuyenDe(){
        String sql = "{CALL sp_DiemChuyenDe}";
        String[] cols = {"ChuyenDe", "SoHV", "ThapNhat", "CaoNhat", "TrungBinh"};
        return this.getListOfArray(sql, cols);
    }
    
    //{CALL sp_DoanhThu(@Nam)}
    //{ChuyenDe, SoKH, SoHV, DoanhThu, ThapNhat, CaoNhat, TrungBinh)}
    public List<Object[]> getDoanhThu(int nam){
        String sql = "{CALL sp_DoanhThu (?)}";
        String[] cols = {"ChuyenDe", "SoKH", "SoHV",  "DoanhThu", "ThapNhat", "CaoNhat", "TrungBinh"};
        return this.getListOfArray(sql, cols, nam);
    }
}


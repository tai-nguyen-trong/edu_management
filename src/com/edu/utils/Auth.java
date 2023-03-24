package com.edu.utils;

import com.edu.entity.NhanVien;

//hỗ trợ quản lí thông tin đăng nhập
public class Auth {
    /**
     * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
     */
    //duy trì user đăng nhập vào hệ thống
    public static NhanVien user = null;
    /**
     * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
     */
    public static void clear() {
        Auth.user = null;
    }
    /**
     * Kiểm tra xem đăng nhập hay chưa
     */
    public static boolean isLogin() {
        return Auth.user != null;
    }
     /**
     * Kiểm tra xem có phải là trưởng phòng hay không
     */
    //trưởng phòng được phép xóa và xem doanh thu
    public static boolean isManager() {
        return Auth.isLogin() && user.getVaiTro();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.DangNhap;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class DangNhapController {
    private final Connection<DangNhap> dbContext;
     private DangNhap model;
    
    public DangNhapController(DangNhap model) {
        this.model = model;
        dbContext = new Connection<>(); // Khởi tạo đối tượng Connection
    }
    public boolean DangNhap(){
        String query = "SELECT tenTK, matKhau FROM qlpk1.taikhoan WHERE tenTK=? AND matKhau=?";
        
        DangNhap result = dbContext.fetchOne(query, rs -> {
            try {
                // Nếu tài khoản tồn tại, trả về đối tượng DangNhap
                return new DangNhap(
                        rs.getString("tenTK"),
                        rs.getString("matKhau")
                );
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }, model.getTenTK(), model.getMatKhau());

        // Nếu kết quả trả về không null, nghĩa là tài khoản và mật khẩu đúng
        return result != null;
    }
    }

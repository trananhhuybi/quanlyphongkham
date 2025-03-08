/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Thuoc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.UIManager.getInt;
import static javax.swing.UIManager.getString;

/**
 *
 * @author Administrator
 */
public class ThuocController {

    private final Connection<Thuoc> dbContext; // Khởi tạo dbContext
    private static final Logger LOGGER = Logger.getLogger(ThuocController.class.getName());

    // Constructor để khởi tạo dbContext
    public ThuocController() {
        dbContext = new Connection<>(); // Khởi tạo đối tượng Connection
    }

    // Phương thức lấy tất cả dữ liệu thuốc
    public List<Thuoc> getALLThuoc() {
        List<Thuoc> thuocs = new ArrayList<>();
        String query = "SELECT * FROM qlpk1.Thuoc"; // Truy vấn lấy danh sách thuốc

        thuocs = dbContext.fetchAll(query, sr -> {
            try {
                // Chuyển dữ liệu từ ResultSet thành đối tượng Thuoc
                return new Thuoc(
                        sr.getInt("id"),
                        sr.getString("tenThuoc"),
                        sr.getInt("soLuong"),
                        sr.getString("donViTinh"),
                        sr.getFloat("gia")
                );
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                return null;
            }
        });

        return thuocs; // Trả về danh sách thuốc
    }

    // Thêm vào trong lớp ThuocController
    public Thuoc getThuocById(int id) {
        String query = "SELECT * FROM qlpk1.Thuoc WHERE id = ?";  // Truy vấn để lấy thông tin thuốc theo ID

        // Gọi phương thức fetchOne từ dbContext và sử dụng mapper để chuyển dữ liệu ResultSet thành đối tượng Thuoc
        return dbContext.fetchOne(query, sr -> {
            try {
                return new Thuoc(
                        sr.getInt("id"),
                        sr.getString("tenThuoc"),
                        sr.getInt("soLuong"),
                        sr.getString("donViTinh"),
                        sr.getFloat("gia")
                );
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                return null;
            }
        }, id);
    }
    
    public Thuoc getThuocByName(String name) {
        String query = "SELECT * FROM qlpk1.Thuoc WHERE tenThuoc LIKE ?";  

        return dbContext.fetchOne(query, sr -> {
            try {
                return new Thuoc(
                        sr.getInt("id"),
                        sr.getString("tenThuoc"),
                        sr.getInt("soLuong"),
                        sr.getString("donViTinh"),
                        sr.getFloat("gia")
                );
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                return null;
            }
        }, "%"+name+"%");
    }

    public boolean addThuoc(Thuoc thuoc) {
        String query = "INSERT INTO qlpk1.Thuoc (tenThuoc, soLuong, donViTinh, gia) VALUES (?, ?, ?, ?)";

        // Thực thi câu lệnh insert
        int result = dbContext.insert(query, thuoc.getTenThuoc(), thuoc.getSoLuong(), thuoc.getDonViTinh(), thuoc.getGia());

        // Nếu insert thành công, trả về true, ngược lại false
        return result > 0;
    }

    public boolean updateThuoc(Thuoc thuoc) {
        String query = "UPDATE qlpk1.Thuoc SET tenThuoc = ?, soLuong = ?, donViTinh = ?, gia = ? WHERE id = ?";

        // Thực thi câu lệnh update
        int result = dbContext.updateOrDelete(query, thuoc.getTenThuoc(), thuoc.getSoLuong(), thuoc.getDonViTinh(), thuoc.getGia(), thuoc.getId());

        // Nếu update thành công, trả về true, ngược lại false
        return result > 0;
    }

    // Xóa thuốc theo ID
    public boolean deleteThuoc(int id) {
        String query = "DELETE FROM qlpk1.Thuoc WHERE id = ?";

        // Thực thi câu lệnh delete
        int result = dbContext.updateOrDelete(query, id);

        // Nếu delete thành công, trả về true, ngược lại false
        return result > 0;
    }
}

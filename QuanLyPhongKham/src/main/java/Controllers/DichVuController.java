/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.DichVu;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DichVuController {
     private final Connection<DichVu> dbContext; // Khởi tạo dbContext
    private static final Logger LOGGER = Logger.getLogger(DichVuController.class.getName());

    // Constructor để khởi tạo dbContext
    public DichVuController() {
        dbContext = new Connection<>(); // Khởi tạo đối tượng Connection
    }

    // Phương thức lấy tất cả dữ liệu thuốc
    public List<DichVu> getALLDichVu() {
        List<DichVu> dichvus = new ArrayList<>();
        String query = "SELECT * FROM qlpk1.dichvu"; // Truy vấn lấy danh sách thuốc

        dichvus = dbContext.fetchAll(query, sr -> {
            try {
                // Chuyển dữ liệu từ ResultSet thành đối tượng Thuoc
                return new DichVu(
                        sr.getInt("id"),
                        sr.getString("tenDV"),
                        sr.getFloat("giaDV")
                );
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                return null;
            }
        });
        return dichvus; // Trả về danh sách thuốc
    }
    
    public DichVu getDichVuById(int id) {
        String query = "SELECT * FROM qlpk1.dichvu WHERE id=?"; // Truy vấn lấy danh sách thuốc

        return dbContext.fetchOne(query, sr -> {
            try {
                // Chuyển dữ liệu từ ResultSet thành đối tượng Thuoc
                return new DichVu(
                        sr.getInt("id"),
                        sr.getString("tenDV"),
                        sr.getFloat("giaDV")
                );
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                return null;
            }
        },id);
    }
    
    public DichVu getDichVuByName(String name) {
        String query = "SELECT * FROM qlpk1.dichvu WHERE tenDV LIKE ?"; // Truy vấn lấy danh sách thuốc

        return dbContext.fetchOne(query, sr -> {
            try {
                // Chuyển dữ liệu từ ResultSet thành đối tượng Thuoc
                return new DichVu(
                        sr.getInt("id"),
                        sr.getString("tenDV"),
                        sr.getFloat("giaDV")
                );
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                return null;
            }
        },"%"+name+"%");
    }
    
    public boolean addDichVu(DichVu dichvu) {
        String query = "INSERT INTO qlpk1.dichvu (tenDV, giaDV) VALUES (?, ?)";

        // Thực thi câu lệnh insert
        int result = dbContext.insert(query, dichvu.getTenDV(), dichvu.getGiaDV());

        // Nếu insert thành công, trả về true, ngược lại false
        return result > 0;
    }
    
    public boolean updateDichVu(DichVu dichvu){
        String query = "UPDATE qlpk1.dichvu SET tenDV=?, giaDV=? WHERE id=?";
        
        int result = dbContext.updateOrDelete(query, dichvu.getTenDV(), dichvu.getGiaDV(), dichvu.getID());
        
        return result > 0;
    }
    
    public boolean deleteDichVu(int id){
        String query = "DELETE FROM qlpk1.dichvu WHERE id=?";
        
        int result = dbContext.updateOrDelete(query, id);
        
        return result>0;
    }
}

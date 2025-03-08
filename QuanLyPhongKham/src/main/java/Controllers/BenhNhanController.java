/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.BenhNhan;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class BenhNhanController {
    private final Connection<BenhNhan> dbContext; // Khởi tạo dbContext
    private static final Logger LOGGER = Logger.getLogger(DichVuController.class.getName());
    
    public BenhNhanController() {
        dbContext = new Connection<>(); // Khởi tạo đối tượng Connection
    }
    
    public List<BenhNhan> getALLBenhNhan(){
        List<BenhNhan> benhnhans = new ArrayList<>();
        String query = "SELECT * FROM qlpk1.benhnhan order by id DESC";
        
        benhnhans = dbContext.fetchAll(query, sr -> {
            try {
                // Chuyển dữ liệu từ ResultSet thành đối tượng Thuoc
                return new BenhNhan(
                        sr.getInt("id"),
                        sr.getString("hoTen"),
                        sr.getString("diaChi"),
                        sr.getString("gioiTinh"),
                        sr.getString("sdt"),
                        sr.getInt("namSinh"),
                        sr.getString("tienSu")
                );
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                return null;
            }
        });
        return benhnhans;
    }
    
    public BenhNhan getBenhNhanById(int id) {
        String query = "SELECT * FROM qlpk1.benhnhan WHERE id=? order by id DESC"; // Truy vấn lấy danh sách thuốc

        return dbContext.fetchOne(query, sr -> {
            try {
                // Chuyển dữ liệu từ ResultSet thành đối tượng Thuoc
                return new BenhNhan(
                        sr.getInt("id"),
                        sr.getString("hoTen"),
                        sr.getString("diaChi"),
                        sr.getString("gioiTinh"),
                        sr.getString("sdt"),
                        sr.getInt("namSinh"),
                        sr.getString("tienSu")
                );
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                return null;
            }
        },id);
    }
    
    public BenhNhan getBenhNhanByName(String name) {
        String query = "SELECT * FROM qlpk1.benhnhan WHERE hoTen LIKE ? order by id DESC"; // Truy vấn lấy danh sách thuốc

        return dbContext.fetchOne(query, sr -> {
            try {
                // Chuyển dữ liệu từ ResultSet thành đối tượng Thuoc
                return new BenhNhan(
                        sr.getInt("id"),
                        sr.getString("hoTen"),
                        sr.getString("diaChi"),
                        sr.getString("gioiTinh"),
                        sr.getString("sdt"),
                        sr.getInt("namSinh"),
                        sr.getString("tienSu")
                );
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                return null;
            }
        },"%"+name+"%");
    }
    
    public boolean addBenhNhan(BenhNhan benhNhan) {
        String query = "INSERT INTO qlpk1.benhnhan (hoTen, diaChi, gioiTinh, sdt, namSinh, tienSu) VALUES (?, ?, ?, ?, ?, ?)";

        // Thực thi câu lệnh insert
        int result = dbContext.insert(query, benhNhan.getHoTen(), benhNhan.getDiaChi(), benhNhan.getGioiTinh(), benhNhan.getSdt(),
                benhNhan.getNamSinh(), benhNhan.getTienSu());

        // Nếu insert thành công, trả về true, ngược lại false
        return result > 0;
    }
    
    public boolean updateBenhNhan(BenhNhan benhNhan){
        String query = "UPDATE qlpk1.benhnhan SET hoTen=?, diaChi=?, gioiTinh=?, sdt=?, namSinh=?, tienSu=? WHERE id=?";
        
        int result = dbContext.updateOrDelete(query, benhNhan.getHoTen(), benhNhan.getDiaChi(), benhNhan.getGioiTinh(),
                benhNhan.getSdt(), benhNhan.getNamSinh(), benhNhan.getTienSu(), benhNhan.getId());
        
        return result > 0;
    }
    
    public boolean deleteBenhNhan(int id){
        String query = "DELETE FROM qlpk1.benhnhan WHERE id=?";
        
        int result = dbContext.updateOrDelete(query, id);
        
        return result > 0;
    }
}

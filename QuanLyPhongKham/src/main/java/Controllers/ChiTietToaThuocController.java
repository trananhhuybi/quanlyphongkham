/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.ChiTietToaThuoc;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ChiTietToaThuocController {
    private final Connection<ChiTietToaThuoc> dbContext;
    private static final Logger LOGGER = Logger.getLogger(ThuocController.class.getName());
    
    public ChiTietToaThuocController(){
        dbContext = new Connection<>();
    }
    
    public List<ChiTietToaThuoc> getCTTTByIDKB(int idKB) {
        List<ChiTietToaThuoc> cttts = new ArrayList<>();
        String query = "SELECT * FROM qlpk1.chitiettoathuoc WHERE idKB = ?"; // Truy vấn lấy danh sách khám bệnh theo idBN

        cttts = dbContext.fetchAll(query, sr -> {
            try {
                // Chuyển dữ liệu từ ResultSet thành đối tượng KhamBenh
                return new ChiTietToaThuoc(
                        sr.getInt("id"),
                        sr.getInt("idThuoc"),
                        sr.getInt("idKB"),
                        sr.getString("tenThuoc"),
                        sr.getInt("sang"),
                        sr.getInt("trua"),
                        sr.getInt("chieu"),
                        sr.getFloat("gia")
                );
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                return null;
            }
        }, idKB);
        return cttts;
    }
    
    public ChiTietToaThuoc getCTTTById(int id) {
        String query = "SELECT * FROM qlpk1.chitiettoathuoc WHERE id = ?";  

        return dbContext.fetchOne(query, sr -> {
            try {
                return new ChiTietToaThuoc(
                        sr.getInt("id"),
                        sr.getInt("idThuoc"),
                        sr.getInt("idKB"),
                        sr.getString("tenThuoc"),
                        sr.getInt("sang"),
                        sr.getInt("trua"),
                        sr.getInt("chieu"),
                        sr.getFloat("gia")
                );
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                return null;
            }
        }, id);
    }
    
    public boolean addCTTT(ChiTietToaThuoc cttt){
        String query = "INSERT INTO qlpk1.chitiettoathuoc (idThuoc, idKB, tenThuoc, sang, trua, chieu, gia, tongGia ) VALUES (?,?,?,?,?,?,?,?)";
        
        int result = dbContext.insert(query, cttt.getIdThuoc(), cttt.getIdKB(), cttt.getTenThuoc(), cttt.getSang(),
                cttt.getTrua(), cttt.getChieu(), cttt.getGia(), cttt.getTongGia());
        
        return result > 0;
    }
    
    public boolean updateCTTT(ChiTietToaThuoc cttt){
        String query = "UPDATE qlpk1.chitiettoathuoc SET idThuoc=?, idKB=?, tenThuoc=?, sang=?, trua=?, chieu=?, gia=?"
                + "WHERE id=?";
        
        int result = dbContext.updateOrDelete(query, cttt.getIdThuoc(), cttt.getIdKB(), cttt.getTenThuoc(), cttt.getSang(),
                cttt.getTrua(), cttt.getChieu(), cttt.getGia(), cttt.getId());
        
        return result > 0;
    }
    
    public boolean deleteCTTT(int id) {
        String query = "DELETE FROM qlpk1.chitiettoathuoc WHERE id=?";

        int result = dbContext.updateOrDelete(query, id);

        return result > 0;
    }
    
}

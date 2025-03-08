/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.HoaDon;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class HoaDonController {
    private final Connection<HoaDon> dbContext;
    private static final Logger LOGGER = Logger.getLogger(HoaDonController.class.getName());
    public HoaDonController(){
        dbContext = new Connection<>();
    }
    
    public List<HoaDon> getAllHoaDon(){
        List<HoaDon> hoadon = new ArrayList<>();
        String query = "SELECT * FROM qlpk1.hoadon order by id DESC";
        
        hoadon = dbContext.fetchAll(query, sr -> {
            try {
                return new HoaDon(
                        sr.getInt("id"),
                        sr.getInt("idKB"),
                        sr.getDate("ngayKham"),
                        sr.getFloat("thanhTien")
                );
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                return null;
            }
        });

        return hoadon; 
    }
    
    public boolean addHoaDon(HoaDon hoadon){
        String query = "INSERT INTO qlpk1.hoadon (idKB, thanhTien) VALUES (?,?)";
        int result = dbContext.insert(query, hoadon.getIdKB(), hoadon.getThanhTien());
        return result > 0;
    }
}

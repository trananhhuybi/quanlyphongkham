/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.KhamBenh;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class KhamBenhController {

    private final Connection<KhamBenh> dbContext; // Khởi tạo dbContext
    private static final Logger LOGGER = Logger.getLogger(DichVuController.class.getName());

    public KhamBenhController() {
        dbContext = new Connection<>();
    }

    public List<KhamBenh> getALLKhamBenh() {
        List<KhamBenh> khambenhs = new ArrayList<>();
        String query = "SELECT * FROM qlpk1.khambenh";

        khambenhs = dbContext.fetchAll(query, sr -> {
            try {
                return new KhamBenh(
                        sr.getInt("id"),
                        sr.getInt("idBN"),
                        sr.getInt("idDV"),
                        sr.getString("trieuChung"),
                        sr.getString("chuanDoan"),
                        sr.getString("tenDV"),
                        sr.getFloat("giaDV")
                );
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                return null;
            }
        });

        return khambenhs;
    }

    public List<KhamBenh> getKhamBenhByIDBN(int idBN) {
        List<KhamBenh> khambenhs = new ArrayList<>();
        String query = "SELECT * FROM qlpk1.khambenh WHERE idBN=?"; // Truy vấn lấy danh sách khám bệnh theo idBN

        khambenhs = dbContext.fetchAll(query, sr -> {
            try {
                // Chuyển dữ liệu từ ResultSet thành đối tượng KhamBenh
                return new KhamBenh(
                        sr.getInt("id"),
                        sr.getInt("idBN"),
                        sr.getInt("idDV"),
                        sr.getString("trieuChung"),
                        sr.getString("chuanDoan"),
                        sr.getString("tenDV"),
                        sr.getFloat("giaDV")
                );
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                return null;
            }
        }, idBN);

        return khambenhs;
    }

    public KhamBenh getKhamBenhByIDBenhNhan(int id) {
        String query = "SELECT * FROM qlpk1.khambenh WHERE idBN=?"; // Truy vấn lấy danh sách thuốc

        return dbContext.fetchOne(query, sr -> {
            try {
                // Chuyển dữ liệu từ ResultSet thành đối tượng Thuoc
                return new KhamBenh(
                        sr.getInt("id"),
                        sr.getInt("idBN"),
                        sr.getInt("idDV"),
                        sr.getString("trieuChung"),
                        sr.getString("chuanDoan"),
                        sr.getString("tenDV"),
                        sr.getFloat("giaDV")
                );
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                return null;
            }
        }, id);
    }

    public KhamBenh getKhamBenhByID(int id) {
        String query = "SELECT * FROM qlpk1.khambenh WHERE id=?"; 

        return dbContext.fetchOne(query, sr -> {
            try {
                // Chuyển dữ liệu từ ResultSet thành đối tượng Thuoc
                return new KhamBenh(
                        sr.getInt("id"),
                        sr.getInt("idBN"),
                        sr.getInt("idDV"),
                        sr.getString("trieuChung"),
                        sr.getString("chuanDoan"),
                        sr.getString("tenDV"),
                        sr.getFloat("giaDV")
                );
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                return null;
            }
        }, id);
    }

    public boolean addKhamBenh(KhamBenh khamBenh) {
        String query = "INSERT INTO qlpk1.khambenh (idBN, idDV, trieuChung, chuanDoan, tenDV, giaDV) VALUES (?,?,?,?,?,?)";

        int result = dbContext.insert(query, khamBenh.getIdBN(), khamBenh.getIdDV(), khamBenh.getTrieuChung(), khamBenh.getChuanDoan(),
                khamBenh.getTenDV(), khamBenh.getGiaDV());

        return result > 0;
    }

    public boolean updateKhamBenh(KhamBenh khamBenh) {
        String query = "UPDATE qlpk1.khambenh SET trieuChung=?, chuanDoan=?, tenDV=?, giaDV=? WHERE id=?";

        int result = dbContext.updateOrDelete(query, khamBenh.getTrieuChung(), khamBenh.getChuanDoan(),
                khamBenh.getTenDV(), khamBenh.getGiaDV(), khamBenh.getId());

        return result > 0;
    }

    public boolean deleteKhamBenh(int id) {
        String query = "DELETE FROM qlpk1.khambenh WHERE id=?";

        int result = dbContext.updateOrDelete(query, id);

        return result > 0;
    }
}

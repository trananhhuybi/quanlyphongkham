/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class Thuoc {
    private int id;
    private String tenThuoc;
    private int soLuong;
    private float gia;
    private String donViTinh;

    public Thuoc(int id, String tenThuoc, int soLuong, String donViTinh, float gia) {
        this.id = id;
        this.tenThuoc = tenThuoc;
        this.soLuong = soLuong;
        this.gia = gia;
        this.donViTinh = donViTinh;
    }

    public Thuoc() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }
    
    @Override
    public String toString() {
        return tenThuoc ; // Chỉ hiển thị tên thuốc, giá và đơn vị tính
    }
}

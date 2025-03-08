/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class HoaDon {
    private int id;
    private int idKB;
    private Date ngayKham;
    private float thanhTien;

    public HoaDon(int idKB, float thanhTien) {
        this.idKB = idKB;
        this.thanhTien = thanhTien;
    }

    public HoaDon(int id, int idKB, Date ngayKham, float thanhTien) {
        this.id = id;
        this.idKB = idKB;
        this.ngayKham = ngayKham;
        this.thanhTien = thanhTien;
    }
    
    

    public Date getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(Date ngayKham) {
        this.ngayKham = ngayKham;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdKB() {
        return idKB;
    }

    public void setIdKB(int idKB) {
        this.idKB = idKB;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }
   
}

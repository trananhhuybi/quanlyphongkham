/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Administrator
 */
public class BenhNhan {
    private int id;
    private String hoTen;
    private String diaChi;
    private String gioiTinh;
    private String sdt;
    private int namSinh;
    private String tienSu;

    public BenhNhan() {
    }

    public BenhNhan(int id, String hoTen, String diaChi, String gioiTinh, String sdt, int namSinh, String tienSu) {
        this.id = id;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.namSinh = namSinh;
        this.tienSu = tienSu;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getTienSu() {
        return tienSu;
    }

    public void setTienSu(String tienSu) {
        this.tienSu = tienSu;
    }
    
    
}

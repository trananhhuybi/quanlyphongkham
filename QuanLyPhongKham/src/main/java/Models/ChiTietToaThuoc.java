/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Administrator
 */
public class ChiTietToaThuoc {
    private int id;
    private int idThuoc;
    private int idKB;
    private String tenThuoc;
    private int sang;
    private int trua;
    private int chieu;
    private float gia;

    public ChiTietToaThuoc() {
    }

    public ChiTietToaThuoc(int id, int idThuoc, int idKB, String tenThuoc, int sang, int trua, int chieu, float gia) {
        this.id = id;
        this.idThuoc = idThuoc;
        this.idKB = idKB;
        this.tenThuoc = tenThuoc;
        this.sang = sang;
        this.trua = trua;
        this.chieu = chieu;
        this.gia = gia;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdThuoc() {
        return idThuoc;
    }

    public void setIdThuoc(int idThuoc) {
        this.idThuoc = idThuoc;
    }

    public int getIdKB() {
        return idKB;
    }

    public void setIdKB(int idKB) {
        this.idKB = idKB;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public int getSang() {
        return sang;
    }

    public void setSang(int sang) {
        this.sang = sang;
    }

    public int getTrua() {
        return trua;
    }

    public void setTrua(int trua) {
        this.trua = trua;
    }

    public int getChieu() {
        return chieu;
    }

    public void setChieu(int chieu) {
        this.chieu = chieu;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public float getTongGia() {
        return (gia*sang)+(gia*trua)+(gia*chieu);
    }

      
}

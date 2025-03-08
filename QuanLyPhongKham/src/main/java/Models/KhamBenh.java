/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Administrator
 */
public class KhamBenh {
    private int id;
    private int idBN;
    private int idDV;
    private String trieuChung;
    private String chuanDoan;
    private String tenDV;
    private float giaDV;

    public KhamBenh() {
    }

    public KhamBenh(int id, int idBN, int idDV, String trieuChung, String chuanDoan, String tenDV, float giaDV) {
        this.id = id;
        this.idBN = idBN;
        this.idDV = idDV;
        this.trieuChung = trieuChung;
        this.chuanDoan = chuanDoan;
        this.tenDV = tenDV;
        this.giaDV = giaDV;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBN() {
        return idBN;
    }

    public void setIdBN(int idBN) {
        this.idBN = idBN;
    }

    public int getIdDV() {
        return idDV;
    }

    public void setIdDV(int idDV) {
        this.idDV = idDV;
    }

    public String getTrieuChung() {
        return trieuChung;
    }

    public void setTrieuChung(String trieuChung) {
        this.trieuChung = trieuChung;
    }

    public String getChuanDoan() {
        return chuanDoan;
    }

    public void setChuanDoan(String chuanDoan) {
        this.chuanDoan = chuanDoan;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public float getGiaDV() {
        return giaDV;
    }

    public void setGiaDV(float giaDV) {
        this.giaDV = giaDV;
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Administrator
 */
public class DichVu {
    private int ID;
    private String TenDV;
    private float GiaDV;

    public DichVu() {
    }

    public DichVu(int ID, String TenDV, float GiaDV) {
        this.ID = ID;
        this.TenDV = TenDV;
        this.GiaDV = GiaDV;
    }
    
    
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenDV() {
        return TenDV;
    }

    public void setTenDV(String TenDV) {
        this.TenDV = TenDV;
    }

    public float getGiaDV() {
        return GiaDV;
    }

    public void setGiaDV(float GiaDV) {
        this.GiaDV = GiaDV;
    }
    
    @Override
    public String toString() {
        return TenDV; // ComboBox sẽ hiển thị tên dịch vụ
    }
}

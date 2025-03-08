/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Administrator
 */
public class DangNhap {
    private String tenTK;
    private String matKhau;

    public DangNhap() {
    }

    public DangNhap(String tenTK, String matKhau) {
        this.tenTK = tenTK;
        this.matKhau = matKhau;
    }

    
    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    
}

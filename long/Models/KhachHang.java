/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author Gaara
 */
public class KhachHang {
    
    private String maKhachHang;
    private String password;
    private String name;
    private Date birth;
    private String diaChi;
    private String phone;
    
    public KhachHang() {
        
    }
    
    public KhachHang(String maKH, String password, String name, Date birth, String diaChi, String phone) {
        this.maKhachHang = maKH;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.diaChi = diaChi;
        this.phone = phone;
    }
    public String getMaKH() {
        return maKhachHang;
    }
    public void setMaKH(String maKH) {
        this.maKhachHang = maKH;
    }
    
    public String getPass() {
        return password;
    }
    public void setPass(String pass) {
        this.password = pass;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name1) {
        this.name = name1;
    }
    
    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    
    public String getDiaChi() {
        return diaChi;
    }
    public void setDiaChi(String diachi) {
        this.diaChi = diachi;
    }
    
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}

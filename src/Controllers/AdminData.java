/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.KhachHangData.ps;
import Models.Admin;
import Models.KhachHang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.FileReader;
/**
 *
 * @author Gaara
 */
public class AdminData {
    public static PreparedStatement ps;
    public static ResultSet rs;
    
    public Admin dangNhap(String taiKhoan, String pass) {
        Admin ad = null;
        try{
            BufferedReader br = new BufferedReader(new FileReader("D:\\account.dat"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(taiKhoan) && data[1].equals(pass)) {
                    ad = new Admin();
                    // Khởi tạo các thuộc tính cho đối tượng Admin ad ở đây
                    break;
                }
            }
            br.close();
        }
        catch(Exception e) {
            return ad = null;
        }
        return ad;
    }
    
//    public static ResultSet showTextfield(String sql) {
//        try {
//            ps = Connect.getConnect().prepareStatement(sql);
//            return ps.executeQuery();
//        } catch (Exception e) {
//            return null;
//        }
//        
//    }
    
//    public boolean UpdateAdmin(Admin ad) {
//        try {
//            ps = Connect.getConnect().prepareStatement("UPDATE QUAN_TRI SET Password = ? where Ma_Admin = ?");
//            ps.setString(2, ad.getMaAdmin());
//            ps.setString(1, ad.getPassword());
//            return ps.executeUpdate() >0;
//        } catch (Exception e) {
//            return false;
//        }
//    }
    
//    public boolean DeleteAdmin(String maAd) {
//        try {
//            ps = Connect.getConnect().prepareStatement("DELETE FROM QUAN_TRI WHERE Ma_Admin = ?");
//            ps.setString(1, maAd);
//            return ps.executeUpdate() >0;
//        } catch(Exception e) {
//            return false;
//        }
//    }
}

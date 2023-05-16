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
            BufferedReader br = new BufferedReader(new FileReader("D:\\account.txt"));
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
    

}
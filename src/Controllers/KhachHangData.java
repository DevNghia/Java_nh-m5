/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.KhachHang;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Gaara
 */
public class KhachHangData {
    public static PreparedStatement ps;
    public static ResultSet rs;
    
    public KhachHang dangNhap(String taiKhoan, String pass) {
        KhachHang kh = null;
       try{
            BufferedReader br = new BufferedReader(new FileReader("D:\\khachhang.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(taiKhoan) && data[1].equals(pass)) {
                    kh = new KhachHang();
                    // Khởi tạo các thuộc tính cho đối tượng Admin ad ở đây
                    break;
                }
            }
            br.close();
        }
        catch(Exception e) {
            return kh = null;
        }
        return kh;
    }
    
    
    public static ResultSet showTextfield(String sql) {
        try {
//            ps = Connect.getConnect().prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }
    
     public static void InsertKhachHang(KhachHang kh) {
        String sql = "insert into KHACH_HANG values(?,?,?,?,?,?)";
        try {
//            ps = Connect.getConnect().prepareStatement(sql);
            ps.setString(1, kh.getMaKH());
            ps.setString(2, kh.getPass());
            ps.setString(3, kh.getName());
            ps.setDate(4, kh.getBirth());
            ps.setString(5, kh.getDiaChi());
            ps.setString(6, kh.getPhone());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Đã thêm khách hàng thành công!" , "Thông báo", 1);
        } catch(HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Khách hàng không được thêm" , "Thông báo", 1);
        }
    }
    
    public boolean UpdateKhachHang(KhachHang kh) {
        try {
//            ps = Connect.getConnect().prepareStatement("UPDATE KHACH_HANG SET Password = ?, Ten_Khach_hang = ?,"
//                    + "Ngay_sinh = ?, Dia_chi = ?, Phone = ? where Ma_Khach_hang = ?");
            ps.setString(6, kh.getMaKH());
            ps.setString(1, kh.getPass());
            ps.setString(2, kh.getName());
            ps.setDate(3, kh.getBirth());
            ps.setString(4, kh.getDiaChi());
            ps.setString(5, kh.getPhone());
            return ps.executeUpdate() >0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean DeleteKhachHang(String maKH) {
        try {
//            ps = Connect.getConnect().prepareStatement("DELETE FROM KHACH_HANG WHERE Ma_Khach_hang = ?");
            ps.setString(1, maKH);
            return ps.executeUpdate() >0;
        } catch(Exception e) {
            return false;
        }
    }
    
}

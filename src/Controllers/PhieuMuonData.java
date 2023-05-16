/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.PhieuMuon;
import Models.Sach;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Gaara
 */
public class PhieuMuonData {
    public static PreparedStatement ps;
    public static ResultSet rs;
    
     public static boolean InsertPhieu(String filePath, PhieuMuon s) {
    try {
        // Đọc dữ liệu từ file txt và lưu vào một danh sách các đối tượng Sach
        List<PhieuMuon> pmList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 5) {
                PhieuMuon pm = new PhieuMuon(parts[0], parts[1], parts[2], Date.valueOf(parts[3]), Date.valueOf(parts[4]));
                pmList.add(pm);
            } else {
                System.out.println("Invalid data: " + line);
            }
        }
        reader.close();

        // Kiểm tra xem mã phieu muon mới đã tồn tại trong danh sách chưa
        boolean found = false;
        for (PhieuMuon pm: pmList) {
            if (pm.getMaMuon().equals(s.getMaMuon())) {
                found = true;
                break;
            }
        }

        // Nếu mã sách mới không tồn tại trong danh sách thì thêm vào danh sách
        if (!found) {
            pmList.add(s);
        } else {
            System.out.println("Ma sach da ton tai: " + s.getMaMuon());
            JOptionPane.showMessageDialog(null, "Mã sách đã tồn tại!", "Thông báo", 2);
        }

        // Ghi lại danh sách các đối tượng Sach vào file txt
        FileWriter writer = new FileWriter(filePath);
        for (PhieuMuon pm : pmList) {
            writer.write(pm.toString() + "\n");
        }
        writer.close();

        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    
//    public boolean UpdatePhieu(PhieuMuon p) {
//        try {
////            ps = Connect.getConnect().prepareStatement("UPDATE PHIEU_MUON SET  Ma_Khach_hang = ?, Ma_Sach = ?,"
////                    + "Ngay_muon = ?, Han_tra = ? where Ma_Phieu_muon = ?");
//            ps.setString(5, p.getMaMuon());
//            ps.setString(1, p.getMaKhach());
//            ps.setString(2, p.getSach());
//            ps.setDate(3, p.getNgayMuon());
//            ps.setDate(4, p.getHanTra());
//            return ps.executeUpdate() >0;
//        } catch (Exception e) {
//            return false;
//        }
//    }
    
    public boolean DeletePhieu(String ms) {
        try {
//            ps = Connect.getConnect().prepareStatement("DELETE FROM PHIEU_MUON WHERE Ma_Phieu_muon = ?");
            ps.setString(1, ms);
            return ps.executeUpdate() >0;
        } catch(Exception e) {
            return false;
        }
}}

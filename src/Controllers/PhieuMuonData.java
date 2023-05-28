/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.PhieuMuon;
import Models.PhieuMuon;
import Models.Sach;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Gaara
 */
public class PhieuMuonData {
    public static PreparedStatement ps;
    public static ResultSet rs;
    
     public static List<String[]> searchMuon(List<String[]> phieuList, String searchQuery) {
    List<String[]> result = new ArrayList<>();
    for (String[] phieu : phieuList) {
        if (phieu[0].toLowerCase().contains(searchQuery.toLowerCase()) ||
            phieu[1].toLowerCase().contains(searchQuery.toLowerCase()) ||
            phieu[2].toLowerCase().contains(searchQuery.toLowerCase()) ||
           phieu[3].toLowerCase().contains(searchQuery.toLowerCase())) {
            result.add(phieu);
        }
    }
    return result;
}
     public static boolean InsertPhieu(String filePath,PhieuMuon p) {
       try {
        // Đọc dữ liệu từ file txt và lưu vào một danh sách các đối tượng PhieuMuon
        List<PhieuMuon> phieuList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
             String dateString = parts[3];
             String dateString2 = parts[4];
             
        SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-yyyy");
        Date date, date2;
        

        try {
            date = dateFormat.parse(dateString);
              date2 = dateFormat.parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
            date = new Date(); // Giá trị mặc định nếu không thể phân tích chuỗi
            date2 = new Date();
        }
           
                PhieuMuon phieu = new PhieuMuon(parts[0], parts[1], parts[2], date,date2);
                phieuList.add(phieu);
                
          
        }
        reader.close();

        // Kiểm tra xem mã sách mới đã tồn tại trong danh sách chưa
        boolean found = false;
        for (PhieuMuon phieu : phieuList) {
            if (phieu.getMaMuon().equals(p.getMaMuon())) {
                found = true;
                break;
            }
        }

        // Nếu mã sách mới không tồn tại trong danh sách thì thêm vào danh sách
        if (!found) {
            phieuList.add(p);
        } else {
            System.out.println("Ma phieuda ton tai: " + p.getMaMuon());
            JOptionPane.showMessageDialog(null, "Mã phiếu đã tồn tại!", "Thông báo", 2);
        }

        // Ghi lại danh sách các đối tượng PhieuMuon vào file txt
        FileWriter writer = new FileWriter(filePath);
for (PhieuMuon phieu : phieuList) {
    StringBuilder lineBuilder = new StringBuilder(phieu.toString());
    lineBuilder.append(",0");
    String lines = lineBuilder.toString();
    writer.write(lines + "\n");
}
writer.close();


        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    }
    
    public static boolean UpdatePhieu(String filePath,PhieuMuon p) {
       try {
        // Đọc dữ liệu từ file txt và lưu vào một danh sách các đối tượng PhieuMuon
        List<PhieuMuon> phieuList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
             String dateString = parts[3];
             String dateString2 = parts[4];
        SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-yyyy");
        Date date, date2;
        

        try {
            date = dateFormat.parse(dateString);
              date2 = dateFormat.parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
            date = new Date(); // Giá trị mặc định nếu không thể phân tích chuỗi
            date2 = new Date();
        }
          
                PhieuMuon phieu = new PhieuMuon(parts[0], parts[1], parts[2], date,date2);
            phieuList.add(phieu);
          
        }
        reader.close();
        
        // Cập nhật dữ liệu trong danh sách các đối tượng PhieuMuon
        for (PhieuMuon phieu : phieuList) {
            if (phieu.getMaMuon().equals(p.getMaMuon())) {
                phieu.setMaKhach(p.getMaKhach());
               phieu.setMaSach(p.getSach());
                phieu.setNgayMuon(p.getNgayMuon());
                phieu.setHanTra(p.getHanTra());

                break;
            }
        }
        
        // Ghi lại danh sách các đối tượng PhieuMuon vào file txt
        FileWriter writer = new FileWriter(filePath);
for (PhieuMuon phieu : phieuList) {
    StringBuilder lineBuilder = new StringBuilder(phieu.toString());
    lineBuilder.append(",0");
    String lines = lineBuilder.toString();
    writer.write(lines + "\n");
}
writer.close();
        
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    }
    
    public boolean DeletePhieu(String filePath,String ms) {
        try {
        // Đọc dữ liệu từ file txt và lưu vào một danh sách các đối tượng PhieuMuon
        List<PhieuMuon> phieuList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
                String dateString = parts[3];
             String dateString2 = parts[4];
        SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-yyyy");
        Date date, date2;
        

        try {
            date = dateFormat.parse(dateString);
              date2 = dateFormat.parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
            date = new Date(); // Giá trị mặc định nếu không thể phân tích chuỗi
            date2 = new Date();
        }
          
              PhieuMuon phieu = new PhieuMuon(parts[0], parts[1], parts[2], date,date2);
                phieuList.add(phieu);
           
        }
        reader.close();

        // Tìm và xóa đối tượng PhieuMuon có mã sách trùng với maPhieuMuon
        PhieuMuon phieuToRemove = null;
        for (PhieuMuon phieu : phieuList) {
            if (phieu.getMaMuon().equals(ms)) {
                phieuToRemove = phieu;
                break;
            }
        }

        if (phieuToRemove != null) {
            phieuList.remove(phieuToRemove);
        } else {
            System.out.println("Khong tim thay phieu co ma: " + ms);
            return false;
        }

        // Ghi lại danh sách các đối tượng PhieuMuon vào file txt
        // Ghi lại danh sách các đối tượng PhieuMuon vào file txt
        FileWriter writer = new FileWriter(filePath);
for (PhieuMuon phieu : phieuList) {
    StringBuilder lineBuilder = new StringBuilder(phieu.toString());
    lineBuilder.append(",0");
    String lines = lineBuilder.toString();
    writer.write(lines + "\n");
}
writer.close();

        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}}

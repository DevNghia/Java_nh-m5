/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.KhachHang;
import Models.KhachHang;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    
   public static List<String[]> searchKhach(List<String[]> khachList, String searchQuery) {
    List<String[]> result = new ArrayList<>();
    for (String[] khach : khachList) {
        if (khach[0].toLowerCase().contains(searchQuery.toLowerCase()) ||
            khach[1].toLowerCase().contains(searchQuery.toLowerCase()) ||
            khach[2].toLowerCase().contains(searchQuery.toLowerCase()) ||
           khach[3].toLowerCase().contains(searchQuery.toLowerCase())) {
            result.add(khach);
        }
    }
    return result;
}
    
     public static boolean InsertKhachHang(String filePath,KhachHang kh) {
       try {
        // Đọc dữ liệu từ file txt và lưu vào một danh sách các đối tượng KhachHang
        List<KhachHang> khachList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
             String dateString = parts[3];
        SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-yyyy");
        Date date;

        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            date = new Date(); // Giá trị mặc định nếu không thể phân tích chuỗi
        }
            if (parts.length == 6) {
                KhachHang khach = new KhachHang(parts[0], parts[1], parts[2], date, parts[4], parts[5]);
                khachList.add(khach);
            } else {
                System.out.println("Invalid data: " + line);
            }
        }
        reader.close();

        // Kiểm tra xem mã sách mới đã tồn tại trong danh sách chưa
        boolean found = false;
        for (KhachHang khach : khachList) {
            if (khach.getMaKH().equals(kh.getMaKH())) {
                found = true;
                break;
            }
        }

        // Nếu mã sách mới không tồn tại trong danh sách thì thêm vào danh sách
        if (!found) {
            khachList.add(kh);
        } else {
            System.out.println("Ma khach hang da ton tai: " + kh.getMaKH());
            JOptionPane.showMessageDialog(null, "Mã khách hàng đã tồn tại!", "Thông báo", 2);
        }

        // Ghi lại danh sách các đối tượng KhachHang vào file txt
        FileWriter writer = new FileWriter(filePath);
        for (KhachHang khach : khachList) {
            writer.write(khach.toString() + "\n");
        }
        writer.close();

        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    }
    
    public static boolean UpdateKhachHang(String filePath,KhachHang kh) {
       try {
        // Đọc dữ liệu từ file txt và lưu vào một danh sách các đối tượng KhachHang
        List<KhachHang> khachList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
             String dateString = parts[3];
        SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-yyyy");
        Date date;

        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            date = new Date(); // Giá trị mặc định nếu không thể phân tích chuỗi
        }
           if(parts.length == 6){
                KhachHang khach = new KhachHang(parts[0], parts[1], parts[2], date, parts[4], parts[5]);
            khachList.add(khach);
           }else{
               System.out.println("Invalid data: " + line);
           }
        }
        reader.close();
        
        // Cập nhật dữ liệu trong danh sách các đối tượng KhachHang
        for (KhachHang khach : khachList) {
            if (khach.getMaKH().equals(kh.getMaKH())) {
                khach.setPass(kh.getPass());
               khach.setName(kh.getName());
                khach.setBirth(kh.getBirth());
                khach.setDiaChi(kh.getDiaChi());
              khach.setPhone(kh.getPhone());
                break;
            }
        }
        
        // Ghi lại danh sách các đối tượng KhachHang vào file txt
        FileWriter writer = new FileWriter(filePath);
        for (KhachHang khach : khachList) {
            writer.write(khach.toString() + "\n");
        }
        writer.close();
        
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    }
    
    public static boolean DeleteKhachHang(String filePath,String maKH) {
        try {
        // Đọc dữ liệu từ file txt và lưu vào một danh sách các đối tượng KhachHang
        List<KhachHang> khachList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
               String dateString = parts[3];
        SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-yyyy");
        Date date;

        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            date = new Date(); // Giá trị mặc định nếu không thể phân tích chuỗi
        }
            if (parts.length == 6) {
               KhachHang khach = new KhachHang(parts[0], parts[1], parts[2], date, parts[4], parts[5]);
                khachList.add(khach);
            } else {
                System.out.println("Invalid data: " + line);
            }
        }
        reader.close();

        // Tìm và xóa đối tượng KhachHang có mã sách trùng với maKhachHang
        KhachHang khachToRemove = null;
        for (KhachHang khach : khachList) {
            if (khach.getMaKH().equals(maKH)) {
                khachToRemove = khach;
                break;
            }
        }

        if (khachToRemove != null) {
            khachList.remove(khachToRemove);
        } else {
            System.out.println("Khong tim thay khach co ma: " + maKH);
            return false;
        }

        // Ghi lại danh sách các đối tượng KhachHang vào file txt
        FileWriter writer = new FileWriter(filePath);
        for (KhachHang khach : khachList) {
            writer.write(khach.toString() + "\n");
        }
        writer.close();

        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    }
    
}

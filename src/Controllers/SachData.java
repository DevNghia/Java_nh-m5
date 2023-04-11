/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Sach;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Gaara
 */
public class SachData {
    
    public static PreparedStatement ps;
    public static ResultSet rs;
    
    public static ResultSet showTextfield(String sql) {
        try {
            ps = Connect.getConnect().prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static boolean InsertSach(String filePath, Sach s) {
    try {
        // Đọc dữ liệu từ file txt và lưu vào một danh sách các đối tượng Sach
        List<Sach> sachList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                Sach sach = new Sach(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                sachList.add(sach);
            } else {
                System.out.println("Invalid data: " + line);
            }
        }
        reader.close();

        // Kiểm tra xem mã sách mới đã tồn tại trong danh sách chưa
        boolean found = false;
        for (Sach sach : sachList) {
            if (sach.getMaSach().equals(s.getMaSach())) {
                found = true;
                break;
            }
        }

        // Nếu mã sách mới không tồn tại trong danh sách thì thêm vào danh sách
        if (!found) {
            sachList.add(s);
        } else {
            System.out.println("Ma sach da ton tai: " + s.getMaSach());
            JOptionPane.showMessageDialog(null, "Mã sách đã tồn tại!", "Thông báo", 2);
        }

        // Ghi lại danh sách các đối tượng Sach vào file txt
        FileWriter writer = new FileWriter(filePath);
        for (Sach sach : sachList) {
            writer.write(sach.toString() + "\n");
        }
        writer.close();

        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean UpdateSach(String filePath, Sach s) {
    try {
        // Đọc dữ liệu từ file txt và lưu vào một danh sách các đối tượng Sach
        List<Sach> sachList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
           if(parts.length == 6){
                Sach sach = new Sach(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
            sachList.add(sach);
           }else{
               System.out.println("Invalid data: " + line);
           }
        }
        reader.close();
        
        // Cập nhật dữ liệu trong danh sách các đối tượng Sach
        for (Sach sach : sachList) {
            if (sach.getMaSach().equals(s.getMaSach())) {
                sach.setTenSach(s.getTenSach());
                sach.setTenTacGia(s.getTenTacGia());
                sach.setNhaXB(s.getNhaXB());
                sach.setGiaTien(s.getGiaTien());
                sach.setSoLuong(s.getSoLuong());
                break;
            }
        }
        
        // Ghi lại danh sách các đối tượng Sach vào file txt
        FileWriter writer = new FileWriter(filePath);
        for (Sach sach : sachList) {
            writer.write(sach.toString() + "\n");
        }
        writer.close();
        
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    
   public static boolean DeleteSach(String filePath, String maSach) {
    try {
        // Đọc dữ liệu từ file txt và lưu vào một danh sách các đối tượng Sach
        List<Sach> sachList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                Sach sach = new Sach(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                sachList.add(sach);
            } else {
                System.out.println("Invalid data: " + line);
            }
        }
        reader.close();

        // Tìm và xóa đối tượng Sach có mã sách trùng với maSach
        Sach sachToRemove = null;
        for (Sach sach : sachList) {
            if (sach.getMaSach().equals(maSach)) {
                sachToRemove = sach;
                break;
            }
        }

        if (sachToRemove != null) {
            sachList.remove(sachToRemove);
        } else {
            System.out.println("Khong tim thay sach co ma: " + maSach);
            return false;
        }

        // Ghi lại danh sách các đối tượng Sach vào file txt
        FileWriter writer = new FileWriter(filePath);
        for (Sach sach : sachList) {
            writer.write(sach.toString() + "\n");
        }
        writer.close();

        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

}

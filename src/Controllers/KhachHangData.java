/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.KhachHang;
import Models.PhieuMuon;
import Models.Sach;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gaara
 */
public class KhachHangData {
    
    
    public KhachHang dangNhap(String taiKhoan, String pass) {
        KhachHang kh = null;
        return kh;
    }
    public static boolean addKhachHang(String filepath, KhachHang kh) throws ParseException{
        List<KhachHang> khList = new ArrayList<>();
        boolean isExits =false;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                String maKhach = fields[0];
                String tenKhach = fields[1];
                String passWord = fields[2];
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date ngaysinh = dateFormat.parse(fields[3]);
                String diachi = fields[4];
                String Phone = fields[5];
                
                KhachHang pm = new KhachHang(maKhach, tenKhach, passWord, ngaysinh, diachi, Phone);
                khList.add(pm);
            }
            
            for(KhachHang k: khList){
                if(k.getMaKH().toLowerCase().equals(kh.getMaKH().toLowerCase())){
                    isExits = true;
                    break;
                }
            }
            
            if(isExits){
//                JOptionPane.showMessageDialog(null, "Mã phiếu mượn đã tồn tại", "Thông báo", 2);
                return false;
            }
            else {
                khList.add(kh);
                FileWriter writer = new FileWriter(filepath);
                for(KhachHang pm: khList){
                    writer.write(pm.toString() + "\n");
                }
                writer.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }
   
    public static DefaultTableModel search(String filepath, String p) {
    String[] columnNames = {"Mã khách hàng", "Tên khách hàng", "Mật khẩu", "Ngày sinh", "Địa chỉ", "Điện thoại"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

    try {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            String maKhach = fields[0];
            String tenKhach = fields[1];
            String passWord = fields[2];
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date ngaySinh = dateFormat.parse(fields[3]);
            String diaChi = fields[4];
            String phone = fields[5];

            KhachHang kh = new KhachHang(maKhach, tenKhach, passWord, ngaySinh, diaChi, phone);

            if (kh.getMaKH().toLowerCase().contains(p.toLowerCase())
                    || kh.getName().toLowerCase().contains(p.toLowerCase())
                    || kh.getDiaChi().toLowerCase().contains(p.toLowerCase())) {
                Object[] rowData = {kh.getMaKH(), kh.getName(), kh.getPass(), dateFormat.format(ngaySinh), kh.getDiaChi(), kh.getPhone()};
                model.addRow(rowData);
            }
        }
        reader.close();

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu mượn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }
    return model;
}
    
    public static boolean updatePhieu(String filepath, KhachHang p){
        List<KhachHang> khList = new ArrayList<>();
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                String maKH = fields[0];
                String tenKH = fields[1];
                String passWord = fields[2];
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date ngaySinh = dateFormat.parse(fields[3]);
                String diaChi = fields[4];
                String phone = fields[5];
                
                KhachHang kh = new KhachHang(maKH, tenKH, passWord, ngaySinh, diaChi, phone);
                khList.add(kh);
            }
            reader.close();
            for(KhachHang pm: khList){
                if(pm.getMaKH().toLowerCase().equals(p.getMaKH().toLowerCase())){
                    pm.setName(p.getName());
                    pm.setPass(p.getPass());
                    pm.setBirth(p.getBirth());
                    pm.setDiaChi(p.getDiaChi());
                    pm.setPhone(p.getPhone());
                    break;
                }       
            }
            
            FileWriter fr = new FileWriter(filepath);
            for(KhachHang pm : khList){
                fr.write(pm.toString() + "\n");
            }
            fr.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
        }catch(ParseException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean DeleteKhach(String filePath, String maKH) {
    try {
        // Đọc dữ liệu từ file txt và lưu vào một danh sách các đối tượng Sach
        List<KhachHang> khList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        try{
            while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date ngaysinh = dateFormat.parse(parts[3]);
                
                KhachHang kh = new KhachHang(parts[0], parts[1], parts[2], ngaysinh ,parts[4], parts[5]);
                khList.add(kh);
            } else {
                System.out.println("Invalid data: " + line);
            }
        }
        reader.close();
        }catch(ParseException ex){
            
        }
        

        // Tìm và xóa đối tượng Sach có mã sách trùng với maSach
        KhachHang khRemove = null;
        for (KhachHang kh : khList) {
            if (kh.getMaKH().equals(maKH)) {
                khRemove = kh;
                break;
            }
        }

        if (khRemove != null) {
            khList.remove(khRemove);
        } else {
            System.out.println("Khong tim thay sach co ma: " + maKH);
            return false;
        }

        // Ghi lại danh sách các đối tượng Sach vào file txt
        FileWriter writer = new FileWriter(filePath);
        for (KhachHang kh : khList) {
            writer.write(kh.toString() + "\n");
        }
        writer.close();

        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
}

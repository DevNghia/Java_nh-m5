/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.PhieuMuon;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;




/**
 *
 * @author Gaara
 */
public class PhieuMuonData {
    //kiểm tra xem trong file đã từng xuất hiện mã phiếu mượn đó chưa
        public static boolean addPhieu(String filepath, PhieuMuon p) throws ParseException{
        List<PhieuMuon> pmList = new ArrayList<>();
        boolean isExits =false;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                String maMuon = fields[0];
                String maKhach = fields[1];
                String maSach = fields[2];
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date ngayMuon = dateFormat.parse(fields[3]);
                Date hanTra = dateFormat.parse(fields[4]);
                
                PhieuMuon pm = new PhieuMuon(maMuon, maKhach, maSach, ngayMuon, hanTra);
                pmList.add(pm);
            }
            
            for(PhieuMuon pm: pmList){
                if(pm.getMaMuon().toLowerCase().equals(p.getMaMuon().toLowerCase())){
                    isExits = true;
                    break;
                }
            }
            
            if(isExits){
//                JOptionPane.showMessageDialog(null, "Mã phiếu mượn đã tồn tại", "Thông báo", 2);
                return false;
            }
            else {
                pmList.add(p);
                FileWriter writer = new FileWriter(filepath);
                for(PhieuMuon pm: pmList){
                    writer.write(pm.toString() + "\n");
                }
                writer.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }
    
    public static boolean updatePhieu(String filepath, PhieuMuon p){
        List<PhieuMuon> pmList = new ArrayList<>();
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                String maMuon = fields[0];
                String maKhach = fields[1];
                String maSach = fields[2];
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date ngayMuon = dateFormat.parse(fields[3]);
                Date hanTra = dateFormat.parse(fields[4]);
                
                PhieuMuon pm = new PhieuMuon(maMuon, maKhach, maSach, ngayMuon, hanTra);
                pmList.add(pm);
            }
            reader.close();
            for(PhieuMuon pm: pmList){
                if(pm.getMaMuon().toLowerCase().equals(p.getMaMuon().toLowerCase())){
                    pm.setMaKhach(p.getMaKhach());
                    pm.setMaSach(p.getMaSach());
                    pm.setNgayMuon(p.getNgayMuon());
                    pm.setHanTra(p.getHanTra());
                    break;
                }       
            }
            
            FileWriter fr = new FileWriter(filepath);
            for(PhieuMuon pm : pmList){
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

    
    public static boolean delPhieu(String filepath, String p){
        List<PhieuMuon> pmList = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                String maMuon = fields[0];
                String maKhach = fields[1];
                String maSach = fields[2];
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date ngayMuon = dateFormat.parse(fields[3]);
                Date hanTra = dateFormat.parse(fields[4]);
                
                PhieuMuon pm = new PhieuMuon(maMuon, maKhach, maSach, ngayMuon, hanTra);
                pmList.add(pm);
            }
            reader.close();
            
            
            PhieuMuon pmtoDel = null;
            for(PhieuMuon pm: pmList){
                if(pm.getMaMuon().toLowerCase().equals(p.toLowerCase())){
                    pmtoDel = pm;
                    break;
                }       
            }
            if (pmtoDel != null) {
                pmList.remove(pmtoDel);
            } else {
                System.out.println("Khong tim thay phieu co ma: " + p);
                return false;
            }
            
            FileWriter fr = new FileWriter(filepath);
            for(PhieuMuon pm : pmList){
                fr.write(pm.toString() + "\n");
            }
            fr.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
        }catch(ParseException e){
            e.printStackTrace();
        }
        return true;
    }
    
    public static DefaultTableModel search(String filepath, String p) {
    String[] columnNames = {"Mã phiếu mượn", "Mã khách", "Mã sách", "Ngày mượn", "Hạn trả"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

    try {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            String maMuon = fields[0];
            String maKhach = fields[1];
            String maSach = fields[2];
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date ngayMuon = dateFormat.parse(fields[3]);
            Date hanTra = dateFormat.parse(fields[4]);

            PhieuMuon pm = new PhieuMuon(maMuon, maKhach, maSach, ngayMuon, hanTra);

            if (pm.getMaMuon().toLowerCase().contains(p.toLowerCase())
                    || pm.getMaKhach().toLowerCase().contains(p.toLowerCase())
                    || pm.getMaSach().toLowerCase().contains(p.toLowerCase())) {
                Object[] rowData = {pm.getMaMuon(), pm.getMaKhach(), pm.getMaSach(), dateFormat.format(ngayMuon), dateFormat.format(hanTra)};
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
}
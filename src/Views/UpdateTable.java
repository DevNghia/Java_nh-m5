/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gaara
 */
public class UpdateTable {
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;
//    public static Connection con = Connect.getConnect();
   public static String filesach = "D:\\sach.txt";
    
    
//    public static void LoadData (String sql,JTable tb) {
//        try{
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            tb.setModel((DbUtils.resultSetToTableModel(rs)));
//        }
//        catch(Exception e) {
//            JOptionPane.showMessageDialog(null, e ,"Thông báo lỗi",1);
//        }
//    }
    public static void LoadData(String fileName, JTable tb) {
    try {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        String line;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã sách");
        model.addColumn("Tên sách");
        model.addColumn("Tên tác giả");
          model.addColumn("NXB");
        model.addColumn("Giá");
        model.addColumn("Số lượng");
        //Thêm các cột khác nếu cần thiết
        while ((line = br.readLine()) != null) {
            String[] data = line.split(","); //Phân tách dữ liệu bằng khoảng trắng hoặc dấu phẩy hoặc ký tự khác nếu có
            model.addRow(data);
        }
        tb.setModel(model);
        br.close();
        fr.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Thông báo lỗi", 1);
    }
}
    public static void LoadDataK(String fileName, JTable tb) {
    try {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        String line;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã khách hàng");
        model.addColumn("Password");
        model.addColumn("Tên khách hàng");
          model.addColumn("Ngày sinh");
        model.addColumn("Địa chỉ");
        model.addColumn("Số điện thoại");
        //Thêm các cột khác nếu cần thiết
        while ((line = br.readLine()) != null) {
            String[] data = line.split(","); //Phân tách dữ liệu bằng khoảng trắng hoặc dấu phẩy hoặc ký tự khác nếu có
            model.addRow(data);
        }
        tb.setModel(model);
        br.close();
        fr.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Thông báo lỗi", 1);
    }
}
     public static void LoadDataPM(String fileName, JTable tb) {
    try {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        String line;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã phiếu mượn");
        model.addColumn("Mã khách hàng");
        model.addColumn("Mã sách");
          model.addColumn("Ngày mượn");
        model.addColumn("Hạn trả");
        model.addColumn("Ngày trả");
        //Thêm các cột khác nếu cần thiết
        while ((line = br.readLine()) != null) {
            String[] data = line.split(","); //Phân tách dữ liệu bằng khoảng trắng hoặc dấu phẩy hoặc ký tự khác nếu có
            model.addRow(data);
        }
        tb.setModel(model);
        br.close();
        fr.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Thông báo lỗi", 1);
    }
}
     public static void LoadDataPM(List<String> lines, JTable tb) {
    try {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã phiếu mượn");
        model.addColumn("Mã khách hàng");
        model.addColumn("Mã sách");
        model.addColumn("Ngày mượn");
        model.addColumn("Hạn trả");
        model.addColumn("Ngày trả");

        for (String line : lines) {
            String[] data = line.split(",");
            model.addRow(data);
        }

        tb.setModel(model);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Thông báo lỗi", 1);
    }
}
   public static void LoadData1(List<String[]> fileName, JTable tb) {
    try {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã sách");
        model.addColumn("Tên sách");
        model.addColumn("Tên tác giả");
        model.addColumn("NXB");
        model.addColumn("Giá");
        model.addColumn("Số lượng");

        for (String[] data : fileName) {
            model.addRow(data);
        }
        tb.setModel(model);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Thông báo lỗi", 1);
    }
}
 public static void LoadDataK1(List<String[]> fileName, JTable tb) {
    try {
        DefaultTableModel model = new DefaultTableModel();
       model.addColumn("Mã khách hàng");
        model.addColumn("Password");
        model.addColumn("Tên khách hàng");
          model.addColumn("Ngày sinh");
        model.addColumn("Địa chỉ");
        model.addColumn("Số điện thoại");

        for (String[] data : fileName) {
            model.addRow(data);
        }
        tb.setModel(model);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Thông báo lỗi", 1);
    }
}

public static void LoadDataPM1(List<String[]> fileName, JTable tb) {
    try {
        DefaultTableModel model = new DefaultTableModel();
       model.addColumn("Mã phiếu mượn");
        model.addColumn("Mã khách hàng");
        model.addColumn("Mã sách");
          model.addColumn("Ngày mượn");
        model.addColumn("Hạn trả");
        model.addColumn("Ngày trả");

        for (String[] data : fileName) {
            model.addRow(data);
        }
        tb.setModel(model);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Thông báo lỗi", 1);
    }
}    
    public static ResultSet ShowTextField(String sql) {
        try{
//            ps = con.prepareStatement(sql);
            return ps.executeQuery(); 
        }
        catch(Exception e) {
            return null;
            //JOptionPane.showMessageDialog(null, e, "Thông báo lỗi", 1);
        }
    }

   public static List<String[]> getDataFromTextFile(String fileName) {
    List<String[]> data = new ArrayList<>();
    try {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            String[] row = line.split(","); //Phân tách dữ liệu bằng khoảng trắng hoặc dấu phẩy hoặc ký tự khác nếu có
            data.add(row);
        }
        br.close();
        fr.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Thông báo lỗi", 1);
    }
    return data;
}

    public static boolean updateTeupxtFile(String fileName, List<String[]> data) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        for (String[] row : data) {
            writer.write(String.join(",", row));
            writer.newLine();
        }
        return true;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}

    }
    
    

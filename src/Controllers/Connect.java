// /*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Controllers;
//
//import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
///**
// *
// * @author Gaara
// */
//public class Connect {
//    private  static Connection con;
//    
//    public static Connection getConnect(){
////         final String url = "jdbc:sqlserver://localhost:1433;databaseName=QLTV;user=sa;password=a;encrypt=true;trustServerCertificate=true";
////        try {
////            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
////            return DriverManager.getConnection(url);
////        } catch (SQLException | ClassNotFoundException e) {
////            e.printStackTrace();
////        }
////        return null;
//        try {
//          //  SQLServerDataSource ds = new SqlServerDataSource();
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLTV;user=sa;password=devnghia123;encrypt=true;trustServerCertificate=true");
//            System.out.println("Ket noi co so du lieu thanh cong!");
//        } catch (Exception e) {
//            System.out.println("Ket noi khong thanh cong"+e);
//        }
//        return con;
//    }
//    public static String testConnect() {
//        try{
//            con = Connect.getConnect();
//            
//            return "Ket noi thanh cong";
//        }
//        catch(Exception e) {
//            return "Ket noi that bai";
//        }
//    } 
//}

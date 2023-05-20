package com.cy.studentsel.DAO;

//import MemberClass.CourseRecord;
//import MemberClass.StudentRecord;
//import dbConnect.dbUtils;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
///**
// * @author leaf-fulture
// * @date 2023/5/19 21:27
// */
//public class ClassDao {
////    public static void addCourse(CourseRecord record) {
////        try {
////            String sql = "insert into Course values(?,?,?,?,?,?,?,?)";
////            PreparedStatement ps = dbUtils.ConN.prepareStatement(sql);
////            ps.setString(1, record.ID());
////            ps.setString(2, record.Name());
////            ps.setInt(3, record.Credit());
////            ps.setString(4, record.Type());
////            ps.setString(5, record.Teacher());
////            ps.setString(6, record.time());
////            ps.setString(7, record.place());
////            ps.setInt(8, record.capacity());
////            ps.executeUpdate();
////            ps.close();
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
////    }
//    public static void deletecouse(String ID) {
//        try {
//            String sql = "delete from course where ID=?";
//            PreparedStatement ps = dbUtils.ConN.prepareStatement(sql);
//            ps.setString(1, ID);
//            ps.executeUpdate();
//            ps.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public static void alterStudent(CourseRecord record) {
//        try {
//            String sql = "update course set ID = ? , name = ? , credit = ? , type = ? , teacher = ? , time = ?, place = ?, capacity=? where ID = ?";
//            PreparedStatement ps = dbUtils.ConN.prepareStatement(sql);
//            ps.setString(1, record.ID());
//            ps.setString(2, record.Name());
//            ps.setInt(3, record.Credit());
//            ps.setString(4, record.Type());
//            ps.setString(5, record.Teacher());
//            ps.setString(6, record.time());
//            ps.setString(7, record.place());
//            ps.setInt(8, record.capacity());
//            ps.executeUpdate();
//            ps.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public static void main(String[] args) throws SQLException {
//        dbUtils.establishConnection();
////        CourseRecord record = new CourseRecord("123","123",123,"123","123","123","123",123);
//
//        dbUtils.closeConnection();
//    }
//
//}

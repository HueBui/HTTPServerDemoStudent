package dao;

import entity.Student;
import model.DBConnection;

import java.sql.*;

public class DAOLogin {
    Connection conn = null;
    DBConnection dbconn = null;
    Statement state = null;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;

    public DAOLogin(DBConnection dbcon) {
        this.dbconn = dbcon;
        conn = dbcon.getConnection();
    }

    public int login(Student student) {
        int n = 0;
        String sql = "select idstudent, password from student where idstudent='" + student.getIdStudent() + "' and password='" + student.getPassword() + "'";

        ResultSet rs = dbconn.getData(sql);
        try {
            while (rs.next()) {
                n = 1;
            }
        } catch (Exception ex) {
            System.out.println("Loi login");
        }

        return n;
    }

    public int updateToken(Student student, String token) {
        int n = 0;
        String sql = "update student set token=? where idstudent=" + student.getIdStudent();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, token);
            n = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n;
    }

    public String ckeck(Student student) {
        String sql = "select token from student where idstudent=" + student.getIdStudent();

        ResultSet rs = dbconn.getData(sql);
        String tokenSql = null;
        try {
            while (rs.next()) {
                tokenSql = rs.getString("token");
            }
        } catch (Exception ex) {
            System.out.println("Loi so sanh");
        }
        System.out.println("token: " + tokenSql);
        return null;
    }
}

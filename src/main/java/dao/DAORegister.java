package dao;

import entity.Student;
import model.DBConnection;

import java.sql.*;

public class DAORegister {
    Connection conn = null;
    DBConnection dbconn = null;
    Statement state = null;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;

    public DAORegister(DBConnection dbcon) {
        this.dbconn = dbcon;
        conn = dbcon.getConnection();
    }

    public int register(Student student){
        int n =0;
        String sql = "insert into student(namestudent,address,email,phone,sex,password,token) values(?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre  = conn.prepareStatement(sql);
            pre.setString(1,student.getNameStudent());
            pre.setString(2,student.getAddress());
            pre.setString(3,student.getEmail());
            pre.setInt(4,student.getPhone());
            pre.setInt(5,student.getSex());
            pre.setString(6,student.getPassword());
            pre.setString(7,student.getToken());

            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n;
    }
}

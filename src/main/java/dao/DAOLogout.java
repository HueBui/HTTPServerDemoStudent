package dao;

import entity.Student;
import model.DBConnection;

import java.sql.*;

public class DAOLogout {
    Connection conn = null;
    DBConnection dbconn = null;
    Statement state = null;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;

    public DAOLogout(DBConnection dbcon) {
        this.dbconn = dbcon;
        conn = dbcon.getConnection();
    }

    public int removeToken(Student student){
        int n = 0;
        String sql = "update student set token=null where idstudent="+student.getIdStudent();

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
}

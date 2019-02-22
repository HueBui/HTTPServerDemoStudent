package dao;

import entity.Student;
import model.DBConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOStudent {

    Connection conn = null;
    DBConnection dbconn = null;
    Statement state = null;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;

    public DAOStudent(DBConnection dbcon) {
        this.dbconn = dbcon;
        conn = dbcon.getConnection();
    }

    public int addStudent(Student student) {
        int n = 0;
        String sqlPre = "insert into student(namestudent,address,email,phone,sex,password,token)"
                + " values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sqlPre);
            pre.setString(1, student.getNameStudent());
            pre.setString(2, student.getAddress());
            pre.setString(3, student.getEmail());
            pre.setInt(4, student.getPhone());
            pre.setInt(5, student.getSex());
            pre.setString(6, student.getPassword());
            pre.setString(7, student.getToken());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public JSONObject findById(int id) {
        String sqlPre = "select * from student where idstudent = " + id;
        ResultSet rs = dbconn.getData(sqlPre);
        JSONObject jSONObject = null;
        try {
            while (rs.next()) {
                jSONObject = new JSONObject();
                jSONObject.put("sex", rs.getInt(6));
                jSONObject.put("phone", rs.getInt(5));
                jSONObject.put("email", rs.getString(4));
                jSONObject.put("address", rs.getString(3));
                jSONObject.put("studentName", rs.getString(2));
                jSONObject.put("idStudent", rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject;
    }

    public JSONArray listAll() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jSONObject;

        String sqlPre = "select idstudent, namestudent, address, email, phone, sex from student";
        ResultSet rs = dbconn.getData(sqlPre);
        try {
            while (rs.next()) {
                jSONObject = new JSONObject();
                jSONObject.put("sex", rs.getInt(6));
                jSONObject.put("phone", rs.getInt(5));
                jSONObject.put("email", rs.getString(4));
                jSONObject.put("address", rs.getString(3));
                jSONObject.put("studentName", rs.getString(2));
                jSONObject.put("idStudent", rs.getInt(1));
                jsonArray.add(jSONObject);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonArray;
    }

    public int update(Student student) {
        int n = 0;
        String sql = "update student set namestudent=?, address=?, email=?,"
                + " phone=?, sex=? where idstudent=" + student.getIdStudent();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, student.getNameStudent());
            pre.setString(2, student.getAddress());
            pre.setString(3, student.getEmail());
            pre.setInt(4, student.getPhone());
            pre.setInt(5, student.getSex());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int delete(int id) {
        int n = 0;
        String sql = "delete from student where idstudent=" + id;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int findRoleById(int id) {
        int role = 0;
        String sql = "select role from student where idstudent =" + id;

        ResultSet rs = dbconn.getData(sql);
        try {
            while (rs.next()) {
                role = rs.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println("Loi find role" + ex);
        }

        return role;
    }

    public JSONArray findByName(String name) {
        int n = 0;
        String sql = "select * from student where namestudent like '%" + name + "%'";
        ResultSet rs = dbconn.getData(sql);
        JSONArray jsonArray = new JSONArray();
        JSONObject jSONObject = null;
        try {
            while (rs.next()) {
                jSONObject = new JSONObject();
                jSONObject = new JSONObject();
                jSONObject.put("sex", rs.getInt(6));
                jSONObject.put("phone", rs.getInt(5));
                jSONObject.put("email", rs.getString(4));
                jSONObject.put("address", rs.getString(3));
                jSONObject.put("studentName", rs.getString(2));
                jSONObject.put("idStudent", rs.getInt(1));
                jsonArray.add(jSONObject);
            }
        } catch (Exception ex) {
            System.out.println("Loi select by name" + ex);
        }
        return jsonArray;
    }
}

package controller;

import dao.DAOStudent;
import entity.Student;
import main.main;
import org.json.simple.JSONObject;
import security.JwtValidator;

public class StudentController extends Handler {

    @Override
    public String doProcessGet(String param, String token) {
        DAOStudent st = new DAOStudent(main.conn);
        Student student = JwtValidator.getInstance().validate(token);

        if (st.findRoleById(student.getIdStudent())==1)
        {
            if(param.contains("/findAll/getInfo/")){
                return student.toString();
            }
        }
        else if(st.findRoleById(student.getIdStudent())==0){
            if(param.contains("/findAll/getInfo/")){
                return student.toString();
            }

            if (param.contains("/findAll/")) {
                try {
                    int id = Integer.parseInt(param.substring(9));
                    return st.findById(id).toString();
                } catch (Exception e) {
                    return "invalid params";
                }
            }
            return st.listAll().toString();
        }
        return "";
    }

    @Override
    public String doProcessPost(JSONObject params,String token) {

        DAOStudent st = new DAOStudent(main.conn);
        Student student = new Student();
        JSONObject js = new JSONObject();

            try {
                student.setAddress(params.get("address").toString());
                student.setEmail(params.get("email").toString());
                student.setPhone(Integer.parseInt(params.get("phone").toString()));
                student.setSex(Integer.parseInt(params.get("sex").toString()));
                student.setNameStudent(params.get("student_name").toString());
                student.setPassword(params.get("password").toString());
                student.setToken(params.get("token").toString());
                System.out.println(params.get("token".toString()));
            } catch (Exception e) {
                js.put("rc", "-1");
                js.put("rd", e.toString());
                return js.toString();
            }

            if (st.addStudent(student) == 1) {
                js.put("rc", "1");
                js.put("rd", "add thanh cong 1 thang vao db");
            } else {
                js.put("rc", "-1");
                js.put("rd", "Err");
            }

        return js.toString();
//        return null;
    }

    @Override
    public String doProcessPut(JSONObject params,String token) {
        DAOStudent dao = new DAOStudent(main.conn);
        Student student = new Student();
        JSONObject js = new JSONObject();

        student.setNameStudent((String) params.get("student_name"));
        student.setAddress((String) params.get("address"));
        student.setEmail((String) params.get("email"));
        student.setPhone(Integer.parseInt(params.get("phone").toString()));
        student.setSex(Integer.parseInt(params.get("sex").toString()));
        student.setIdStudent(Integer.parseInt(params.get("id_student").toString()));

        if (dao.update(student) == 1) {
            js.put("rc", "1");
            js.put("rd", "sua thanh cong ");
        } else {
            js.put("rc", "-1");
            js.put("rd", "Err");
        }
        return js.toString();
    }

    @Override
    public String doProcessDelete(String param,String token) {
//        DAOStudent dao = new DAOStudent(main.conn);
//        int id = 0;
//        JSONObject js = new JSONObject();
//
//        if (param.contains("/findAll/")) {
//            try {
//                id = Integer.parseInt(param.substring(9));
//            } catch (Exception e) {
//                return "invalid params";
//            }
//        }
//
//        if (dao.delete(id) == 1) {
//            js.put("rc", "1");
//            js.put("rd", "xoa thanh cong ");
//        } else {
//            js.put("rc", "-1");
//            js.put("rd", "Err");
//        }
//        return js.toString();
        return null;
    }

}

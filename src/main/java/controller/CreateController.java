package controller;

import dao.DAOStudent;
import entity.Student;
import main.main;
import org.json.simple.JSONObject;
import security.JwtValidator;

public class CreateController extends Handler {
    @Override
    public String doProcessGet(String param, String token) {
        return null;
    }

    @Override
    public String doProcessPost(JSONObject params, String token) {
        DAOStudent st = new DAOStudent(main.conn);

        JSONObject js = new JSONObject();
        Student studentValidateToken = JwtValidator.getInstance().validate(token);

        if (st.findRoleById(studentValidateToken.getIdStudent()) == 1) {
            Student student = new Student();
            try {
                student.setAddress(params.get("address").toString());
                student.setEmail(params.get("email").toString());
                student.setPhone(Integer.parseInt(params.get("phone").toString()));
                student.setSex(Integer.parseInt(params.get("sex").toString()));
                student.setNameStudent(params.get("student_name").toString());
                student.setPassword(params.get("password").toString());
            } catch (Exception e) {
                js.put("rc", "-1");
                js.put("rd", e.toString());
                return js.toString();
            }

            if (st.addStudent(student) == 1) {
                js.put("rc", "1");
                js.put("rd", "Add success");
            } else {
                js.put("rc", "-1");
                js.put("rd", "Err");
            }
        }else{
            js.put("rc","-1");
            js.put("rd", "Not Defined");
        }
        return js.toString();
    }

    @Override
    public String doProcessPut(JSONObject params,String token) {
        return null;
    }

    @Override
    public String doProcessDelete(String param,String token) {
        return null;
    }
}

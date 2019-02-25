package controller;

import dao.DAOStudent;
import entity.Student;
import main.main;
import org.json.simple.JSONObject;
import security.JwtValidator;

public class CreateController extends Handler {
    @Override
    public String doProcessGet(String param, Student student) {
        return null;
    }

    @Override
    public String doProcessPost(JSONObject params, Student student) {
        DAOStudent st = new DAOStudent(main.conn);

        JSONObject js = new JSONObject();

        if (Integer.parseInt(student.getRole()) == 1) {
            Student stu = new Student();
            try {
                stu.setAddress(params.get("address").toString());
                stu.setEmail(params.get("email").toString());
                stu.setPhone(Integer.parseInt(params.get("phone").toString()));
                stu.setSex(Integer.parseInt(params.get("sex").toString()));
                stu.setNameStudent(params.get("student_name").toString());
                stu.setPassword(params.get("password").toString());
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
    public String doProcessPut(JSONObject params,Student student) {
        return null;
    }

    @Override
    public String doProcessDelete(String param,Student student) {
        return null;
    }
}

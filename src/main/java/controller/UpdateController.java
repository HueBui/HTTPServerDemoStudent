package controller;

import dao.DAOStudent;
import entity.Student;
import main.main;
import org.json.simple.JSONObject;
import security.JwtValidator;

public class UpdateController extends Handler {
    @Override
    public String doProcessGet(String param, Student stu) {
        return null;
    }

    @Override
    public String doProcessPost(JSONObject params, Student stu) {
        return null;
    }

    @Override
    public String doProcessPut(JSONObject params, Student stu) {
        DAOStudent dao = new DAOStudent(main.conn);
        Student student = new Student();
        JSONObject js = new JSONObject();


        if(Integer.parseInt(stu.getRole())==1 && Integer.parseInt(stu.getRole())==0){
            student.setNameStudent((String) params.get("student_name"));
            student.setAddress((String) params.get("address"));
            student.setEmail((String) params.get("email"));
            student.setPhone(Integer.parseInt(params.get("phone").toString()));
            student.setSex(Integer.parseInt(params.get("sex").toString()));
            student.setIdStudent(Integer.parseInt(params.get("id_student").toString()));

            if (dao.update(student) == 1) {
                js.put("rc", "1");
                js.put("rd", "Update success");
            } else {
                js.put("rc", "-1");
                js.put("rd", "Err");
            }
        } else {
            js.put("rc", "-1");
            js.put("rd", "Not Defined");
        }
        return js.toString();
    }

    @Override
    public String doProcessDelete(String param, Student stu) {
        return null;
    }
}

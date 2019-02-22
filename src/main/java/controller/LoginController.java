package controller;

import entity.Student;
import main.main;
import dao.DAOLogin;
import org.json.simple.JSONObject;
import security.JwtGenerator;
import security.JwtValidator;

public class LoginController extends HandlerNoUseToken {

    @Override
    public String doProcessPost(JSONObject params) {
        DAOLogin daoLogin = new DAOLogin(main.conn);
        Student student = new Student();
        JSONObject js = new JSONObject();

        try {
            student.setIdStudent(Integer.parseInt(params.get("idstudent").toString()));
            student.setNameStudent(params.get("student_name").toString());
            student.setPassword(params.get("password").toString());
        } catch (Exception e) {
            js.put("rc", "-1");
            js.put("rd", e.toString());
            return js.toString();
        }
        String token = JwtGenerator.getInstance().generate(student);

        //update token
        daoLogin.updateToken(student, token);

        if (daoLogin.login(student) == 1) {
            daoLogin.ckeck(student);
            js.put("rc", "1");
            js.put("rd", "Login success");
            js.put("token", token);
        } else {
            js.put("rc", "-1");
            js.put("rd", "Err");
        }

        return js.toString();
    }
}

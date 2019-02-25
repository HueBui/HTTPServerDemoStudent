package controller;

import dao.DAOLogout;
import entity.Student;
import main.main;
import org.json.simple.JSONObject;
import security.JwtValidator;

public class LogOutController extends Handler {

    @Override
    public String doProcessGet(String param, Student stu) {
        return null;
    }

    @Override
    public String doProcessPost(JSONObject params, Student stu) {
        DAOLogout daoLogout = new DAOLogout(main.conn);
        JSONObject jsonObject = new JSONObject();

        if(Integer.parseInt(stu.getRole())==1){
            jsonObject.put("rc","1");
            jsonObject.put("rd","Logout success");
        }
        else {
            jsonObject.put("rc","-1");
            jsonObject.put("rd","Err");
        }
        return jsonObject.toString();
    }

    @Override
    public String doProcessPut(JSONObject params, Student stu) {
        return null;
    }

    @Override
    public String doProcessDelete(String param, Student stu) {
        return null;
    }
}

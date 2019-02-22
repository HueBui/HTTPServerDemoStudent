package controller;

import dao.DAOLogout;
import entity.Student;
import main.main;
import org.json.simple.JSONObject;
import security.JwtValidator;

public class LogOutController extends Handler {

    @Override
    public String doProcessGet(String param, String token) {
        return null;
    }

    @Override
    public String doProcessPost(JSONObject params, String token) {
        DAOLogout daoLogout = new DAOLogout(main.conn);
        JSONObject jsonObject = new JSONObject();
        Student student = JwtValidator.getInstance().validate(token);
        if (daoLogout.removeToken(student)==1){
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
    public String doProcessPut(JSONObject params, String token) {
        return null;
    }

    @Override
    public String doProcessDelete(String param, String token) {
        return null;
    }
}

package controller;

import dao.DAOStudent;
import entity.Student;
import main.main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import security.JwtValidator;

public class FindAllController extends Handler {

    @Override
    public String doProcessGet(String param, String token) {
        DAOStudent st = new DAOStudent(main.conn);
        Student student = JwtValidator.getInstance().validate(token);
        JSONObject jsonObject = new JSONObject();

        JSONArray jsonArray = new JSONArray();

        if (st.findRoleById(student.getIdStudent())==1) {
            if (param.contains("/findAll")) {
                jsonObject.put("rc","1");
                jsonObject.put("rd","success");
                jsonObject.put("student",st.listAll());
            }
        }
        else{
            jsonObject.put("rc","-1");
            jsonObject.put("rd","Err");
        }
        return jsonObject.toString();
    }

    @Override
    public String doProcessPost(JSONObject params, String token) {
        return null;
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

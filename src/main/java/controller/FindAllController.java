package controller;

import dao.DAOStudent;
import entity.Student;
import main.main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import security.JwtValidator;

public class FindAllController extends Handler {

    @Override
    public String doProcessGet(String param, Student stu) {
        DAOStudent st = new DAOStudent(main.conn);

        JSONObject jsonObject = new JSONObject();

        JSONArray jsonArray = new JSONArray();

        if(Integer.parseInt(stu.getRole())==1){
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
    public String doProcessPost(JSONObject params, Student stu) {
        return null;
    }

    @Override
    public String doProcessPut(JSONObject params,Student stu) {
        return null;
    }

    @Override
    public String doProcessDelete(String param,Student stu) {
        return null;
    }
}

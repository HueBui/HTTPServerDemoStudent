package controller;

import dao.DAOStudent;
import entity.Student;
import main.main;
import org.json.simple.JSONObject;
import security.JwtValidator;

public class FindByIdController extends Handler {
    @Override
    public String doProcessGet(String param, Student stu) {
        DAOStudent st = new DAOStudent(main.conn);

        if(Integer.parseInt(stu.getRole())==1){
            if (param.contains("/findById/")) {
                try {
                    int id = Integer.parseInt(param.substring(10));
                    return st.findById(id).toString();
                } catch (Exception e) {
                    return "invalid params";
                }
            }
        }
        return "Null";
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

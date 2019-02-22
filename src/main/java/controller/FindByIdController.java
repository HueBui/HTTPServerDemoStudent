package controller;

import dao.DAOStudent;
import entity.Student;
import main.main;
import org.json.simple.JSONObject;
import security.JwtValidator;

public class FindByIdController extends Handler {
    @Override
    public String doProcessGet(String param, String token) {
        DAOStudent st = new DAOStudent(main.conn);
        Student student = JwtValidator.getInstance().validate(token);

        if (st.findRoleById(student.getIdStudent())==1 || st.findRoleById(student.getIdStudent())==0) {
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

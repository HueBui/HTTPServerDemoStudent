package controller;

import dao.DAOStudent;
import entity.Student;
import main.main;
import org.json.simple.JSONObject;
import security.JwtValidator;

public class DeleteController extends Handler {
    @Override
    public String doProcessGet(String param, String token) {
        return null;
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
        DAOStudent dao = new DAOStudent(main.conn);
        int id = 0;
        JSONObject js = new JSONObject();

        Student studentValidate = JwtValidator.getInstance().validate(token);
        if(dao.findRoleById(studentValidate.getIdStudent())==1){
            if (param.contains("/delete/")) {
                try {
                    id = Integer.parseInt(param.substring(8));
                } catch (Exception e) {
                    return "Invalid params";
                }
            }

            if (dao.delete(id) == 1) {
                js.put("rc", "1");
                js.put("rd", "Delete success");
            } else {
                js.put("rc", "-1");
                js.put("rd", "Err");
            }
        }else{
            js.put("rc", "-1");
            js.put("rd", "Not Defined");
        }

        return js.toString();
    }

}

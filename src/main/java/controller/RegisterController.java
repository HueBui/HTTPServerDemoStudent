package controller;
import dao.DAOLogin;
import entity.Student;
import main.main;
import dao.DAORegister;
import model.DBConnection;
import org.json.simple.JSONObject;
import security.JwtGenerator;

public class RegisterController extends HandlerNoUseToken {
    @Override
    public String doProcessPost(JSONObject params) {
        DAORegister daoRegister = new DAORegister(main.conn);
        Student student = new Student();
        JSONObject js = new JSONObject();

        try{
            student.setNameStudent(params.get("student_name").toString());
            student.setAddress(params.get("address").toString());
            student.setEmail(params.get("email").toString());
            student.setPhone(Integer.parseInt(params.get("phone").toString()));
            student.setPassword(params.get("password").toString());
            student.setSex(Integer.parseInt(params.get("sex").toString()));
            String token = JwtGenerator.getInstance().generate(student);
            student.setToken(token);
            if(daoRegister.register(student)==1){
                js.put("rc","1");
                js.put("rd","Register success");
                js.put("token ",token);

            }
        }catch (Exception ex){
            js.put("rc","-1");
            js.put("rd","Err");
        }

        return js.toString();
    }
}

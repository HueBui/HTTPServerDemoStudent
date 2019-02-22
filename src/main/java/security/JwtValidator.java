package security;

import entity.Student;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class JwtValidator {
    static private JwtValidator instance;

    private JwtValidator() {

    }

    public static JwtValidator getInstance() {
        if (instance == null) {
            instance = new JwtValidator();
        }
        return instance;
    }

    public Student validate(String token) {
        Student student = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("SECRET")
                    .parseClaimsJws(token)
                    .getBody();

            student = new Student();

            student.setIdStudent(Integer.parseInt(claims.get("id_student").toString()));
            student.setNameStudent(claims.get("name_student").toString());
            student.setPassword(claims.get("password").toString());

            System.out.println(claims);
            System.out.println(student);
        } catch (ExpiredJwtException ejwte) {
            System.err.println("Jwt Expired: " + ejwte);
        } catch (Exception e) {
            System.out.println(e);
        }
        return student;
    }
}
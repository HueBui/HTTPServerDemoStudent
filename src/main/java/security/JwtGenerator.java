package security;

import entity.Student;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtGenerator {
    private static JwtGenerator instance;

    private JwtGenerator() {
    }

    public static JwtGenerator getInstance() {
        if (instance == null) {
            instance = new JwtGenerator();
        }
        return instance;
    }

    public String generate(Student student) {
        Claims claims = Jwts.claims()
                .setSubject(student.getNameStudent());
        claims.put("id_student", student.getIdStudent());
        claims.put("name_student", student.getNameStudent());
        claims.put("password", student.getPassword());
        Date date = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, "SECRET")
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + 10000 * 60))
                .compact();
    }

}

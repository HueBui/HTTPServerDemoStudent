package controller;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entity.Student;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import security.JwtValidator;

import java.io.*;
import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public abstract class Handler implements HttpHandler {

    public void handle(HttpExchange he) throws IOException {

        Headers headers = he.getRequestHeaders();
        String method = he.getRequestMethod();
        URI uri = he.getRequestURI();
        System.out.println("uri = " + uri.toString());

        String response = null;

        String token = getTokenHeaderParams(he);

        Student student = validateToken(token);

        System.out.println(student);

        if (student != null) {
            System.out.println("Student khac null");
        }

        if ("GET".equals(method)) {
            try {
                response = doProcessGet(uri.toString(), student);
            } catch (Exception ex) {
                System.out.println("err");
            }

        } else if ("POST".equals(method)) {
            try {
                response = doProcessPost(getRequestBody(he), student);
            } catch (Exception e) {
                System.err.println("err");
            }

        } else if ("PUT".equals(method)) {
            try {
                response = doProcessPut(getRequestBody(he), student);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if ("DELETE".equals(method)) {
            try {
                response = doProcessDelete(uri.toString(), student);
            } catch (Exception ex) {
                System.out.println("err");
            }
        }

        sendResp(he, response);

    }

    public abstract String doProcessGet(String param, Student student);

    public abstract String doProcessPost(JSONObject params, Student student);

    public abstract String doProcessPut(JSONObject params, Student student);

    public abstract String doProcessDelete(String param, Student student);

    private JSONObject getRequestBody(HttpExchange arg0) throws IOException, ParseException, org.json.simple.parser.ParseException {
        InputStream ins = arg0.getRequestBody();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = ins.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        String data = result.toString("UTF-8");
        if (data == null || data.isEmpty()) {
            return null;
        }
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(data);

        return obj;
    }

    private void sendResp(HttpExchange http, String resp) throws IOException {
        if (http == null) {
            return;
        }
        http.sendResponseHeaders(200, resp.getBytes("UTF-8").length);
        try (OutputStream os = http.getResponseBody()) {
            os.write(resp.getBytes("UTF-8"));
        }
    }

    protected Student validateToken(String token) {
        Student student = JwtValidator.getInstance().validate(token);
        return student;
    }

    protected String getTokenHeaderParams(HttpExchange headersEx) {
        String token = null;
        Headers requestHeaders = headersEx.getRequestHeaders();
        for (Map.Entry<String, List<String>> header : requestHeaders.entrySet()) {
            String key = header.getKey();
            List<String> value = header.getValue();

            if (key.equals("Token")) {
                token = value.get(0);
            }
        }
        return token;
    }

}

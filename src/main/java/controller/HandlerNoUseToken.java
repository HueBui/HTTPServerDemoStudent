package controller;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.text.ParseException;

public abstract class HandlerNoUseToken implements HttpHandler {
    public void handle(HttpExchange he) throws IOException {

        Headers headers = he.getRequestHeaders();
        String method = he.getRequestMethod();
        URI uri = he.getRequestURI();
        System.out.println("uri = " + uri.toString());

        String response = null;

        if ("GET".equals(method)) {

        } else if ("POST".equals(method)) {
            try {
                response =  doProcessPost(getRequestBody(he));
            } catch (Exception e) {
                System.err.println("err");
            }
        } else if ("PUT".equals(method)) {

        } else if ("DELETE".equals(method)) {
        }

        sendResp(he, response);
    }
    public abstract String doProcessPost(JSONObject params);

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
}

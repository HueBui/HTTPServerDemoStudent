package main;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import controller.*;
import model.DBConnection;

import java.io.IOException;
import java.net.InetSocketAddress;

public class main {
    public static DBConnection conn = new DBConnection();
    public static HttpExchange he;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/register", new RegisterController());
        server.createContext("/login", new LoginController());
        server.createContext("/logout", new LogOutController());

        server.createContext("/findAll", new FindAllController());
        server.createContext("/findById", new FindByIdController());
        server.createContext("/findByName", new FindByNameController());
        server.createContext("/create", new CreateController());
        server.createContext("/update", new UpdateController());
        server.createContext("/delete", new DeleteController());

        System.out.println("Start Port: " + server.getAddress());
        server.start();

    }
}

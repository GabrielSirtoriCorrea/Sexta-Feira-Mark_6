package FridayInterface;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServerConnection {
    private JSONObject jsonObject;
    private JSONObject jsonRequest;
    private JSONObject jsonResponse;
    private JSONObject jsonInterfaceObject;
    private JSONParser parser;
    private String ip = Configurations.getConfig("ServerHost");
    private int port = Integer.parseInt(Configurations.getConfig("Port"));
    private Socket socket;
    private PrintStream out;
    private BufferedReader in;

    public ServerConnection() {
        try {
            this.socket = new Socket(this.ip, this.port);
            this.out = new PrintStream(this.socket.getOutputStream());
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String send(String request, String status, String action, String url){
        jsonRequest = new JSONObject();
        jsonRequest.put("header", "gazeboindustries09082004");
        jsonRequest.put("request", request);
        jsonRequest.put("status", status);
        jsonRequest.put("action", action);
        jsonRequest.put("url", url);

        this.out.print(jsonRequest);

        String line = this.in.readLine();
        
        return null;
    }

}
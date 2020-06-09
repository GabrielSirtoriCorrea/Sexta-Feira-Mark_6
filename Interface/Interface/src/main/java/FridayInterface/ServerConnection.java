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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
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

    public String receive(){

        jsonRequest = new JSONObject();
        jsonRequest.put("header", "gazeboindustries09082004");
        jsonRequest.put("request", "getDevicesStatus");

        this.out.print(jsonRequest);


        char[] buffer = new char[1024];
        try {
            System.out.println(this.in.read(buffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        jsonResponse = new JSONObject();
        String data = new String(buffer);

        System.out.println(data);

        try {
            jsonResponse = (JSONObject) JSONValue.parse(data.trim());     
            System.out.println(jsonResponse.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

}
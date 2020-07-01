package com.gazeboindustries.sextafeiramobile;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerConnection extends AsyncTask<String, Integer, String> {
    private String IP = "192.168.0.5";
    private int port = 5000;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private JSONObject jsonRequest;
    private JSONObject jsonResponse;
    private String data;
    private ArrayList<JSONArray> list;
    private JSONArray arrayResponse;
    private char[] buffer = new char[9000];

    public ServerConnection(String request){
        execute(request);
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            this.socket = new Socket(IP, port);
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            this.jsonRequest = new JSONObject();
            this.jsonRequest.put("header", "gazeboindustries09082004");
            this.jsonRequest.put("request", params[0]);

            this.out.println(this.jsonRequest);

            this.in.read(this.buffer);

            this.data = new String(this.buffer);

            this.jsonResponse = new JSONObject(this.data.trim());

            System.out.println(this.jsonResponse);

            this.list = new ArrayList<>();

            for (int c = 0; c < jsonResponse.length(); c++){
                this.arrayResponse = (JSONArray) this.jsonResponse.get(Integer.toString(c));
                this.list.add(arrayResponse);
            }

            this.socket.close();
            this.out.close();
            this.in.close();

        } catch (IOException | JSONException e) {
            System.out.println("DEU ERRO");
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<JSONArray> getListResponse(){
        return this.list;
    }
}

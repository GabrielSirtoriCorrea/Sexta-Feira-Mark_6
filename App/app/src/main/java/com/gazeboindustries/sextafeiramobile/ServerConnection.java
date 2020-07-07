package com.gazeboindustries.sextafeiramobile;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class ServerConnection extends AsyncTask<JSONObject, Integer, ArrayList<JSONArray>> {
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

    public ArrayList<JSONArray> sendRequest(JSONObject request){
        try {
            execute(request);
            sleep(4500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this.list;

    }

    @Override
    protected ArrayList<JSONArray> doInBackground(JSONObject... params) {
        try {
            this.socket = new Socket(IP, port);
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            this.out.println(params[0]);

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

    public JSONObject prepareRequest(String request){
        try {
            this.jsonRequest = new JSONObject();
            this.jsonRequest.put("header", "gazeboindustries09082004");
            this.jsonRequest.put("request", request);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonRequest;
    }

}

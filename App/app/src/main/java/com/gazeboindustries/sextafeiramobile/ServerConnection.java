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
    private ArrayList<JSONArray> list = null;
    private JSONArray arrayResponse;
    private char[] buffer = new char[9000];
    private boolean msgStatus = false;


    @Override
    protected ArrayList<JSONArray> doInBackground(JSONObject... params) {
        try {
            this.socket = new Socket(IP, port);
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            this.out.println(params[0]);

            this.msgStatus = true;

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

    public boolean getMsgStatus(){
        return this.msgStatus;
    }

    public ArrayList<JSONArray> sendRequest(JSONObject request){
        try {
            execute(request);
            while(this.list == null){
                sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this.list;

    }

    public JSONObject prepareInteraction(String request, String key1, String key2, String key3, String res1, String res2, String res3, String command){
        try {
            this.jsonRequest = new JSONObject();
            this.jsonRequest.put("header", "gazeboindustries09082004");
            this.jsonRequest.put("request", request);
            this.jsonRequest.put("keyWord1", key1);
            this.jsonRequest.put("keyWord2", key2);
            this.jsonRequest.put("keyWord3", key3);
            this.jsonRequest.put("response1", res1);
            this.jsonRequest.put("response2", res2);
            this.jsonRequest.put("response3", res3);
            this.jsonRequest.put("command", command);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonRequest;
    }

    public JSONObject prepareDevice(String request, String device, String desc, String json){
        try {
            this.jsonRequest = new JSONObject();
            this.jsonRequest.put("header", "gazeboindustries09082004");
            this.jsonRequest.put("request", request);
            this.jsonRequest.put("device", device);
            this.jsonRequest.put("description", desc);
            this.jsonRequest.put("json", json);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonRequest;
    }

    public JSONObject prepareHomework(String request, String type, String subject, String homework, String delivery, String desc){
        try {
            this.jsonRequest = new JSONObject();
            this.jsonRequest.put("header", "gazeboindustries09082004");
            this.jsonRequest.put("request", request);
            this.jsonRequest.put("type", type);
            this.jsonRequest.put("subject", subject);
            this.jsonRequest.put("homeWork", homework);
            this.jsonRequest.put("delivery", delivery);
            this.jsonRequest.put("description", desc);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonRequest;
    }

    public JSONObject prepareProject(String request, String project, String desc){
        try {
            this.jsonRequest = new JSONObject();
            this.jsonRequest.put("header", "gazeboindustries09082004");
            this.jsonRequest.put("request", request);
            this.jsonRequest.put("project", project);
            this.jsonRequest.put("repository", desc);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonRequest;
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

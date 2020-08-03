package com.gazeboindustries.friday;

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
    //private String IP = "gazeboindustries.hopto.org";
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
    private char[] buffer;
    private boolean msgStatus = false;


    @Override
    protected ArrayList<JSONArray> doInBackground(JSONObject... params) {
        try {
            if(params[0].get("request").equals("getDevicesStatus")) {
                this.socket = new Socket(IP, port);
                this.out = new PrintWriter(this.socket.getOutputStream(), true);
                this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

                this.out.println(params[0]);

                this.msgStatus = true;

                try {
                    sleep(125);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.buffer = new char[this.socket.getReceiveBufferSize()];

                this.in.read(this.buffer);

                this.data = new String(this.buffer);

                this.jsonResponse = new JSONObject(this.data);

                System.out.println(this.jsonResponse);

                this.socket.close();
                this.out.close();
                this.in.close();

            }else{
                this.socket = new Socket(IP, port);
                this.out = new PrintWriter(this.socket.getOutputStream(), true);
                this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

                this.out.println(params[0]);

                this.msgStatus = true;

                try {
                    sleep(125);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.buffer = new char[this.socket.getReceiveBufferSize()];

                this.in.read(this.buffer);

                this.data = new String(this.buffer);

                this.jsonResponse = new JSONObject(this.data);

                System.out.println(this.jsonResponse);

                this.list = new ArrayList<>();

                for (int c = 0; c < jsonResponse.length(); c++) {
                    this.arrayResponse = (JSONArray) this.jsonResponse.get(Integer.toString(c));
                    this.list.add(arrayResponse);
                }

                this.socket.close();
                this.out.close();
                this.in.close();
            }

        } catch (IOException | JSONException e) {
            System.out.println("DEU ERRO");
            e.printStackTrace();
        }
        return null;
    }

    public boolean getMsgStatus(){
        return this.msgStatus;
    }

    public JSONObject sendJSONRequest(JSONObject request){
        try {
            execute(request);
            while(this.jsonResponse == null){
                sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this.jsonResponse;

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

    public JSONObject prepareAddInteraction(String request, String key1, String key2, String key3, String res1, String res2, String res3, String command){
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

    public JSONObject prepareAddDevice(String request, String device, String desc, int actions){
        try {
            this.jsonRequest = new JSONObject();
            this.jsonRequest.put("header", "gazeboindustries09082004");
            this.jsonRequest.put("request", request);
            this.jsonRequest.put("device", device);
            this.jsonRequest.put("description", desc);
            this.jsonRequest.put("actions", actions);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonRequest;
    }

    public JSONObject prepareAddHomework(String request, String type, String subject, String homework, String delivery, String desc){
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

    public JSONObject prepareAddProject(String request, String type, String project){
        try {
            this.jsonRequest = new JSONObject();
            this.jsonRequest.put("header", "gazeboindustries09082004");
            this.jsonRequest.put("request", request);
            this.jsonRequest.put("type", type);
            this.jsonRequest.put("project", project);

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

    public JSONObject prepareUpdateInteraction(String request, int updateId, String key1, String key2, String key3, String res1, String res2, String res3, String command){
        try {
            this.jsonRequest = new JSONObject();
            this.jsonRequest.put("header", "gazeboindustries09082004");
            this.jsonRequest.put("request", request);
            this.jsonRequest.put("updateId", updateId);
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

    public JSONObject prepareUpdateDevice(String request, String deleteName, int updateId, String device, String desc, int actions){
        try {
            this.jsonRequest = new JSONObject();
            this.jsonRequest.put("header", "gazeboindustries09082004");
            this.jsonRequest.put("request", request);
            this.jsonRequest.put("deleteName", deleteName);
            this.jsonRequest.put("updateId", updateId);
            this.jsonRequest.put("device", device);
            this.jsonRequest.put("description", desc);
            this.jsonRequest.put("actions", actions);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonRequest;
    }

    public JSONObject prepareUpdateHomework(String request, int updateId, String type, String subject, String homework, String delivery, String desc){
        try {
            this.jsonRequest = new JSONObject();
            this.jsonRequest.put("header", "gazeboindustries09082004");
            this.jsonRequest.put("request", request);
            this.jsonRequest.put("updateId", updateId);
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

    public JSONObject prepareUpdateProject(String request, int updateId, String type, String project){
        try {
            this.jsonRequest = new JSONObject();
            this.jsonRequest.put("header", "gazeboindustries09082004");
            this.jsonRequest.put("request", request);
            this.jsonRequest.put("updateId", updateId);
            this.jsonRequest.put("type", type);
            this.jsonRequest.put("project", project);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonRequest;
    }

    public JSONObject prepareDelete(String request, int deleteId){
        try {
            this.jsonRequest = new JSONObject();
            this.jsonRequest.put("header", "gazeboindustries09082004");
            this.jsonRequest.put("request", request);
            this.jsonRequest.put("deleteId", deleteId);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonRequest;
    }

    public JSONObject prepareDelete(String request, int deleteId, String name){
        try {
            this.jsonRequest = new JSONObject();
            this.jsonRequest.put("header", "gazeboindustries09082004");
            this.jsonRequest.put("request", request);
            this.jsonRequest.put("deleteId", deleteId);
            this.jsonRequest.put("deleteName", name);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonRequest;
    }

    public JSONObject prepareSetDevice(String device, int action, String url){
        try {
            this.jsonRequest = new JSONObject();
            this.jsonRequest.put("header", "gazeboindustries09082004");
            this.jsonRequest.put("request", "setDevicesStatus");
            this.jsonRequest.put("receiverID", device);
            this.jsonRequest.put("action", action);
            this.jsonRequest.put("url", url);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonRequest;

    }


}

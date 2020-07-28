package com.gazeboindustries.friday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ListItemRow extends ArrayAdapter<JSONArray> {

    private Context context;
    private ArrayList<JSONArray> list;
    private LayoutInflater inflater;
    private View rowView;

    private TextView listID;
    private TextView listField1;
    private TextView listField2;
    private TextView listField3;

    private JSONArray arrayLine;

    private String data;

    public ListItemRow(Context context, ArrayList<JSONArray> list, String data) {
        super(context, R.layout.list_itemrow, list);
        this.context = context;
        this.list = list;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.list_itemrow, parent, false);

        listID = rowView.findViewById(R.id.listID);
        listField1 = rowView.findViewById(R.id.listField1);
        listField2 = rowView.findViewById(R.id.listField2);
        listField3 = rowView.findViewById(R.id.listField3);


        try {
            switch (data) {
                case "HomeWork":
                    arrayLine = list.get(position);

                    listID.setText(arrayLine.get(0).toString());
                    listField1.setText(arrayLine.get(2).toString());
                    listField2.setText(arrayLine.get(3).toString());
                    listField3.setText(arrayLine.get(4).toString());
                    break;
                case "Interactions":
                    arrayLine = list.get(position);

                    listID.setText(arrayLine.get(0).toString());
                    listField1.setText(arrayLine.get(1).toString());
                    listField2.setText(arrayLine.get(2).toString());
                    listField3.setText(arrayLine.get(3).toString());
                    break;
                case "Devices":
                    arrayLine = list.get(position);

                    listID.setText(arrayLine.get(0).toString());
                    listField1.setText(arrayLine.get(1).toString());
                    listField2.setText(arrayLine.get(2).toString());
                    listField3.setText(arrayLine.get(3).toString());
                    break;
                case "Projects":
                    arrayLine = list.get(position);

                    listID.setText(arrayLine.get(0).toString());
                    listField1.setText(arrayLine.get(1).toString());
                    listField2.setText(arrayLine.get(2).toString());
                    break;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rowView;
    }
}

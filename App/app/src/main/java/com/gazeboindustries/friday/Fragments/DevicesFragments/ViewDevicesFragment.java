package com.gazeboindustries.friday.Fragments.DevicesFragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gazeboindustries.friday.R;
import com.gazeboindustries.friday.ServerConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class ViewDevicesFragment extends Fragment {
    private Intent intent;
    private EditText txtDevice;
    private EditText txtDescription;
    private EditText txtActions;

    private Button btnEditSend;
    private Button btnDeleteCancel;
    private Button btnAction;
    private Button btnOnOff;

    private Drawable cancelIcon;
    private Drawable saveIcon;

    private Drawable editIcon;
    private Drawable removeIcon;

    private int deviceAction;
    private int ID;

    private ServerConnection connection;

    private AlertDialog.Builder removeAlert;
    private AlertDialog removeDialog;

    private JSONObject status;
    private JSONObject deviceStatus;


    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewdevice, container, false);

        connection = new ServerConnection();

        txtDevice = view.findViewById(R.id.txtViewDevice);
        txtDescription = view.findViewById(R.id.txtViewDeviceDescription);
        txtActions = view.findViewById(R.id.txtViewDeviceActions);
        btnEditSend = view.findViewById(R.id.btnEditDevice);
        btnDeleteCancel = view.findViewById(R.id.btnRemoveDevice);
        btnAction = view.findViewById(R.id.btnActionDevice);
        btnOnOff = view.findViewById(R.id.btnOnOffDevice);

        intent = getActivity().getIntent();

        ID = intent.getIntExtra("ID", 0);
        txtDevice.setText(intent.getStringExtra("Device"));
        txtDescription.setText(intent.getStringExtra("Description"));
        txtActions.setText(Integer.toString(intent.getIntExtra("Actions", 0)));

        try {
            status = (JSONObject) connection.sendJSONRequest(connection.prepareRequest("getDevicesStatus"));
            deviceStatus = (JSONObject) status.get(txtDevice.getText().toString());
            System.out.println(deviceStatus.toString());
            deviceAction = deviceStatus.getInt("action");
            if(deviceAction != 0){
                btnOnOff.setText("Desligar");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        removeAlert = new AlertDialog.Builder(view.getContext());
        removeAlert.setMessage("Deseja remover o device?");
        removeAlert.setCancelable(false);

        removeAlert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "Cancelado", Toast.LENGTH_SHORT).show();
            }
        });

        removeAlert.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                connection = null;
                connection = new ServerConnection();
                connection.sendRequest(connection.prepareDelete("deleteDevice", ID, txtDevice.getText().toString()));

                if(connection.getMsgStatus()) {
                    Toast.makeText(getContext(), "Excluído", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Erro ao remover device", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEditSend.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(btnEditSend.getText().equals("Editar")){
                    txtDevice.setEnabled(true);
                    txtDescription.setEnabled(true);
                    txtActions.setEnabled(true);

                    btnDeleteCancel.setText("Cancelar");
                    btnEditSend.setText("Salvar");

                    cancelIcon = getResources().getDrawable(R.drawable.ic_clear_black_24dp);
                    saveIcon = getResources().getDrawable(R.drawable.ic_save_black_24dp);

                    btnEditSend.setCompoundDrawablesWithIntrinsicBounds(saveIcon, null, null, null);
                    btnDeleteCancel.setCompoundDrawablesWithIntrinsicBounds(cancelIcon, null, null, null);

                }else{
                    connection = null;
                    connection = new ServerConnection();
                    connection.sendRequest(connection.prepareUpdateDevice("updateDevice",intent.getStringExtra("Device"), ID, txtDevice.getText().toString(), txtDescription.getText().toString(),
                            Integer.parseInt(txtActions.getText().toString())));

                    txtDevice.setEnabled(false);
                    txtDescription.setEnabled(false);
                    txtActions.setEnabled(false);

                    btnDeleteCancel.setText("Excluir");
                    btnEditSend.setText("Editar");

                    editIcon = getResources().getDrawable(R.drawable.ic_edit_black_24dp);
                    removeIcon = getResources().getDrawable(R.drawable.ic_delete_black_24dp);

                    btnEditSend.setCompoundDrawablesWithIntrinsicBounds(editIcon, null, null, null);
                    btnDeleteCancel.setCompoundDrawablesWithIntrinsicBounds(removeIcon, null, null, null);

                    intent.putExtra("ID", ID);
                    intent.putExtra("Device", txtDevice.getText().toString());
                    intent.putExtra("Description", txtDescription.getText().toString());
                    intent.putExtra("Actions", Integer.parseInt(txtActions.getText().toString()));

                    if(connection.getMsgStatus()){
                        Toast.makeText(getContext(), "Salvo", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Erro ao salvar o device", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnDeleteCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnDeleteCancel.getText().equals("Excluir")){
                    removeDialog = removeAlert.create();
                    removeDialog.show();
                }else{
                    txtDevice.setEnabled(false);
                    txtDescription.setEnabled(false);

                    btnDeleteCancel.setText("Excluir");
                    btnEditSend.setText("Editar");

                    editIcon = getResources().getDrawable(R.drawable.ic_edit_black_24dp);
                    removeIcon = getResources().getDrawable(R.drawable.ic_delete_black_24dp);

                    btnEditSend.setCompoundDrawablesWithIntrinsicBounds(editIcon, null, null, null);
                    btnDeleteCancel.setCompoundDrawablesWithIntrinsicBounds(removeIcon, null, null, null);

                    txtDevice.setText(intent.getStringExtra("Device"));
                    txtDescription.setText(intent.getStringExtra("Description"));
                    txtDescription.setText(intent.getIntExtra("Actions", 0));

                }
            }
        });

        btnOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connection = null;
                connection = new ServerConnection();

                if(btnOnOff.getText().equals("Ligar")){
                    connection.sendRequest(connection.prepareSetDevice(txtDevice.getText().toString(), 1, ".com"));
                    deviceAction = 1;
                    btnOnOff.setText("Desligar");
                }else{
                    connection.sendRequest(connection.prepareSetDevice(txtDevice.getText().toString(), 0, ".com"));
                    deviceAction = 0;
                    btnOnOff.setText("Ligar");

                }
            }
        });

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connection = null;
                connection = new ServerConnection();
                if(deviceAction != 0){
                    deviceAction++;
                    connection.sendRequest(connection.prepareSetDevice(txtDevice.getText().toString(), deviceAction, ".com"));
                }
            }
        });

        return view;
    }
}

package com.gazeboindustries.friday.Fragments.DevicesFragments;

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

public class AddDeviceFragment extends Fragment {
    private Button btnAddDevice;
    private EditText device;
    private EditText desc;
    private EditText actions;

    private ServerConnection connection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adddevice, container, false);

        btnAddDevice = view.findViewById(R.id.btnAddDevice);

        device = view.findViewById(R.id.txtAddDevice);
        desc = view.findViewById(R.id.txtAddDeviceDescription);
        actions = view.findViewById(R.id.txtAddDeviceActions);

        btnAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connection = new ServerConnection();

                connection.sendRequest(connection.prepareAddDevice("insertDevice", device.getText().toString(), desc.getText().toString(), Integer.parseInt(actions.getText().toString())));

                if(connection.getMsgStatus()){
                    Toast.makeText(view.getContext(), "Device adicionado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(view.getContext(), "Erro ao adicionar device", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}

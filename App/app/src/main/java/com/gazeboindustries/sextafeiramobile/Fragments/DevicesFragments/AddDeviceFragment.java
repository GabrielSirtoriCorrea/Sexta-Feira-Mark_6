package com.gazeboindustries.sextafeiramobile.Fragments.DevicesFragments;

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

import com.gazeboindustries.sextafeiramobile.R;
import com.gazeboindustries.sextafeiramobile.ServerConnection;

public class AddDeviceFragment extends Fragment {
    private Button btnAddDevice;
    private EditText device;
    private EditText desc;

    private ServerConnection connection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adddevice, container, false);

        btnAddDevice = view.findViewById(R.id.btnAddDevice);

        device = view.findViewById(R.id.txtAddDevice);
        desc = view.findViewById(R.id.txtAddDeviceDescription);

        btnAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connection = new ServerConnection();

                connection.sendRequest(connection.prepareDevice("insertDevice", device.getText().toString(), desc.getText().toString(), "json"));

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

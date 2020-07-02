package com.gazeboindustries.sextafeiramobile.Fragments.DevicesFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gazeboindustries.sextafeiramobile.R;

public class ViewDevicesFragment extends Fragment {
    private Intent intent;
    private EditText txtDevice;
    private EditText txtDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewdevice, container, false);

        txtDevice = view.findViewById(R.id.txtViewDevice);
        txtDescription = view.findViewById(R.id.txtViewDeviceDescription);

        intent = getActivity().getIntent();

        txtDevice.setText(intent.getSerializableExtra("Device").toString());
        txtDescription.setText(intent.getSerializableExtra("Description").toString());

        return view;
    }
}

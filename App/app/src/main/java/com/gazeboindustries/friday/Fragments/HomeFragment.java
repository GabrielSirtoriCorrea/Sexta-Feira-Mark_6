package com.gazeboindustries.friday.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gazeboindustries.friday.R;
import com.gazeboindustries.friday.ServerConnection;

public class HomeFragment extends Fragment {
    private Button startFriday;
    private ServerConnection connection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        connection = new ServerConnection();

        startFriday = view.findViewById(R.id.btnStartFriday);

        startFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connection.sendRequest(connection.prepareRequest("startFriday"));
            }
        });

        return view;
    }
}

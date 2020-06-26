package com.gazeboindustries.sextafeiramobile.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.gazeboindustries.sextafeiramobile.MainActivity;
import com.gazeboindustries.sextafeiramobile.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InteractionFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interactions, container, false);

        Button btnAddInteraction = view.findViewById(R.id.btnAddInteraction);

        btnAddInteraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frame, new AddInteractionFragment()).commit();
            }
        });

        return view;
    }

}

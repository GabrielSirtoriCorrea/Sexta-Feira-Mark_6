package com.gazeboindustries.sextafeiramobile.Fragments.InteractionsFragments;

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

public class ViewInteractionFragment extends Fragment {
    private Intent intent;
    private EditText keyWord1;
    private EditText keyWord2;
    private EditText keyWord3;
    private EditText response1;
    private EditText response2;
    private EditText response3;
    private EditText command;

    private String KeyWord1Text;
    private String KeyWord2Text;
    private String KeyWord3Text;
    private String Response1Text;
    private String Response2Text;
    private String Response3Text;
    private String CommandText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewinteractions, container, false);

        keyWord1 = view.findViewById(R.id.txtViewInteractionKeyword1);
        keyWord2 = view.findViewById(R.id.txtViewInteractionKeyword2);
        keyWord3 = view.findViewById(R.id.txtViewInteractionKeyword3);
        response1 = view.findViewById(R.id.txtViewInteractionResponse1);
        response2 = view.findViewById(R.id.txtViewInteractionResponse2);
        response3 = view.findViewById(R.id.txtViewInteractionResponse3);
        command = view.findViewById(R.id.txtViewInteractionCommand);

        intent = getActivity().getIntent();

        /*KeyWord1Text = intent.getStringExtra("Keyword1");
        KeyWord2Text = intent.getStringExtra("Keyword2");
        KeyWord3Text = intent.getStringExtra("Keyword3");
        Response1Text = intent.getStringExtra("Response1");
        Response2Text = intent.getStringExtra("Response2");
        Response3Text = intent.getStringExtra("Response3");
        CommandText = intent.getStringExtra("Command");*/

        keyWord1.setText(intent.getStringExtra("Keyword1"));
        keyWord2.setText(intent.getStringExtra("Keyword2"));
        keyWord3.setText(intent.getStringExtra("Keyword3"));
        response1.setText(intent.getStringExtra("Response1"));
        response2.setText(intent.getStringExtra("Response2"));
        response3.setText(intent.getStringExtra("Response3"));
        command.setText(intent.getStringExtra("Command"));


        return view;
    }
}

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewhomework, container, false);

        keyWord1 = view.findViewById(R.id.txtViewInteractionKeyword1);
        keyWord2 = view.findViewById(R.id.txtViewInteractionKeyword2);
        keyWord3 = view.findViewById(R.id.txtViewInteractionKeyword3);
        response1 = view.findViewById(R.id.txtViewInteractionResponse1);
        response2 = view.findViewById(R.id.txtViewInteractionResponse2);
        response3 = view.findViewById(R.id.txtViewInteractionResponse3);
        command = view.findViewById(R.id.txtViewInteractionCommand);

        intent = getActivity().getIntent();

        System.out.println(intent.getSerializableExtra("Keyword1").toString());
        System.out.println(intent.getSerializableExtra("Keyword2").toString());
        System.out.println(intent.getSerializableExtra("Keyword3").toString());
        System.out.println(intent.getSerializableExtra("Response1").toString());
        System.out.println(intent.getSerializableExtra("Response2").toString());
        System.out.println(intent.getSerializableExtra("Response3").toString());
        System.out.println(intent.getSerializableExtra("Command").toString());


        /*keyWord1.setText(intent.getSerializableExtra("Keyword1").toString());
        keyWord2.setText(intent.getSerializableExtra("Keyword2").toString());
        keyWord3.setText(intent.getSerializableExtra("Keyword3").toString());
        response1.setText(intent.getSerializableExtra("Response1").toString());
        response2.setText(intent.getSerializableExtra("Response2").toString());
        response3.setText(intent.getSerializableExtra("Response3").toString());
        command.setText(intent.getSerializableExtra("Command").toString());
        */

        return view;
    }
}

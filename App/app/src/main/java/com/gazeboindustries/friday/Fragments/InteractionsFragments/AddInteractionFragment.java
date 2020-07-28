package com.gazeboindustries.friday.Fragments.InteractionsFragments;

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

public class AddInteractionFragment extends Fragment {
    private ServerConnection connection;
    private Button btnAddInteraction;

    private EditText key1;
    private EditText key2;
    private EditText key3;
    private EditText res1;
    private EditText res2;
    private EditText res3;
    private EditText command;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addinteraction, container, false);

        btnAddInteraction = view.findViewById(R.id.btnAddInteraction);

        key1 = view.findViewById(R.id.txtKeyword1);
        key2 = view.findViewById(R.id.txtKeyword2);
        key3 = view.findViewById(R.id.txtKeyword3);
        res1 = view.findViewById(R.id.txtResponse1);
        res2 = view.findViewById(R.id.txtResponse2);
        res3 = view.findViewById(R.id.txtResponse3);
        command = view.findViewById(R.id.txtCommand);

        btnAddInteraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connection = new ServerConnection();

                connection.sendRequest(connection.prepareAddInteraction("insertInteraction", key1.getText().toString(), key2.getText().toString(),
                        key3.getText().toString(), res1.getText().toString(), res2.getText().toString(), res3.getText().toString(),
                        command.getText().toString()));
                if(connection.getMsgStatus()){
                    Toast.makeText(view.getContext(), "Interação adicionada", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(view.getContext(), "Erro ao adicionar interação", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}

package com.gazeboindustries.friday.Fragments.InteractionsFragments;

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

public class ViewInteractionFragment extends Fragment {
    private Intent intent;
    private int ID;
    private EditText keyWord1;
    private EditText keyWord2;
    private EditText keyWord3;
    private EditText response1;
    private EditText response2;
    private EditText response3;
    private EditText command;
    private Button btnEditSend;
    private Button btnDeleteCancel;

    private ServerConnection connection;
    private AlertDialog.Builder removeAlert;
    private AlertDialog removeDialog;

    private Drawable cancelIcon;
    private Drawable saveIcon;

    private Drawable editIcon;
    private Drawable removeIcon;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewinteractions, container, false);

        connection = new ServerConnection();

        keyWord1 = view.findViewById(R.id.txtViewInteractionKeyword1);
        keyWord2 = view.findViewById(R.id.txtViewInteractionKeyword2);
        keyWord3 = view.findViewById(R.id.txtViewInteractionKeyword3);
        response1 = view.findViewById(R.id.txtViewInteractionResponse1);
        response2 = view.findViewById(R.id.txtViewInteractionResponse2);
        response3 = view.findViewById(R.id.txtViewInteractionResponse3);
        command = view.findViewById(R.id.txtViewInteractionCommand);

        btnEditSend = view.findViewById(R.id.btnEditInteraction);
        btnDeleteCancel = view.findViewById(R.id.btnRemoveInteraction);

        intent = getActivity().getIntent();

        ID = intent.getIntExtra("ID", 0);
        keyWord1.setText(intent.getStringExtra("Keyword1"));
        keyWord2.setText(intent.getStringExtra("Keyword2"));
        keyWord3.setText(intent.getStringExtra("Keyword3"));
        response1.setText(intent.getStringExtra("Response1"));
        response2.setText(intent.getStringExtra("Response2"));
        response3.setText(intent.getStringExtra("Response3"));
        command.setText(intent.getStringExtra("Command"));

        removeAlert = new AlertDialog.Builder(view.getContext());
        removeAlert.setMessage("Deseja remover a interação?");
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
                connection.sendRequest(connection.prepareDelete("deleteInteraction", ID));

                if(connection.getMsgStatus()) {
                    Toast.makeText(getContext(), "Excluído", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Erro ao remover Interação", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEditSend.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(btnEditSend.getText().equals("Editar")){
                    keyWord1.setEnabled(true);
                    keyWord2.setEnabled(true);
                    keyWord3.setEnabled(true);
                    response1.setEnabled(true);
                    response2.setEnabled(true);
                    response3.setEnabled(true);
                    command.setEnabled(true);

                    btnDeleteCancel.setText("Cancelar");
                    btnEditSend.setText("Salvar");

                    cancelIcon = getResources().getDrawable(R.drawable.ic_clear_black_24dp);
                    saveIcon = getResources().getDrawable(R.drawable.ic_save_black_24dp);

                    btnEditSend.setCompoundDrawablesWithIntrinsicBounds(saveIcon, null, null, null);
                    btnDeleteCancel.setCompoundDrawablesWithIntrinsicBounds(cancelIcon, null, null, null);


                }else{

                    connection.sendRequest(connection.prepareUpdateInteraction("updateInteraction", ID, keyWord1.getText().toString(), keyWord2.getText().toString(),
                            keyWord3.getText().toString(), response1.getText().toString(), response2.getText().toString(), response3.getText().toString(),
                            command.getText().toString()));

                    keyWord1.setEnabled(false);
                    keyWord2.setEnabled(false);
                    keyWord3.setEnabled(false);
                    response1.setEnabled(false);
                    response2.setEnabled(false);
                    response3.setEnabled(false);
                    command.setEnabled(false);

                    btnDeleteCancel.setText("Excluir");
                    btnEditSend.setText("Editar");

                    editIcon = getResources().getDrawable(R.drawable.ic_edit_black_24dp);
                    removeIcon = getResources().getDrawable(R.drawable.ic_delete_black_24dp);

                    btnEditSend.setCompoundDrawablesWithIntrinsicBounds(editIcon, null, null, null);
                    btnDeleteCancel.setCompoundDrawablesWithIntrinsicBounds(removeIcon, null, null, null);

                    intent.putExtra("ID", ID);
                    intent.putExtra("Keyword1", keyWord1.getText().toString());
                    intent.putExtra("Keyword2", keyWord2.getText().toString());
                    intent.putExtra("Keyword3", keyWord3.getText().toString());
                    intent.putExtra("Response1", response1.getText().toString());
                    intent.putExtra("Response2", response2.getText().toString());
                    intent.putExtra("Response3", response3.getText().toString());
                    intent.putExtra("Command", command.getText().toString());

                    if(connection.getMsgStatus()){
                        Toast.makeText(getContext(), "Salvo", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Erro ao salvar interação", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnDeleteCancel.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(btnDeleteCancel.getText().equals("Excluir")){
                    removeDialog = removeAlert.create();
                    removeDialog.show();

                }else{
                    keyWord1.setEnabled(false);
                    keyWord2.setEnabled(false);
                    keyWord3.setEnabled(false);
                    response1.setEnabled(false);
                    response2.setEnabled(false);
                    response3.setEnabled(false);
                    command.setEnabled(false);

                    btnDeleteCancel.setText("Excluir");
                    btnEditSend.setText("Editar");

                    editIcon = getResources().getDrawable(R.drawable.ic_edit_black_24dp);
                    removeIcon = getResources().getDrawable(R.drawable.ic_delete_black_24dp);

                    btnEditSend.setCompoundDrawablesWithIntrinsicBounds(editIcon, null, null, null);
                    btnDeleteCancel.setCompoundDrawablesWithIntrinsicBounds(removeIcon, null, null, null);

                    keyWord1.setText(intent.getStringExtra("Keyword1"));
                    keyWord2.setText(intent.getStringExtra("Keyword2"));
                    keyWord3.setText(intent.getStringExtra("Keyword3"));
                    response1.setText(intent.getStringExtra("Response1"));
                    response2.setText(intent.getStringExtra("Response2"));
                    response3.setText(intent.getStringExtra("Response3"));
                    command.setText(intent.getStringExtra("Command"));

                }
            }
        });


        return view;
    }
}

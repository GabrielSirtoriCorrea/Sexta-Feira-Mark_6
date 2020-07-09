package com.gazeboindustries.sextafeiramobile.Fragments.InteractionsFragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gazeboindustries.sextafeiramobile.R;
import com.gazeboindustries.sextafeiramobile.ServerConnection;
import com.google.android.material.drawable.DrawableUtils;

public class ViewInteractionFragment extends Fragment {
    private Intent intent;
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

        removeAlert = new AlertDialog.Builder(view.getContext());
        removeAlert.setMessage("Deseja remover a interação?");
        removeAlert.setCancelable(false);

        removeAlert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "Cancelado", Toast.LENGTH_SHORT).show();
            }
        });

        removeAlert.setPositiveButton("Remover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "Removido", Toast.LENGTH_SHORT).show();

            }
        });

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

        keyWord1.setText(intent.getStringExtra("Keyword1"));
        keyWord2.setText(intent.getStringExtra("Keyword2"));
        keyWord3.setText(intent.getStringExtra("Keyword3"));
        response1.setText(intent.getStringExtra("Response1"));
        response2.setText(intent.getStringExtra("Response2"));
        response3.setText(intent.getStringExtra("Response3"));
        command.setText(intent.getStringExtra("Command"));

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
                    //connection = new ServerConnection();
                    Toast.makeText(getContext(), "Salvo", Toast.LENGTH_SHORT).show();
                    //connection.sendRequest(connection.prepareRequest());
                }
            }
        });

        btnDeleteCancel.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(btnDeleteCancel.getText().equals("Remover")){
                    removeDialog = removeAlert.create();
                    removeDialog.show();

                    //connection = new ServerConnection();


                }else{
                    keyWord1.setEnabled(false);
                    keyWord2.setEnabled(false);
                    keyWord3.setEnabled(false);
                    response1.setEnabled(false);
                    response2.setEnabled(false);
                    response3.setEnabled(false);
                    command.setEnabled(false);

                    btnDeleteCancel.setText("Remover");
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

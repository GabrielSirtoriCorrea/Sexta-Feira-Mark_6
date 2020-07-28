package com.gazeboindustries.friday.Fragments.SkillsFragments.HomeworkFragments;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewHomeWorkFragment extends Fragment {
    private Intent intent;
    private EditText txtType;
    private EditText txtSubject;
    private EditText txtHomeWork;
    private EditText txtDelivery;
    private EditText txtDescription;

    private Button btnEditSend;
    private Button btnDeleteCancel;

    private Drawable saveIcon;
    private Drawable cancelIcon;

    private Drawable editIcon;
    private Drawable removeIcon;

    private SimpleDateFormat sdf;
    private SimpleDateFormat dateOutput;
    private Date dateFormated;

    private ServerConnection connection;
    private int ID;

    private AlertDialog.Builder removeAlert;
    private AlertDialog removeDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewhomework, container, false);

        connection = new ServerConnection();

        sdf = new SimpleDateFormat("dd/MM/yyyy");
        dateOutput = new SimpleDateFormat("yyyy-MM-dd");


        txtType = view.findViewById(R.id.txtViewHomeWorkType);
        txtSubject = view.findViewById(R.id.txtViewHomeWorkSubject);
        txtHomeWork = view.findViewById(R.id.txtViewHomeWork);
        txtDelivery = view.findViewById(R.id.txtViewHomeWorkDelivery);
        txtDescription = view.findViewById(R.id.txtViewHomeWorkDescription);

        intent = getActivity().getIntent();

        btnEditSend = view.findViewById(R.id.btnEditHomeWork);
        btnDeleteCancel = view.findViewById(R.id.btnRemoveHomeWork);

        ID = intent.getIntExtra("ID", 0);
        txtType.setText(intent.getStringExtra("Type"));
        txtSubject.setText(intent.getStringExtra("Subject"));
        txtHomeWork.setText(intent.getStringExtra("HomeWork"));
        txtDelivery.setText(intent.getStringExtra("Delivery"));
        txtDescription.setText(intent.getStringExtra("Description"));

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
                connection.sendRequest(connection.prepareDelete("deleteHomeWork", ID));

                if(connection.getMsgStatus()) {
                    Toast.makeText(getContext(), "Excluído", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Erro ao remover tarefa", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEditSend.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(btnEditSend.getText().equals("Editar")){
                    txtType.setEnabled(true);
                    txtSubject.setEnabled(true);
                    txtHomeWork.setEnabled(true);
                    txtDelivery.setEnabled(true);
                    txtDescription.setEnabled(true);

                    btnDeleteCancel.setText("Cancelar");
                    btnEditSend.setText("Salvar");

                    cancelIcon = getResources().getDrawable(R.drawable.ic_clear_black_24dp);
                    saveIcon = getResources().getDrawable(R.drawable.ic_save_black_24dp);

                    btnEditSend.setCompoundDrawablesWithIntrinsicBounds(saveIcon, null, null, null);
                    btnDeleteCancel.setCompoundDrawablesWithIntrinsicBounds(cancelIcon, null, null, null);

                }else{
                    try {
                        dateFormated = sdf.parse(txtDelivery.getText().toString());

                        connection.sendRequest(connection.prepareUpdateHomework("updateHomeWork", ID, txtType.getText().toString(), txtSubject.getText().toString(),
                                txtHomeWork.getText().toString(), dateOutput.format(dateFormated), txtDescription.getText().toString()));


                        txtType.setEnabled(false);
                        txtSubject.setEnabled(false);
                        txtHomeWork.setEnabled(false);
                        txtDelivery.setEnabled(false);
                        txtDescription.setEnabled(false);

                        btnDeleteCancel.setText("Excluir");
                        btnEditSend.setText("Editar");

                        editIcon = getResources().getDrawable(R.drawable.ic_edit_black_24dp);
                        removeIcon = getResources().getDrawable(R.drawable.ic_delete_black_24dp);

                        btnEditSend.setCompoundDrawablesWithIntrinsicBounds(editIcon, null, null, null);
                        btnDeleteCancel.setCompoundDrawablesWithIntrinsicBounds(removeIcon, null, null, null);
                        intent.putExtra("ID", ID);
                        intent.putExtra("Type", txtType.getText().toString());
                        intent.putExtra("Subject", txtSubject.getText().toString());
                        intent.putExtra("HomeWork", txtHomeWork.getText().toString());
                        intent.putExtra("Delivery", txtDelivery.getText().toString());
                        intent.putExtra("Description", txtDescription.getText().toString());

                    } catch (ParseException e ) {
                        Toast.makeText(view.getContext(), "Insira um formato válido", Toast.LENGTH_SHORT).show();
                    }


                    if(connection.getMsgStatus()){
                        Toast.makeText(getContext(), "Salvo", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Erro ao salvar a tarefa", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnDeleteCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnDeleteCancel.getText().equals("Excluir")){
                    removeDialog = removeAlert.create();
                    removeDialog.show();

                }else{

                    txtType.setText(intent.getStringExtra("Type"));
                    txtSubject.setText(intent.getStringExtra("Subject"));
                    txtHomeWork.setText(intent.getStringExtra("HomeWork"));
                    txtDelivery.setText(intent.getStringExtra("Delivery"));
                    txtDescription.setText(intent.getStringExtra("Description"));

                    txtType.setEnabled(false);
                    txtSubject.setEnabled(false);
                    txtHomeWork.setEnabled(false);
                    txtDelivery.setEnabled(false);
                    txtDescription.setEnabled(false);

                    btnEditSend.setCompoundDrawablesWithIntrinsicBounds(editIcon, null, null, null);
                    btnDeleteCancel.setCompoundDrawablesWithIntrinsicBounds(removeIcon, null, null, null);

                    btnDeleteCancel.setText("Excluir");
                    btnEditSend.setText("Editar");
                }
            }
        });

        return view;
    }
}

package com.gazeboindustries.friday.Fragments.SkillsFragments.ProjectsFragments;

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

public class ViewProjectsFragment extends Fragment {
    private Intent intent;
    private EditText txtProject;
    private EditText txtType;

    private Button btnEditSend;
    private Button btnDeleteCancel;

    private Drawable saveIcon;
    private Drawable cancelIcon;

    private Drawable editIcon;
    private Drawable removeIcon;

    private ServerConnection connection;
    private int ID;

    private AlertDialog.Builder removeAlert;
    private AlertDialog removeDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewproject , container, false);

        connection = new ServerConnection();

        txtProject = view.findViewById(R.id.txtViewProject);
        txtType = view.findViewById(R.id.txtViewProjectType);

        btnEditSend = view.findViewById(R.id.btnEditProject);
        btnDeleteCancel = view.findViewById(R.id.btnRemoveProject);

        intent = getActivity().getIntent();

        ID = intent.getIntExtra("ID", 0);
        txtProject.setText(intent.getStringExtra("Project"));
        txtType.setText(intent.getStringExtra("type"));

        removeAlert = new AlertDialog.Builder(view.getContext());
        removeAlert.setMessage("Deseja remover o projeto?");
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
                connection.sendRequest(connection.prepareDelete("deleteProject", ID));

                if(connection.getMsgStatus()) {
                    Toast.makeText(getContext(), "Excluído", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Erro ao remover projeto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEditSend.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(btnEditSend.getText().equals("Editar")){
                    txtProject.setEnabled(true);
                    txtType.setEnabled(true);


                    btnDeleteCancel.setText("Cancelar");
                    btnEditSend.setText("Salvar");

                    cancelIcon = getResources().getDrawable(R.drawable.ic_clear_black_24dp);
                    saveIcon = getResources().getDrawable(R.drawable.ic_save_black_24dp);

                    btnEditSend.setCompoundDrawablesWithIntrinsicBounds(saveIcon, null, null, null);
                    btnDeleteCancel.setCompoundDrawablesWithIntrinsicBounds(cancelIcon, null, null, null);

                }else{
                    connection.sendRequest(connection.prepareUpdateProject("updateProject", ID, txtType.getText().toString(), txtProject.getText().toString()));

                    txtProject.setEnabled(false);
                    txtType.setEnabled(false);

                    btnDeleteCancel.setText("Excluir");
                    btnEditSend.setText("Editar");

                    editIcon = getResources().getDrawable(R.drawable.ic_edit_black_24dp);
                    removeIcon = getResources().getDrawable(R.drawable.ic_delete_black_24dp);

                    btnEditSend.setCompoundDrawablesWithIntrinsicBounds(editIcon, null, null, null);
                    btnDeleteCancel.setCompoundDrawablesWithIntrinsicBounds(removeIcon, null, null, null);

                    intent.putExtra("ID", ID);
                    intent.putExtra("Project", txtProject.getText().toString());
                    intent.putExtra("type", txtType.getText().toString());

                    if(connection.getMsgStatus()){
                        Toast.makeText(getContext(), "Salvo", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Erro ao salvar o projeto", Toast.LENGTH_SHORT).show();
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
                    txtProject.setEnabled(false);
                    txtType.setEnabled(false);

                    btnDeleteCancel.setText("Excluir");
                    btnEditSend.setText("Editar");

                    editIcon = getResources().getDrawable(R.drawable.ic_edit_black_24dp);
                    removeIcon = getResources().getDrawable(R.drawable.ic_delete_black_24dp);

                    btnEditSend.setCompoundDrawablesWithIntrinsicBounds(editIcon, null, null, null);
                    btnDeleteCancel.setCompoundDrawablesWithIntrinsicBounds(removeIcon, null, null, null);

                    txtProject.setText(intent.getStringExtra("Project"));
                    txtType.setText(intent.getStringExtra("type"));


                }
            }
        });

        return view;
    }
}

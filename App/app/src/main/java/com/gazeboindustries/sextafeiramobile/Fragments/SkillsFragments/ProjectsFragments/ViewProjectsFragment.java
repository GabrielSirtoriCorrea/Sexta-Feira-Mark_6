package com.gazeboindustries.sextafeiramobile.Fragments.SkillsFragments.ProjectsFragments;

import android.annotation.SuppressLint;
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

import com.gazeboindustries.sextafeiramobile.R;
import com.gazeboindustries.sextafeiramobile.ServerConnection;

public class ViewProjectsFragment extends Fragment {
    private Intent intent;
    private EditText txtProject;
    private EditText txtRepository;

    private Button btnEditSend;
    private Button btnDeleteCancel;

    private Drawable saveIcon;
    private Drawable cancelIcon;

    private Drawable editIcon;
    private Drawable removeIcon;

    private ServerConnection connection;
    private int ID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewproject , container, false);

        txtProject = view.findViewById(R.id.txtViewProject);
        txtRepository = view.findViewById(R.id.txtViewRepository);

        btnEditSend = view.findViewById(R.id.btnEditProject);
        btnDeleteCancel = view.findViewById(R.id.btnRemoveProject);

        intent = getActivity().getIntent();

        ID = intent.getIntExtra("ID", 0);
        txtProject.setText(intent.getStringExtra("Project"));
        txtRepository.setText(intent.getStringExtra("Repository"));

        btnEditSend.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(btnEditSend.getText().equals("Editar")){
                    txtProject.setEnabled(true);
                    txtRepository.setEnabled(true);


                    btnDeleteCancel.setText("Cancelar");
                    btnEditSend.setText("Salvar");

                    cancelIcon = getResources().getDrawable(R.drawable.ic_clear_black_24dp);
                    saveIcon = getResources().getDrawable(R.drawable.ic_save_black_24dp);

                    btnEditSend.setCompoundDrawablesWithIntrinsicBounds(saveIcon, null, null, null);
                    btnDeleteCancel.setCompoundDrawablesWithIntrinsicBounds(cancelIcon, null, null, null);

                }else{
                    connection = new ServerConnection();

                    connection.sendRequest(connection.prepareUpdateProject("updateProject", ID, txtProject.getText().toString(), txtRepository.getText().toString()));

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
                if(btnDeleteCancel.getText().equals("Remover")){
                    //show dialog
                    System.out.println("Remove");

                }else{
                    txtProject.setEnabled(false);
                    txtRepository.setEnabled(false);

                    btnDeleteCancel.setText("Remover");
                    btnEditSend.setText("Editar");

                    editIcon = getResources().getDrawable(R.drawable.ic_edit_black_24dp);
                    removeIcon = getResources().getDrawable(R.drawable.ic_delete_black_24dp);

                    btnEditSend.setCompoundDrawablesWithIntrinsicBounds(editIcon, null, null, null);
                    btnDeleteCancel.setCompoundDrawablesWithIntrinsicBounds(removeIcon, null, null, null);

                    txtProject.setText(intent.getStringExtra("Project"));
                    txtRepository.setText(intent.getStringExtra("Repository"));

                }
            }
        });

        return view;
    }
}

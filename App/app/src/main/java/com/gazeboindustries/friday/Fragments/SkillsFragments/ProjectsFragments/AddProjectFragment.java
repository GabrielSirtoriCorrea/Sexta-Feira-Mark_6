package com.gazeboindustries.friday.Fragments.SkillsFragments.ProjectsFragments;

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

public class AddProjectFragment extends Fragment {
    private Button btnAddProject;
    private EditText type;
    private EditText project;

    private ServerConnection connection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addproject, container, false);

        btnAddProject = view.findViewById(R.id.btnAddProject);
        project = view.findViewById(R.id.txtAddNameProject);
        type = view.findViewById(R.id.txtAddTypeProject);

        btnAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connection = new ServerConnection();

                connection.sendRequest(connection.prepareAddProject("insertProject", type.getText().toString(), project.getText().toString()));

                if(connection.getMsgStatus()){
                    Toast.makeText(view.getContext(), "Projeto adicionado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(view.getContext(), "Erro ao adicionar projeto", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }
}

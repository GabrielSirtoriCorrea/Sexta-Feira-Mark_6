package com.gazeboindustries.sextafeiramobile.Fragments.SkillsFragments.ProjectsFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gazeboindustries.sextafeiramobile.ListItemRow;
import com.gazeboindustries.sextafeiramobile.R;
import com.gazeboindustries.sextafeiramobile.ServerConnection;

import org.json.JSONArray;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class ProjectFragment extends Fragment {
    private ArrayAdapter arrayAdapter;
    private Button btnAddNewInteraction;
    private ListView listProjects;
    private ArrayList<JSONArray> arrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_projects, container, false);

        Button btnAddProject = view.findViewById(R.id.btnAddNewProject);

        btnAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frame, new AddProjectFragment()).commit();
            }
        });

        listProjects = view.findViewById(R.id.listProjects);

        ServerConnection connection = new ServerConnection("getProjects");

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        arrayList =  connection.getListResponse();

        arrayAdapter = new ListItemRow(view.getContext(), arrayList, "Projects");

        listProjects.setAdapter(arrayAdapter);

        return view;
    }
}

package com.gazeboindustries.sextafeiramobile.Fragments.SkillsFragments.ProjectsFragments;

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

public class ViewProjectsFragment extends Fragment {
    Intent intent;
    EditText txtProject;
    EditText txtRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewproject , container, false);

        txtProject = view.findViewById(R.id.txtViewProject);
        txtRepository = view.findViewById(R.id.txtViewRepository);

        intent = getActivity().getIntent();

        txtProject.setText(intent.getSerializableExtra("Project").toString());
        txtRepository.setText(intent.getSerializableExtra("Repository").toString());
        return view;
    }
}

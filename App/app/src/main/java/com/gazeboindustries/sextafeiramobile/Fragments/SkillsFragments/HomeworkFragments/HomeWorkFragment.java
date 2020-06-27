package com.gazeboindustries.sextafeiramobile.Fragments.SkillsFragments.HomeworkFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gazeboindustries.sextafeiramobile.Fragments.SkillsFragments.ProjectsFragments.AddProjectFragment;
import com.gazeboindustries.sextafeiramobile.Fragments.SkillsFragments.ProjectsFragments.ProjectFragment;
import com.gazeboindustries.sextafeiramobile.R;

public class HomeWorkFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homeworks, container, false);

        Button btnAddHomeWork = view.findViewById(R.id.btnAddNewHomework);

        btnAddHomeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frame, new AddHomeWorkFragment()).commit();
            }
        });


        return view;
    }
}

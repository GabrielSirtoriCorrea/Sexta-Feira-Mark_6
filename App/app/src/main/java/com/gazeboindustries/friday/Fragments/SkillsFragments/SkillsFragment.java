package com.gazeboindustries.friday.Fragments.SkillsFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gazeboindustries.friday.Fragments.SkillsFragments.HomeworkFragments.HomeWorkFragment;
import com.gazeboindustries.friday.Fragments.SkillsFragments.ProjectsFragments.ProjectFragment;
import com.gazeboindustries.friday.R;

public class SkillsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skills, container, false);

        Button btnHomeWorkManage = view.findViewById(R.id.btnHomeWorkManage);

        btnHomeWorkManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frame, new HomeWorkFragment()).commit();
            }
        });

        Button btnProjectManage = view.findViewById(R.id.btnProjectManage);

        btnProjectManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frame, new ProjectFragment()).commit();
            }
        });

        return view;
    }
}

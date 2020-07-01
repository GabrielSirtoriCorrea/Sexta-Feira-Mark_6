package com.gazeboindustries.sextafeiramobile.Fragments.SkillsFragments.HomeworkFragments;

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

import com.gazeboindustries.sextafeiramobile.Fragments.SkillsFragments.ProjectsFragments.AddProjectFragment;
import com.gazeboindustries.sextafeiramobile.Fragments.SkillsFragments.ProjectsFragments.ProjectFragment;
import com.gazeboindustries.sextafeiramobile.ListItemRow;
import com.gazeboindustries.sextafeiramobile.R;
import com.gazeboindustries.sextafeiramobile.ServerConnection;

import org.json.JSONArray;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class HomeWorkFragment extends Fragment {
    private ArrayAdapter arrayAdapter;
    private Button btnAddNewInteraction;
    private ListView listHomeWorks;
    private ArrayList<JSONArray> arrayList;

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

        listHomeWorks = view.findViewById(R.id.listHomeWorks);

        ServerConnection connection = new ServerConnection("getHomeWorks");

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        arrayList =  connection.getListResponse();

        arrayAdapter = new ListItemRow(view.getContext(), arrayList, "HomeWorks");

        listHomeWorks.setAdapter(arrayAdapter);

        return view;
    }
}

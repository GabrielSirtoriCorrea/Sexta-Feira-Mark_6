package com.gazeboindustries.friday.Fragments.SkillsFragments.HomeworkFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gazeboindustries.friday.ListItemRow;
import com.gazeboindustries.friday.R;
import com.gazeboindustries.friday.ServerConnection;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class HomeWorkFragment extends Fragment {
    private ArrayAdapter arrayAdapter;
    private Button btnAddNewInteraction;
    private ListView listHomeWorks;
    private ArrayList<JSONArray> arrayList;
    private Intent intent;

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

        ServerConnection connection = new ServerConnection();

        arrayList =  connection.sendRequest(connection.prepareRequest("getHomeWorks"));

        arrayAdapter = new ListItemRow(view.getContext(), arrayList, "HomeWork");

        listHomeWorks.setAdapter(arrayAdapter);

        listHomeWorks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    intent = getActivity().getIntent();
                    intent.putExtra("ID", arrayList.get(i).getInt(0));
                    intent.putExtra("Type", arrayList.get(i).get(1).toString());
                    intent.putExtra("Subject", arrayList.get(i).get(2).toString());
                    intent.putExtra("HomeWork", arrayList.get(i).get(3).toString());
                    intent.putExtra("Delivery", arrayList.get(i).get(4).toString());
                    intent.putExtra("Description", arrayList.get(i).get(5).toString());

                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction().replace(R.id.frame, new ViewHomeWorkFragment()).commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

        return view;
    }
}

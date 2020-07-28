package com.gazeboindustries.friday.Fragments.InteractionsFragments;

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

public class InteractionFragment extends Fragment {
    private ArrayAdapter arrayAdapter;
    private Button btnAddNewInteraction;
    private ListView listInteractions;
    private ArrayList<JSONArray> arrayList;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interactions, container, false);

        btnAddNewInteraction = view.findViewById(R.id.btnAddNewInteraction);

        btnAddNewInteraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frame, new AddInteractionFragment()).commit();
            }
        });

        listInteractions = view.findViewById(R.id.listInteractions);

        ServerConnection connection = new ServerConnection();

        arrayList =  connection.sendRequest(connection.prepareRequest("getInteractions"));

        arrayAdapter = new ListItemRow(view.getContext(), arrayList, "Interactions");

        listInteractions.setAdapter(arrayAdapter);

        listInteractions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    System.out.println(arrayList.toString());

                    intent = getActivity().getIntent();
                    intent.putExtra("ID", arrayList.get(i).getInt(0));
                    intent.putExtra("Keyword1", arrayList.get(i).getString(1));
                    intent.putExtra("Keyword2", arrayList.get(i).getString(2));
                    intent.putExtra("Keyword3", arrayList.get(i).getString(3));
                    intent.putExtra("Response1", arrayList.get(i).getString(4));
                    intent.putExtra("Response2", arrayList.get(i).getString(5));
                    intent.putExtra("Response3", arrayList.get(i).getString(6));
                    intent.putExtra("Command", arrayList.get(i).getString(7));


                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction().replace(R.id.frame, new ViewInteractionFragment()).commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


        return view;
    }



}

package com.gazeboindustries.friday.Fragments.SkillsFragments.HomeworkFragments;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddHomeWorkFragment extends Fragment {
    private Button btnAddHomeWork;
    private EditText type;
    private EditText subject;
    private EditText homeWork;
    private EditText delivery;
    private EditText desc;

    private ServerConnection connection;
    private SimpleDateFormat sdf;
    private SimpleDateFormat dateOutput;
    private Date dateFormated;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        dateOutput = new SimpleDateFormat("yyyy-MM-dd");

        View view = inflater.inflate(R.layout.fragment_addhomework, container, false);

        btnAddHomeWork = view.findViewById(R.id.btnAddHomeWork);

        type = view.findViewById(R.id.txtHomeWorkType);
        subject = view.findViewById(R.id.txtHomeWorkSubject);
        homeWork = view.findViewById(R.id.txtHomeWork);
        delivery = view.findViewById(R.id.txtHomeWorkDelivery);
        desc = view.findViewById(R.id.txtHomeWorkDescription);

        btnAddHomeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connection = new ServerConnection();

                try {
                    dateFormated = sdf.parse(delivery.getText().toString());

                        connection.sendRequest(connection.prepareAddHomework("insertHomeWork", type.getText().toString(), subject.getText().toString(),
                                homeWork.getText().toString(), dateOutput.format(dateFormated), desc.getText().toString()));

                    if(connection.getMsgStatus()){
                        Toast.makeText(view.getContext(), "Tarefa enviada", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(view.getContext(), "Erro ao enviar a tarefa", Toast.LENGTH_SHORT).show();
                    }

                } catch (ParseException e) {
                    Toast.makeText(view.getContext(), "Insira um formato válido", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
}
}

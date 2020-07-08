package com.gazeboindustries.sextafeiramobile.Fragments.SkillsFragments.HomeworkFragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gazeboindustries.sextafeiramobile.R;

public class ViewHomeWorkFragment extends Fragment {
    private Intent intent;
    private EditText txtType;
    private EditText txtSubject;
    private EditText txtHomeWork;
    private EditText txtDelivery;
    private EditText txtDescription;

    private Button btnEditSend;
    private Button btnDeleteCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewhomework, container, false);

        txtType = view.findViewById(R.id.txtViewHomeWorkType);
        txtSubject = view.findViewById(R.id.txtViewHomeWorkSubject);
        txtHomeWork = view.findViewById(R.id.txtViewHomeWork);
        txtDelivery = view.findViewById(R.id.txtViewHomeWorkDelivery);
        txtDescription = view.findViewById(R.id.txtViewHomeWorkDescription);

        intent = getActivity().getIntent();

        btnEditSend = view.findViewById(R.id.btnEditHomeWork);
        btnDeleteCancel = view.findViewById(R.id.btnRemoveHomeWork);


        txtType.setText(intent.getStringExtra("Type"));
        txtSubject.setText(intent.getStringExtra("Subject"));
        txtHomeWork.setText(intent.getStringExtra("HomeWork"));
        txtDelivery.setText(intent.getStringExtra("Delivery"));
        txtDescription.setText(intent.getStringExtra("Description"));

        btnEditSend.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(btnEditSend.getText().equals("Editar")){
                    txtType.setEnabled(true);
                    txtSubject.setEnabled(true);
                    txtHomeWork.setEnabled(true);
                    txtDelivery.setEnabled(true);
                    txtDescription.setEnabled(true);

                    btnDeleteCancel.setText("Cancelar");
                    btnEditSend.setText("Salvar");
                }else{
                    //connection = new ServerConnection();
                    System.out.println("ENVIAR");
                    //connection.sendRequest(connection.prepareRequest());
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
                    txtType.setEnabled(false);
                    txtSubject.setEnabled(false);
                    txtHomeWork.setEnabled(false);
                    txtDelivery.setEnabled(false);
                    txtDescription.setEnabled(false);

                    btnDeleteCancel.setText("Remover");
                    btnEditSend.setText("Editar");

                    txtType.setText(intent.getStringExtra("Type"));
                    txtSubject.setText(intent.getStringExtra("Subject"));
                    txtHomeWork.setText(intent.getStringExtra("HomeWork"));
                    txtDelivery.setText(intent.getStringExtra("Delivery"));
                    txtDescription.setText(intent.getStringExtra("Description"));

                }
            }
        });

        return view;
    }
}

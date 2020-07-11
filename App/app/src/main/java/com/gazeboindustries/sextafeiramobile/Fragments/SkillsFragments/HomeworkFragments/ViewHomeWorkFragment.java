package com.gazeboindustries.sextafeiramobile.Fragments.SkillsFragments.HomeworkFragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

import com.gazeboindustries.sextafeiramobile.R;
import com.gazeboindustries.sextafeiramobile.ServerConnection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewHomeWorkFragment extends Fragment {
    private Intent intent;
    private EditText txtType;
    private EditText txtSubject;
    private EditText txtHomeWork;
    private EditText txtDelivery;
    private EditText txtDescription;

    private Button btnEditSend;
    private Button btnDeleteCancel;

    private Drawable saveIcon;
    private Drawable cancelIcon;

    private Drawable editIcon;
    private Drawable removeIcon;

    private SimpleDateFormat sdf;

    private ServerConnection connection;
    private int ID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewhomework, container, false);

        sdf = new SimpleDateFormat("dd/MM/yyyy");


        txtType = view.findViewById(R.id.txtViewHomeWorkType);
        txtSubject = view.findViewById(R.id.txtViewHomeWorkSubject);
        txtHomeWork = view.findViewById(R.id.txtViewHomeWork);
        txtDelivery = view.findViewById(R.id.txtViewHomeWorkDelivery);
        txtDescription = view.findViewById(R.id.txtViewHomeWorkDescription);

        intent = getActivity().getIntent();

        btnEditSend = view.findViewById(R.id.btnEditHomeWork);
        btnDeleteCancel = view.findViewById(R.id.btnRemoveHomeWork);

        ID = intent.getIntExtra("ID", 0);
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

                    cancelIcon = getResources().getDrawable(R.drawable.ic_clear_black_24dp);
                    saveIcon = getResources().getDrawable(R.drawable.ic_save_black_24dp);

                    btnEditSend.setCompoundDrawablesWithIntrinsicBounds(saveIcon, null, null, null);
                    btnDeleteCancel.setCompoundDrawablesWithIntrinsicBounds(cancelIcon, null, null, null);

                }else{
                    try {
                        sdf.parse(txtDelivery.getText().toString());

                    } catch (ParseException e) {
                        Toast.makeText(view.getContext(), "Insira um formato válido", Toast.LENGTH_SHORT).show();
                    }
                    connection = new ServerConnection();

                    connection.sendRequest(connection.prepareUpdateHomework("updateHomeWork", ID, txtType.getText().toString(), txtSubject.getText().toString(),
                            txtHomeWork.getText().toString(), txtDelivery.getText().toString(), txtDescription.getText().toString()));

                    if(connection.getMsgStatus()){
                        Toast.makeText(getContext(), "Salvo", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Erro ao salvar a tarefa", Toast.LENGTH_SHORT).show();
                    }
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

                    editIcon = getResources().getDrawable(R.drawable.ic_edit_black_24dp);
                    removeIcon = getResources().getDrawable(R.drawable.ic_delete_black_24dp);

                    btnEditSend.setCompoundDrawablesWithIntrinsicBounds(editIcon, null, null, null);
                    btnDeleteCancel.setCompoundDrawablesWithIntrinsicBounds(removeIcon, null, null, null);

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

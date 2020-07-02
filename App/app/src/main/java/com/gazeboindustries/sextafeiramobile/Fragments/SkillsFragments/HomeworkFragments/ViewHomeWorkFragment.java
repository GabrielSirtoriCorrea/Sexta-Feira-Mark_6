package com.gazeboindustries.sextafeiramobile.Fragments.SkillsFragments.HomeworkFragments;

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

public class ViewHomeWorkFragment extends Fragment {
    Intent intent;
    EditText txtType;
    EditText txtSubject;
    EditText txtHomeWork;
    EditText txtDelivery;
    EditText txtDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewinteractions, container, false);

        txtType = view.findViewById(R.id.txtViewHomeWorkType);
        txtSubject = view.findViewById(R.id.txtViewHomeWorkSubject);
        txtHomeWork = view.findViewById(R.id.txtViewHomeWork);
        txtDelivery = view.findViewById(R.id.txtViewHomeWorkDelivery);
        txtDescription = view.findViewById(R.id.txtViewHomeWorkDescription);

        intent = getActivity().getIntent();

        txtType.setText(intent.getSerializableExtra("Type").toString());
        txtSubject.setText(intent.getSerializableExtra("Subject").toString());
        txtHomeWork.setText(intent.getSerializableExtra("HomeWork").toString());
        txtDelivery.setText(intent.getSerializableExtra("Delivery").toString());
        txtDescription.setText(intent.getSerializableExtra("Description").toString());

        return view;
    }
}

package com.gazeboindustries.sextafeiramobile.Fragments.SkillsFragments.HomeworkFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gazeboindustries.sextafeiramobile.R;

public class AddHomeWorkFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*checa se é uma data:
        try {
              sdf.parse(txtDelivery.getText().toString());

             } catch (ParseException e) {
                Toast.makeText(view.getContext(), "Insira um formato válido", Toast.LENGTH_SHORT).show();
         }*/

        return inflater.inflate(R.layout.fragment_addhomework, container, false);

}
}

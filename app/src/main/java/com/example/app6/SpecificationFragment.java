package com.example.app6;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SpecificationFragment extends Fragment {
    View view;
    Button buttonSpecification;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_specification, container, false);

        buttonSpecification = (Button)view.findViewById(R.id.buttonSpecification);

        buttonSpecification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Закрытие первого фрагмента
                getActivity().getSupportFragmentManager().beginTransaction().remove(SpecificationFragment.this).commit();

                //Открытие второго фрагмента
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container_view, SetBetFragment.class, null)
                        .commit();
            }
        });

        return view;
    }
}
package com.example.app6;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ResultFragment extends Fragment {
    View view;
    Button buttonGame;
    GameActivity ga;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_result, container, false);

        if(getActivity()!=null){
            ga = (GameActivity)getActivity();
        }

        buttonGame = (Button) view.findViewById(R.id.buttonGame);
        buttonGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Закрытие первого фрагмента
                getActivity().getSupportFragmentManager().beginTransaction().remove(ResultFragment.this).commit();
                ga.restore();
                ga.moneyWin = ga.bet;
                String moneyWin = String.valueOf(ga.moneyWin);
                ga.textViewMoneyWin.setText(moneyWin);
                ga.newGame = true;

                //Поставить клики с кнопок ЗАБРАТЬ и ПРОДОЛЖИТЬ
                ga.buttonTake.setClickable(true);
                ga.buttonContinue.setClickable(true);
                ga.start();
            }
        });

        return view;
    }
}
package com.example.app6;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class SetBetFragment extends Fragment {
    View view;
    Button buttonSetBet;
    ImageButton imageButtonDownBet, imageButtonUpBet;
    TextView textViewSetBet;
    GameActivity ga;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_set_bet, container, false);
        buttonSetBet = (Button)view.findViewById(R.id.buttonSetBet);
        imageButtonDownBet = (ImageButton)view.findViewById(R.id.imageViewDownBet);
        imageButtonUpBet = (ImageButton)view.findViewById(R.id.imageViewUpBet);
        textViewSetBet = (TextView) view.findViewById(R.id.textViewSetBet);

        if(getActivity()!=null){
            ga = (GameActivity)getActivity();
        }

        buttonSetBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(SetBetFragment.this).commit();
                ga.moneyWin = ga.bet;
                String stringMoneyWin = String.valueOf(ga.moneyWin);
                ga.textViewMoneyWin.setText(stringMoneyWin);
                ga.start();
            }
        });

        imageButtonDownBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int minBet = 100;
                if(ga.bet >= (minBet+50)){
                    ga.bet = ga.bet - 50;
                    String stringBet = String.valueOf(ga.bet);
                    textViewSetBet.setText(stringBet);
                    ga.textViewBet.setText(stringBet);
                }
            }
        });

        imageButtonUpBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maxBet = 2000;
                if(ga.bet <= (maxBet-50)){
                    ga.bet = ga.bet + 50;
                    String stringBet = String.valueOf(ga.bet);
                    textViewSetBet.setText(stringBet);
                    ga.textViewBet.setText(stringBet);
                }
            }
        });

        return view;
    }
}
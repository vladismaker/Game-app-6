package com.example.app6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class GameActivity extends AppCompatActivity {
    TextView textViewMoney, textViewBet, textView0_1, textView0_2, textView1_1, textView1_2, textView2_1, textView2_2, textView3_1, textView3_2, textView4_1, textView4_2, textViewMoneyWin;
    LottieAnimationView lottie0, lottie1, lottie2, lottie3, lottie4, lottieWinSymbol;
    int bet = 100, money = 20000, choiceCard1, choiceCard2, choiceCard3, choiceCard4, choiceApp, moneyWin = 0, countEntry = 0;
    int[] arrayNumber = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    FrameLayout frameLayoutCard1, frameLayoutCard2, frameLayoutCard3, frameLayoutCard4;
    Button buttonTake, buttonContinue, buttonExit;
    boolean newGame = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init();

        moneyWin = bet;
        String stringBet = String.valueOf(bet);
        textViewBet.setText(stringBet);
        textViewMoneyWin.setText(stringBet);
        String stringMoney = String.valueOf(money);
        textViewMoney.setText(stringMoney);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_view, SpecificationFragment.class, null)
                .commit();

/*        if (countEntry==0){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_view, SpecificationFragment.class, null)
                    .commit();
            countEntry++;
        }else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_view, SetBetFragment.class, null)
                    .commit();
        }*/


    }

    public void init() {
        textViewBet = findViewById(R.id.textBet);
        textViewMoney = findViewById(R.id.textMoney);
        textViewMoneyWin = findViewById(R.id.textMoneyWin);

        textView0_1 = findViewById(R.id.text0_1);
        textView0_2 = findViewById(R.id.text0_2);
        textView1_1 = findViewById(R.id.text1_1);
        textView1_2 = findViewById(R.id.text1_2);
        textView2_1 = findViewById(R.id.text2_1);
        textView2_2 = findViewById(R.id.text2_2);
        textView3_1 = findViewById(R.id.text3_1);
        textView3_2 = findViewById(R.id.text3_2);
        textView4_1 = findViewById(R.id.text4_1);
        textView4_2 = findViewById(R.id.text4_2);

        lottie0 = findViewById(R.id.lottie0);
        lottie1 = findViewById(R.id.lottie1);
        lottie2 = findViewById(R.id.lottie2);
        lottie3 = findViewById(R.id.lottie3);
        lottie4 = findViewById(R.id.lottie4);
        lottieWinSymbol = findViewById(R.id.lottieWinSymbol);

        frameLayoutCard1 = (FrameLayout) findViewById(R.id.frame_layout_card1);
        frameLayoutCard2 = (FrameLayout) findViewById(R.id.frame_layout_card2);
        frameLayoutCard3 = (FrameLayout) findViewById(R.id.frame_layout_card3);
        frameLayoutCard4 = (FrameLayout) findViewById(R.id.frame_layout_card4);

        buttonTake = findViewById(R.id.buttonTake);
        buttonContinue = findViewById(R.id.buttonContinue);
        buttonExit = findViewById(R.id.buttonExit);

    }

    public void start() {
        lottieWinSymbol.setVisibility(View.GONE);
        //Рандомно выбрать число из массива
        choiceApp = arrayNumber[random()];
        choiceCard1 = arrayNumber[random()];
        choiceCard2 = arrayNumber[random()];
        choiceCard3 = arrayNumber[random()];
        choiceCard4 = arrayNumber[random()];
        //Показать главную карточку
        String stringChoiceApp = String.valueOf(choiceApp);
        textView0_1.setText(stringChoiceApp);
        textView0_2.setText(stringChoiceApp);
        //Анимация появление чисел главной карточки

        //Убрать клики с кнопок ЗАБРАТЬ и ПРОДОЛЖИТЬ
        buttonTake.setClickable(false);
        buttonContinue.setClickable(false);

        //Ждем выбора игрока
    }

    public int random() {
        int min = 0;
        int max = 13;

        return (int) (Math.random() * (max + 1 - min) + min);
    }

    public void checkResult(int chCard, int chApp) {
        //Проверяем и показываем выйгрыш
        String stringMoneyWin;
        if (chCard > chApp) {
            //Выйгрыш
            moneyWin = moneyWin * 2;

            lottieWinSymbol.setVisibility(View.VISIBLE);
            //Поставить клики с кнопок ЗАБРАТЬ и ПРОДОЛЖИТЬ
            buttonTake.setClickable(true);
            buttonContinue.setClickable(true);
        }
        if(chCard==chApp){
            //Ничья
            lottieWinSymbol.setVisibility(View.VISIBLE);
            //Поставить клики с кнопок ЗАБРАТЬ и ПРОДОЛЖИТЬ
            buttonTake.setClickable(true);
            buttonContinue.setClickable(true);
        }
        if (chCard < chApp) {
            //Проигрыш
            moneyWin = 0;
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_view, ResultFragment.class, null)
                    .commit();
        }
        stringMoneyWin = String.valueOf(moneyWin);
        textViewMoneyWin.setText(stringMoneyWin);
    }

    public void onClickCard1(View view) {
        //Показать карточку
        String stringChoiceCard1 = String.valueOf(choiceCard1);
        textView1_1.setText(stringChoiceCard1);
        textView1_2.setText(stringChoiceCard1);

        //Увеличить выбранную карточку
        frameLayoutCard1.setLayoutParams(new LinearLayout.LayoutParams(250, 350));
        frameLayoutCard1.setBackgroundResource(R.drawable.card);

        //Показать остальные карточки
        String stringChoiceCard2 = String.valueOf(choiceCard2);
        String stringChoiceCard3 = String.valueOf(choiceCard3);
        String stringChoiceCard4 = String.valueOf(choiceCard4);
        textView2_1.setText(stringChoiceCard2);
        textView2_2.setText(stringChoiceCard2);
        textView3_1.setText(stringChoiceCard3);
        textView3_2.setText(stringChoiceCard3);
        textView4_1.setText(stringChoiceCard4);
        textView4_2.setText(stringChoiceCard4);

        if (newGame) {
            //Вычесть из общего количества монет ставку
            money = money - bet;
            String stringMoney = String.valueOf(money);
            textViewMoney.setText(stringMoney);
        }

        //Проверка результата
        checkResult(choiceCard1, choiceApp);

    }
    public void onClickCard2(View view) {
        //Показать карточку
        String stringChoiceCard2 = String.valueOf(choiceCard2);
        textView2_1.setText(stringChoiceCard2);
        textView2_2.setText(stringChoiceCard2);

        //Увеличить выбранную карточку
        frameLayoutCard2.setLayoutParams(new LinearLayout.LayoutParams(250, 350));
        frameLayoutCard2.setBackgroundResource(R.drawable.card);

        //Показать остальные карточки
        String stringChoiceCard1 = String.valueOf(choiceCard1);
        String stringChoiceCard3 = String.valueOf(choiceCard3);
        String stringChoiceCard4 = String.valueOf(choiceCard4);
        textView1_1.setText(stringChoiceCard1);
        textView1_2.setText(stringChoiceCard1);
        textView3_1.setText(stringChoiceCard3);
        textView3_2.setText(stringChoiceCard3);
        textView4_1.setText(stringChoiceCard4);
        textView4_2.setText(stringChoiceCard4);

        if (newGame) {
            //Вычесть из общего количества монет ставку
            money = money - bet;
            String stringMoney = String.valueOf(money);
            textViewMoney.setText(stringMoney);
        }

        //Проверка результата
        checkResult(choiceCard2, choiceApp);
    }
    public void onClickCard3(View view) {
        //Показать карточку
        String stringChoiceCard3 = String.valueOf(choiceCard3);
        textView3_1.setText(stringChoiceCard3);
        textView3_2.setText(stringChoiceCard3);

        //Увеличить выбранную карточку
        frameLayoutCard3.setLayoutParams(new LinearLayout.LayoutParams(250, 350));
        frameLayoutCard3.setBackgroundResource(R.drawable.card);

        //Показать остальные карточки
        String stringChoiceCard2 = String.valueOf(choiceCard2);
        String stringChoiceCard1 = String.valueOf(choiceCard1);
        String stringChoiceCard4 = String.valueOf(choiceCard4);
        textView2_1.setText(stringChoiceCard2);
        textView2_2.setText(stringChoiceCard2);
        textView1_1.setText(stringChoiceCard1);
        textView1_2.setText(stringChoiceCard1);
        textView4_1.setText(stringChoiceCard4);
        textView4_2.setText(stringChoiceCard4);

        if (newGame) {
            //Вычесть из общего количества монет ставку
            money = money - bet;
            String stringMoney = String.valueOf(money);
            textViewMoney.setText(stringMoney);
        }

        //Проверка результата
        checkResult(choiceCard3, choiceApp);
    }
    public void onClickCard4(View view) {
        //Показать карточку
        String stringChoiceCard4 = String.valueOf(choiceCard4);
        textView4_1.setText(stringChoiceCard4);
        textView4_2.setText(stringChoiceCard4);

        //Увеличить выбранную карточку
        frameLayoutCard4.setLayoutParams(new LinearLayout.LayoutParams(250, 350));
        frameLayoutCard4.setBackgroundResource(R.drawable.card);

        //Показать остальные карточки
        String stringChoiceCard2 = String.valueOf(choiceCard2);
        String stringChoiceCard3 = String.valueOf(choiceCard3);
        String stringChoiceCard1 = String.valueOf(choiceCard1);
        textView2_1.setText(stringChoiceCard2);
        textView2_2.setText(stringChoiceCard2);
        textView3_1.setText(stringChoiceCard3);
        textView3_2.setText(stringChoiceCard3);
        textView1_1.setText(stringChoiceCard1);
        textView1_2.setText(stringChoiceCard1);

        if (newGame) {
            //Вычесть из общего количества монет ставку
            money = money - bet;
            String stringMoney = String.valueOf(money);
            textViewMoney.setText(stringMoney);
        }

        //Проверка результата
        checkResult(choiceCard4, choiceApp);
    }

    public void onClickButtonTake(View view) {
        restore();
        newGame = true;
        String stringBet = String.valueOf(bet);
        textViewMoneyWin.setText(stringBet);
        money = money + moneyWin;
        String stringMoney = String.valueOf(money);
        textViewMoney.setText(stringMoney);
        moneyWin = bet;
        start();
    }

    public void onClickButtonContinue(View view) {
        //Сделать все как было
        restore();
        newGame = false;
        start();
    }

    public void onClickButtonExit(View view) {
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);
/*        finishAffinity();
        System.exit(0);*/
    }

    public void onClickSetBetFromLayout(View view){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_view, SetBetFragment.class, null)
                .commit();
    }

    public void restore() {
        frameLayoutCard1.setLayoutParams(new LinearLayout.LayoutParams(200, 300));
        frameLayoutCard1.setBackgroundResource(R.drawable.card);
        frameLayoutCard2.setLayoutParams(new LinearLayout.LayoutParams(200, 300));
        frameLayoutCard2.setBackgroundResource(R.drawable.card);
        frameLayoutCard3.setLayoutParams(new LinearLayout.LayoutParams(200, 300));
        frameLayoutCard3.setBackgroundResource(R.drawable.card);
        frameLayoutCard4.setLayoutParams(new LinearLayout.LayoutParams(200, 300));
        frameLayoutCard4.setBackgroundResource(R.drawable.card);

        textView1_1.setText(" ?");
        textView1_2.setText(" ?");
        textView2_1.setText(" ?");
        textView2_2.setText(" ?");
        textView3_1.setText(" ?");
        textView3_2.setText(" ?");
        textView4_1.setText(" ?");
        textView4_2.setText(" ?");
    }

}
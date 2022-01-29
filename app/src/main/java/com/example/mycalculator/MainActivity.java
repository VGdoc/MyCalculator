package com.example.mycalculator;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final Calc calc = new Calc();
    long numberToDisplay; // переменная содержит значения для отображения
    final int DIGIT_RATE = 10; // константа для увеличения отображаемого числа

    protected TextView summaries; // поле отображения
    private final Button[] buttons = new Button[ExistButtons.values().length]; // массив всех кнопок калькулятора

    /**
     * Листенер класса. Содержит все действия для кнопок калькулятора
     */
    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                /////////////////////////////////////////////// цифры
                case (R.id.button_0):
                    addDigitsToDisplay(0);
                    break;
                case (R.id.button_1):
                    addDigitsToDisplay(1);
                    break;
                case (R.id.button_2):
                    addDigitsToDisplay(2);
                    break;
                case (R.id.button_3):
                    addDigitsToDisplay(3);
                    break;
                case (R.id.button_4):
                    addDigitsToDisplay(4);
                    break;
                case (R.id.button_5):
                    addDigitsToDisplay(5);
                    break;
                case (R.id.button_6):
                    addDigitsToDisplay(6);
                    break;
                case (R.id.button_7):
                    addDigitsToDisplay(7);
                    break;
                case (R.id.button_8):
                    addDigitsToDisplay(8);
                    break;
                case (R.id.button_9):
                    addDigitsToDisplay(9);
                    break;
                /////////////////////////////////////////////////// дополнительные кнопки
                case (R.id.button_00):
                    addDigitsToDisplay(0);
                    addDigitsToDisplay(0);
                    break;
                case (R.id.button_coma):
                    //TODO
                    summaries.setText((String)"Функционал в разработке");
                    break;
                /////////////////////////////////////////////////// кнопки управления
                case (R.id.button_clear):
                    numberToDisplay = 0;
                    summaries.setText(Long.toString(numberToDisplay));
                    break;
                case (R.id.button_backspace):
                    //TODO
                    summaries.setText((String)"Бекспейс в разработке");
                    break;
                /////////////////////////////////////////////////// кнопки действий
                case (R.id.button_persent): // %
                    //TODO
                    summaries.setText((String)"Процент в разработке");
                    break;
                case (R.id.button_division): // деление
                    //TODO
                    summaries.setText((String)"деление в разработке");
                    break;
                case (R.id.button_multiplication): // *
                    //TODO
                    summaries.setText((String)"умножение в разработке");
                    break;
                case (R.id.button_substraction): // -
                    //TODO
                    summaries.setText((String)"вычитание в разработке");
                    break;
                case (R.id.button_addition): // +
                    //TODO
                    summaries.setText((String)"додавание в разработке");
                    break;
                case (R.id.button_equals): // =
                    //TODO
                    summaries.setText((String)"равно в разработке");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    };

    /**
     * Список существующих кнопок, содержащих свои ID
     */
    enum ExistButtons {

        ZERO(R.id.button_0),
        ONE(R.id.button_1),
        TWO(R.id.button_2),
        THREE(R.id.button_3),
        FOUR(R.id.button_4),
        FIVE(R.id.button_5),
        SIX(R.id.button_6),
        SEVEN(R.id.button_7),
        EIGHT(R.id.button_8),
        NINE(R.id.button_9),
        DIVISION(R.id.button_division),
        MULTIPLICATION(R.id.button_multiplication),
        SUBTRACTION(R.id.button_substraction),
        ADDITION(R.id.button_addition),
        EQUALS(R.id.button_equals),
        COMA(R.id.button_coma),
        CLEAR(R.id.button_clear),
        BACKSPACE(R.id.button_backspace),
        DOUBLE_ZERO(R.id.button_00),
        PERSENT(R.id.button_persent);

        private final int layoutID;

        ExistButtons(@IdRes int layoutID) {
            this.layoutID = layoutID;
        }

        public int getLayoutID() {
            return layoutID;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        init();
        setListeners();
    }

    /**
     * Подключает всем кнопкам в массиве Listener
     */
    public void setListeners() {
        for (Button b : buttons) {
                b.setOnClickListener(listener);
        }
    }

    /**
     * инициализирует все интерактивные элементы
     */
    public void init() {
        for (int i = 0; i < ExistButtons.values().length; i++){
            buttons[i] = findViewById(ExistButtons.values()[i].getLayoutID());
        }

        summaries = findViewById(R.id.summaries);
        summaries.setText(Long.toString(0));
    }

    /**
     * Метод добавляет цифры на дисплей при нажатии на цифровые кнопки
     * @param digit какую цифу добавить
     */
    private void addDigitsToDisplay(int digit){
        numberToDisplay = numberToDisplay * DIGIT_RATE + digit;
        summaries.setText(Long.toString(numberToDisplay));
    }

}
















































package com.example.mycalculator;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final String THEME_SETTINGS = "theme_settings";
    public static final String SUCCESS_MESSEGE = "theme_changed";
    public static final int SUCCESS_CODE = 777;
    public static final String RECREATE_FLAG = "RECREATE_FLAG";
    private static String savedScreenState = Long.toString(Calc.START_NUMBER_ON_DISPLAY);
    protected TextView summaries; // поле отображения
    private final Button[] buttons = new Button[ExistButtons.values().length]; // массив всех кнопок этой активити

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
                    summaries.setText((String) "Функционал в разработке");
                    break;
                /////////////////////////////////////////////////// кнопки управления
                case (R.id.button_clear):
                    Calc.reset();
                    renewSummaries();
                    break;
                case (R.id.button_backspace):
                    //TODO
                    summaries.setText((String) "Бекспейс в разработке");
                    break;
                /////////////////////////////////////////////////// кнопки действий
                case (R.id.button_persent): // %
                    Calc.operationPercent();
                    renewSummaries();
                    break;
                case (R.id.button_division): // деление
                    Calc.newOperation(Calc.Operations.DIVIDE);
                    renewSummaries();
                    break;
                case (R.id.button_multiplication): // *
                    Calc.newOperation(Calc.Operations.MULTIPLY);
                    renewSummaries();
                    break;
                case (R.id.button_substraction): // -
                    Calc.newOperation(Calc.Operations.MINUS);
                    renewSummaries();
                    break;
                case (R.id.button_addition): // +
                    Calc.newOperation(Calc.Operations.PLUS);
                    renewSummaries();
                    break;
                case (R.id.button_equals): // =
                    Calc.showEquals();
                    renewSummaries();
                    break;
                /////////////////////////////////////////// настройки темы
                case (R.id.settings): // настройик темы
                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivityForResult(intent, SUCCESS_CODE);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SUCCESS_CODE && resultCode == RESULT_OK) {
            summaries.setText(R.string.theme_changed_successfully);
            if (data.getExtras() != null) {
                recreate();
                summaries.setText(data.getStringExtra(SUCCESS_MESSEGE));
            }
        }
    }


    /**
     * Список существующих кнопок, содержащих свой ID
     * Если сюда добавть кнопку с ID и прописать ей onClick выше,
     * то по-идее ничего не сломается и кнопка будет работать
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
        PERSENT(R.id.button_persent),
        SETTINGS(R.id.settings);
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

        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.THEME_SETTINGS, MODE_PRIVATE);
        setTheme(sharedPreferences.getInt(THEME_SETTINGS, R.style.Theme_MyCalculator));

        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        init();
        setListeners();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        savedScreenState = String.valueOf(summaries.getText());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        summaries.setText(savedScreenState);
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
        for (int i = 0; i < ExistButtons.values().length; i++) {
            buttons[i] = findViewById(ExistButtons.values()[i].getLayoutID());
        }

        summaries = findViewById(R.id.summaries);
        summaries.setText(savedScreenState);
    }

    /**
     * Метод добавляет цифры на дисплей при нажатии на цифровые кнопки
     *
     * @param digit какую цифу добавить
     */
    private void addDigitsToDisplay(int digit) {
        Calc.addNewDigit(digit);
        renewSummaries();
    }

    private void renewSummaries(){
            summaries.setText(fmt(Calc.getNumberToDisplay()));
    }

    /**
     * Метод взят со стековерфлоу, убирает ненужные нули в дробном числе
     * @param d дробное число
     * @return красивая строка без лишних нулей
     */
    public static String fmt(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }

}
















































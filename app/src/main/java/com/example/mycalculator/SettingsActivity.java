package com.example.mycalculator;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    private static int currentTheme = R.style.Theme_MyCalculator;
    private final Button[] buttons = new Button[SettingsActivity.ExistButtons.values().length]; // массив всех кнопок этой активити
    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                ////////////////////////////////////////////// радиокнопки для переключния темы
                case (R.id.rb_cyan_theme): // бирюзовая тема
                    currentTheme = R.style.NiceCyan;
                    recreate();
                    break;
                case (R.id.rb_pink_theme): // розовая тема
                    currentTheme = R.style.NicePink;
                    recreate();
                    break;
                case (R.id.rb_default_theme): // стандартная тема
                    currentTheme = R.style.Theme_MyCalculator;
                    recreate();
                    break;
                case (R.id.save_exit): // сохранить и выйти
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.SUCCESS_MESSEGE, getString(R.string.theme_changed_successfully));
                    SettingsActivity.this.setResult(RESULT_OK, intent);
                    finish();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    };

    enum ExistButtons {
        RADIO_BUTTON_CYAN(R.id.rb_cyan_theme),
        RADIO_BUTTON_PINK(R.id.rb_pink_theme),
        RADIO_BUTTON_DEFAULT(R.id.rb_default_theme),
        SAVE_AND_EXIT(R.id.save_exit);
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
        setTheme(currentTheme);
        setContentView(R.layout.settings_activity);

        init();
        setListeners();


    }

    /**
     * инициализирует все интерактивные элементы
     */
    public void init() {
        for (int i = 0; i < SettingsActivity.ExistButtons.values().length; i++) {
            buttons[i] = findViewById(SettingsActivity.ExistButtons.values()[i].getLayoutID());
        }

    }

    /**
     * Подключает всем кнопкам в массиве Listener
     */
    public void setListeners() {
        for (Button b : buttons) {
            b.setOnClickListener(listener);
        }
    }
}
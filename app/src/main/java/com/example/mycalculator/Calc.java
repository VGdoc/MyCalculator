package com.example.mycalculator;


import android.os.Parcel;
import android.os.Parcelable;

public class Calc implements Parcelable {

    private static double result = 0;
    private static boolean isResultShow; // флаг говорит нужно ли отрисовать результат вычислений
    private static Operations currentOperation;
    protected static final int START_NUMBER_ON_DISPLAY = 0; //стартовое значения поля TextView
    private static double numberToDisplay; // переменная содержит значения для отображения
    private static final int DIGIT_RATE = 10; // константа для увеличения отображаемого числа

    protected Calc(Parcel in) {
    }

    enum Operations {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        EQUALS;
    }

    public static final Creator<Calc> CREATOR = new Creator<Calc>() {

        @Override
        public Calc createFromParcel(Parcel in) {
            return new Calc(in);
        }

        @Override
        public Calc[] newArray(int size) {
            return new Calc[size];
        }
    };

    /**
     * Метод добавляет цифры к старому числу, увеличивая его на 1 разряд
     *
     * @param digit какую цифу добавить
     */
    public static void addNewDigit(int digit) {
        if (isResultShow){
            numberToDisplay = START_NUMBER_ON_DISPLAY;
            isResultShow = false;
        }
        numberToDisplay = numberToDisplay * DIGIT_RATE + digit;
    }

    public static double getNumberToDisplay() {
        return numberToDisplay;
    }

    public static void reset() {
        numberToDisplay = START_NUMBER_ON_DISPLAY;
        result = START_NUMBER_ON_DISPLAY;
        currentOperation = null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }


    /**
     * Метод производит вычисления на основе нажатых кнопок операций с числами
     */
    public static void newOperation(Operations operation) {

        if (currentOperation != null) {
            switch (currentOperation) {
                case PLUS:
                    result = result + numberToDisplay;
                    numberToDisplay = result;
            }
        } else {
            result = numberToDisplay;
        }


        isResultShow = true;
        currentOperation = operation;
    }
}

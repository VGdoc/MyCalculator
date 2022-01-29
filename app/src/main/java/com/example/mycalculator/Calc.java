package com.example.mycalculator;


import android.os.Parcel;
import android.os.Parcelable;

public class Calc implements Parcelable {


    protected static final int START_NUMBER_ON_DISPLAY = 0; //стартовое значения поля TextView
    private static long numberToDisplay; // переменная содержит значения для отображения
    private static final int DIGIT_RATE = 10; // константа для увеличения отображаемого числа

    protected Calc(Parcel in) {
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
        numberToDisplay = numberToDisplay * DIGIT_RATE + digit;
    }

    public static long getNumberToDisplay() {
        return numberToDisplay;
    }

    public static void reset() {
        numberToDisplay = START_NUMBER_ON_DISPLAY;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}

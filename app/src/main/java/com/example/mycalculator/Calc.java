package com.example.mycalculator;


public class Calc {


    private static long numberToDisplay; // переменная содержит значения для отображения
    static final int DIGIT_RATE = 10; // константа для увеличения отображаемого числа

    /**
     * Метод добавляет цифры к старому числу, увеличивая его на 1 разряд
     * @param digit какую цифу добавить
     */
    public static void addNewDigit(int digit) {
        numberToDisplay = numberToDisplay * DIGIT_RATE + digit;
    }

    public static long getNumberToDisplay() {
        return numberToDisplay;
    }

    public static void reset(){
        numberToDisplay = 0;
    }
}

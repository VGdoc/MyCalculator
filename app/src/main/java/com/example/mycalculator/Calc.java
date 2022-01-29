package com.example.mycalculator;


public class Calc {

    /**
     * Метод добавляет цифры к старому числу, увеличивая его на 1 разряд
     * @param oldNumber число, которое необходимо увеличить
     * @param digit какую цифу добавить
     */
    public static long addNewDigit(long oldNumber, int digit) {
        return oldNumber * 10 + digit;
    }

}

package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String line = "";
        try {
            line = bis.readLine();
            if (line.equalsIgnoreCase("EXIT")) throw new InterruptOperationException();
        } catch (IOException e) {

        }
        return line;
    }

    //считать код валюты
    public static String askCurrencyCode() throws InterruptOperationException{
        String currency;
        while (true) {
            ConsoleHelper.writeMessage("Введите трехзначный буквенный код валюты");
            currency = readString();
            if (currency.length() != 3) {
                writeMessage("Некоректный код валюты");
            }else break;
        }
        return currency.toUpperCase();
    }

    //Чтобы считать номинал и количество банкнот
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] array;
        int denomination;
        int quantity;
        writeMessage("Введите номинал и количество купюр в формате \"Номинал количество\"");
        while (true) {
            String s = readString();
            array = s.split(" ");
            try {
                denomination = Integer.parseInt(array[0]);
                quantity = Integer.parseInt(array[1]);
                if (denomination <= 0 || quantity <= 0 || array.length > 2) {
                    writeMessage("Неверный формат, попробуйте снова");
                    continue;
                } else break;
            } catch (Exception e) {
            }
            writeMessage("Неверный формат, попробуйте снова");
        }
        return array;
    }

    //Спрашивает какую операцию пользователь хочет совершить
    public static Operation askOperation() throws InterruptOperationException{
        while (true) {
            Operation operation;
            try {
                writeMessage("Выберте операцию которую хотите выполнить:\n" +
                        "1 - INFO\n2 - DEPOSIT\n3 - WITHDRAW\n4 - EXIT");
                String line = ConsoleHelper.readString();
                operation = Operation.getAllowableOperationByOrdinal(Integer.parseInt(line));
            } catch (IllegalArgumentException e) {
                operation = askOperation();
            }
            return operation;
        }
    }
}
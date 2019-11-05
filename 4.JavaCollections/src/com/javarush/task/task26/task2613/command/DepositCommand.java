package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class DepositCommand implements Command{
    /**
     * 4. В классе DepositCommand в методе execute() запроси код валюты,
     * потом запроси номинал и количество банкнот, а потом добавь их в манипулятор.
     * Если номинал и количество банкнот пользователь ввел не правильно(не числа) - повторять попытку по введению номинала и количества банкнот.
     */

    @Override
    public void execute() throws InterruptOperationException {
        String code = ConsoleHelper.askCurrencyCode();
        String[] value = ConsoleHelper.getValidTwoDigits(code);
        CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code).addAmount(Integer.parseInt(value[0]), Integer.parseInt(value[1]));
    }
}

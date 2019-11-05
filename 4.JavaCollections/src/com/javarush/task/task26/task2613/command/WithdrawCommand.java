package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;


class WithdrawCommand implements Command{

    @Override
    public void execute() throws InterruptOperationException {
        CurrencyManipulator curManipulatr = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());
        int cash;
        while (true){
            ConsoleHelper.writeMessage("Введите сумму");
            try{
                cash = Integer.parseInt(ConsoleHelper.readString());
                if (cash < 0) {
                    ConsoleHelper.writeMessage("Введена некорректная сумма");
                }else if (curManipulatr.isAmountAvailable(cash)) {
                    for (Map.Entry<Integer, Integer> map : curManipulatr.withdrawAmount(cash).entrySet()){
                        ConsoleHelper.writeMessage("\t" + map.getKey() + " - " + map.getValue());
                    }
                    ConsoleHelper.writeMessage("Операция успешно выполнена.");
                    break;
                }
            }catch (NumberFormatException | NotEnoughMoneyException e){
                ConsoleHelper.writeMessage("Введена некорректная сумма");
            }
        }
    }
}

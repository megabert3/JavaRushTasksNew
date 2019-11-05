package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;

class InfoCommand implements Command {
    @Override
    public void execute() {
        Collection<CurrencyManipulator> currencyManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (currencyManipulators.isEmpty()) {
            ConsoleHelper.writeMessage("No money available.");
        }
        for (CurrencyManipulator currencyManipulator : currencyManipulators) {
            if (currencyManipulator.hasMoney()) {
                ConsoleHelper.writeMessage(currencyManipulator.getCurrencyCode().toUpperCase() + " - " + currencyManipulator.getTotalAmount());
            } else {
                ConsoleHelper.writeMessage("No money available.");
            }
        }
    }
}

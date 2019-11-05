package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    /**который будет хранить всю информацию про выбранную валюту*/

    //код валюты, например, USD. Состоит из трех букв.
    private String currencyCode;

    //это Map<номинал, количество>.
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    //Добавляет деньги
    public void addAmount(int denomination, int count){
        if (denominations.containsKey(denomination))
        denominations.put(denomination, denominations.get(denomination) + count);
        else
        denominations.put(denomination, count);
    }
    //Возвращает общее кол-во денег в одной валюте
    public int getTotalAmount(){
        int totalAmount = 0;
        for (Map.Entry<Integer, Integer> map : denominations.entrySet()){
            int amount = map.getKey() * map.getValue();
            totalAmount += amount;
        }
        return totalAmount;
    }
    // проверяет есть ли деньги
    public boolean hasMoney(){
        return getTotalAmount() > 0;
    }

    //проверяет достаточно ли денег для снятия
    public boolean isAmountAvailable(int expectedAmount){
        return (getTotalAmount() - expectedAmount) > 0;
    }

    //Списать деньги со счета
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        int sum = expectedAmount;
        HashMap<Integer, Integer> temp = new HashMap<>();
        temp.putAll(denominations);
        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> pair : temp.entrySet())
            list.add(pair.getKey());

        Collections.sort(list);
        Collections.reverse(list);

        TreeMap<Integer, Integer> result = new TreeMap<>(new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                return o2.compareTo(o1);
            }
        });

        for (Integer aList : list) {
            int key = aList;
            int value = temp.get(key);
            while (true) {
                if (sum < key || value <= 0) {
                    temp.put(key, value);
                    break;
                }
                sum -= key;
                value--;

                if (result.containsKey(key))
                    result.put(key, result.get(key) + 1);
                else
                    result.put(key, 1);
            }
        }
//        if (sum > 0)
//            throw new NotEnoughMoneyException();
//        else
//        {
//            for (Map.Entry<Integer, Integer> pair : result.entrySet())
//                ConsoleHelper.writeMessage("\t" + pair.getKey() + " - " + pair.getValue());
//
//            denominations.clear();
//            denominations.putAll(temp);
//            ConsoleHelper.writeMessage("Transaction was successful!");
//        }
        return result;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
}

package machine.manipulators;


import machine.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;


    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> map = new HashMap<>();
        int availableAmount = 0;

        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            if (availableAmount == expectedAmount) {
                break;
            }
            for (int i = 0; i < entry.getValue(); i++) {
                int key = entry.getKey();
                if (availableAmount == expectedAmount || key + availableAmount > expectedAmount) {
                    break;
                } else {
                    availableAmount = availableAmount + key;
                    if (map.containsKey(key)) {
                        map.put(key, map.get(key) + 1);
                    } else {
                        map.put(key, 1);
                    }
                }
            }
        }
        if (availableAmount != expectedAmount) {
            throw new NotEnoughMoneyException();
        }
        withdrawFromTotalAmount(map);
        return map;
    }

    private void withdrawFromTotalAmount(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            denominations.put(key, denominations.get(key) - entry.getValue());
            if (denominations.get(key) == 0) {
                denominations.remove(key);
            }
        }
    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        this.denominations = new TreeMap<>(Collections.reverseOrder());
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int amount = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            amount = amount + entry.getKey() * entry.getValue();
        }
        return amount;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Map<Integer, Integer> getDenominations() {
        return denominations;
    }
}

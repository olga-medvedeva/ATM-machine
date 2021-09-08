package machine.command;

import machine.ConsoleHelper;
import machine.manipulators.CurrencyManipulator;
import machine.manipulators.CurrencyManipulatorFactory;
import machine.exception.InterruptOperationException;
import machine.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

class WithdrawCommand implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        ConsoleHelper.writeMessage("Withdrawing...");
        ConsoleHelper.writeMessage("Please specify integer amount for withdrawing.");
        Map<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        map.putAll(getMoney(currencyManipulator));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ConsoleHelper.writeMessage(entry.getKey() + " - " + entry.getValue());
        }
    }

    private Map<Integer, Integer> getMoney(CurrencyManipulator currencyManipulator) throws InterruptOperationException{
        Map<Integer, Integer> map;
        try {
            String s = ConsoleHelper.readString();
            int i = Integer.parseInt(s);
            if (i < 1) {
                throw new IllegalArgumentException();
            } else if (currencyManipulator.isAmountAvailable(i)) {
                map = currencyManipulator.withdrawAmount(i);
            } else {
                throw new NotEnoughMoneyException();
            }
        } catch (NotEnoughMoneyException | IllegalArgumentException e) {
            if (e instanceof NotEnoughMoneyException) {
                ConsoleHelper.writeMessage("Exact amount is not available");
                ConsoleHelper.writeMessage("Specify another amount");
                getMoney(currencyManipulator);
            } else {
                ConsoleHelper.writeMessage("Please specify valid positive integer amount for withdrawing.");
            }
            map = getMoney(currencyManipulator);
        }
        return map;
    }
}

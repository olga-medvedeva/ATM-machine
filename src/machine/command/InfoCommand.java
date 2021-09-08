package machine.command;

import machine.ConsoleHelper;
import machine.manipulators.CurrencyManipulator;
import machine.manipulators.CurrencyManipulatorFactory;

import java.util.Map;


class InfoCommand implements Command{

    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Information:");
        if (!CurrencyManipulatorFactory.getAllCurrencyManipulators().isEmpty()) {
            for (CurrencyManipulator manipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
                if (manipulator.hasMoney()) {
                    ConsoleHelper.writeMessage(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount() + "/n");
                }
                for (Map.Entry<Integer, Integer> currency : manipulator.getDenominations().entrySet()) {
                    ConsoleHelper.writeMessage(currency.getKey() + " - " + currency.getValue());
                }
            }
            ConsoleHelper.writeMessage("/n");
        } else {
            ConsoleHelper.writeMessage("No money available.");
        }
    }
}

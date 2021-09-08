package machine.command;


import machine.ConsoleHelper;
import machine.manipulators.CurrencyManipulator;
import machine.manipulators.CurrencyManipulatorFactory;
import machine.exception.InterruptOperationException;

class DepositCommand implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Depositing...");
        String code = ConsoleHelper.askCurrencyCode();
        String[] denominationAndBanknotes = ConsoleHelper.getTwoValidNumbers(code);
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        currencyManipulator.addAmount(Integer.parseInt(denominationAndBanknotes[0]), Integer.parseInt(denominationAndBanknotes[1]));
        ConsoleHelper.writeMessage(String.format("%d %s was deposited successfully", Integer.parseInt(denominationAndBanknotes[0]) * Integer.parseInt(denominationAndBanknotes[1]), code));
    }
}

package machine;


import machine.command.CommandExecutor;
import machine.exception.InterruptOperationException;

public class CashMachine {

    public static void main(String[] args) {
        try {

            Operation operation;
            CommandExecutor.execute(Operation.LOGIN);
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
        }
    }
}

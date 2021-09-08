package machine.command;

import machine.ConsoleHelper;
import machine.exception.InterruptOperationException;

class ExitCommand implements Command{

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Do you really want to exit?\n <y,n>");
            String s = ConsoleHelper.readString();
            if (s != null && s.equalsIgnoreCase("y")) {
                ConsoleHelper.writeMessage("Thank you for visiting ATMMachine. Good luck.");
            }
    }
}

package machine.command;


import machine.Card;
import machine.manipulators.CardManipulator;
import machine.ConsoleHelper;
import machine.exception.InterruptOperationException;


class LoginCommand implements Command {
    private final CardManipulator cardManipulator = new CardManipulator();

    @Override
    public void execute() throws InterruptOperationException {
        Card card = null;
        String cardNumber;
        String pinCode;
        boolean isCardNumberCorrect = false;
        boolean isPinCodeCorrect = false;

        ConsoleHelper.writeMessage("Logging in...");
        ConsoleHelper.writeMessage("Please specify your credit card number and pin code or type 'EXIT' for exiting.");
        do {
            cardNumber = ConsoleHelper.readString();
            if (!cardManipulator.checkCardNumberPattern(cardNumber)) {
                ConsoleHelper.writeMessage("Please try again or type 'EXIT' for urgent exiting");
                ConsoleHelper.writeMessage("Specify valid credit card number - 12 digits.");
            } else if (!cardManipulator.containsCard(cardNumber)) {
                ConsoleHelper.writeMessage(String.format("Credit card [%s] is not exist.", cardNumber));
            } else {
                card = cardManipulator.getCard(cardNumber);
                isCardNumberCorrect = true;
            }
        } while (!isCardNumberCorrect);

        ConsoleHelper.writeMessage("Enter pin-code:");
        do {
            pinCode = ConsoleHelper.readString();
            if (!card.checkPassword(pinCode)) {
                ConsoleHelper.writeMessage("Please try again or type 'EXIT' for urgent exiting");
                ConsoleHelper.writeMessage("Specify valid pin code - 4 digits.");
            } else {
                isPinCodeCorrect = true;
            }
        } while (!isPinCodeCorrect);
        ConsoleHelper.writeMessage(String.format("Credit card [%s] is verified successfully!", cardNumber));
    }
}

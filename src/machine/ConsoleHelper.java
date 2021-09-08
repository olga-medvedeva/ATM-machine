package machine;


import machine.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String s = null;
        try {
            s = bis.readLine();
            if (s.equalsIgnoreCase("EXIT")) {
                ConsoleHelper.writeMessage("Terminated. Thank you for visiting ATMMachine. Good luck.");
                throw new InterruptOperationException();
            } else {
                return s;
            }
        } catch (IOException e) {
            writeMessage("Line reading problem");
            s = readString();
        }
        return s;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage("Enter currency code:");
        String code = null;
        try {
            code = readString();
            if (code.length() != 3 || !code.matches("[A-Za-z]+")) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            writeMessage("Invalid currency code");
            code = askCurrencyCode();
        }
        return code.trim().toUpperCase();
    }

    public static String[] getTwoValidNumbers(String currencyCode) throws InterruptOperationException {
        String[] result = null;
        try {
            writeMessage("Enter the denomination and number of banknotes:");
            String s = readString();
            String[] value = s.split(" ");
            int denomination;
            int banknotes;
            if (value.length != 2 || (denomination = Integer.parseInt(value[0])) <= 0 || (banknotes = Integer.parseInt(value[1])) <= 0
            || (denomination != 100 && denomination != 500 && denomination != 1000 && denomination != 5000)) {
                throw new IllegalArgumentException();
            }
            result = new String[]{String.valueOf(denomination), String.valueOf(banknotes)};
        } catch (IllegalArgumentException e) {
            writeMessage("Incorrect value of denomination or number of banknotes");
            result = getTwoValidNumbers(currencyCode);
        }
        return result;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage("Enter operation number");
        writeMessage("1 - INFO");
        writeMessage("2 - DEPOSIT");
        writeMessage("3 - WITHDRAW");
        writeMessage("4 - EXIT");
        String s = readString();
        Operation operation;
        try {
            operation = Operation.getAllowableOperationByOrdinal(Integer.valueOf(s));
        } catch (IllegalArgumentException e) {
            writeMessage("Invalid operation number");
            operation = askOperation();
        }
        return operation;
    }

    public static void printExitMessage() {
        writeMessage("you left the program");
    }
}


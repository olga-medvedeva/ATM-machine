package machine;

import machine.exception.NotEnoughMoneyException;

import java.nio.charset.StandardCharsets;

public class Card {
    private final String cardNumber;
    private String pinCode;

    public Card(String cardNumber, String password) {
        this.cardNumber = cardNumber;
        this.pinCode = encrypt(password);
    }

    // Энкриптор на коленке
    private String encrypt(String pinCode) {
        byte[] bytes = pinCode.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] << 1);
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append((char) b);
        }
        return sb.toString();
    }

    // Декриптор на коленке
    private String decrypt(String pinCode) {
        byte[] bytes = pinCode.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] >> 1);
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append((char) b);
        }
        return sb.toString();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public boolean checkPassword(String pinCode) {
        return pinCode.trim().length() == 4 && pinCode.matches("^[0-9]+$") && decrypt(this.pinCode).equals(pinCode);
    }
}

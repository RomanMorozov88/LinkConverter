package ru.morozov.services.generators;

import java.util.Random;

/**
 * В данном случае для генерации и логина, и пароля, и уникального ключа для адреса страницы
 * будет использоваться этот простой генератор.
 */
public class SimpleCodeGenerator implements LoginGenerator, PasswordGenerator, PagesKeyGenerator {

    private static final Random RN = new Random();
    private static final String ENG = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static int index = 0;

    private String getCombination() {
        return this.incrIndex() + this.getRndChar() + this.getRndNumber();
    }

    private Character getRndChar() {
        return ENG.charAt(RN.nextInt(ENG.length()));
    }

    private String getRndNumber() {
        return String.valueOf(RN.nextInt(500));
    }

    private synchronized String incrIndex() {
        return String.valueOf(index++);
    }

    @Override
    public String loginGenerate() {
        return this.getCombination();
    }

    @Override
    public String keyGenerate() {
        return this.getCombination();
    }

    @Override
    public String passwordGenerate() {
        return this.getCombination();
    }
}

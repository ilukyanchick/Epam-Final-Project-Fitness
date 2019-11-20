package by.epam.fitness.util.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {
    private static final Pattern LOGIN_PATTERN = Pattern.compile("^[a-zA-Z][\\w-_.]{2,19}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
    private static final Pattern NAME_SURNAME_PATTERN = Pattern.compile("[a-zA-Zа-яА-Я]{3,20}");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[\\w-_.]{3,20}");
    private static final Pattern CARD_NUMBER_PATTERN = Pattern.compile("[0-9]{16}");
    private static final Pattern INPUT_TEXT_PATTERN = Pattern.compile("[A-Za-z0-9][A-Za-z,.()\\s0-9]{1,299}");
    private static final Pattern SHA512_PATTERN = Pattern.compile("[a-f0-9]{128}");
    private static final Pattern INPUT_IDENTIFIABLE_ID_PATTERN = Pattern.compile("[\\d]{1,20}");
    private static final Pattern SET_NUMBER_PATTERN = Pattern.compile("^[1-9][0-9]*$");
    private static final Pattern COST_PATTERN = Pattern.compile("[\\d.]{1,20}");

    public boolean isLoginValid(String login) {
        Matcher matcher = LOGIN_PATTERN.matcher(login);
        return matcher.matches();
    }

    public boolean isPasswordValid(String password) {
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }

    public boolean isEmailValid(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
    public boolean isHashValid(String hash) {
        Matcher matcher = SHA512_PATTERN.matcher(hash);
        return matcher.matches();
    }

    public boolean isCardNumberValid(String cardNumber) {
        Matcher matcher = CARD_NUMBER_PATTERN.matcher(cardNumber);
        return matcher.matches();
    }

    public boolean isNameValid(String name) {
        Matcher matcher = NAME_SURNAME_PATTERN.matcher(name);
        return matcher.matches();
    }

    public boolean isSurnameValid(String surname) {
        Matcher matcher = NAME_SURNAME_PATTERN.matcher(surname);
        return matcher.matches();
    }

    public boolean isIdentifiableIdValid(String userId) {
        Matcher matcher = INPUT_IDENTIFIABLE_ID_PATTERN.matcher(userId);
        return matcher.matches();
    }

    public boolean isNutritionDescriptionValid(String description) {
        Matcher matcher = INPUT_TEXT_PATTERN.matcher(description);
        return matcher.matches();
    }

    public boolean isCommentContentValid(String commentContent) {
        Matcher matcher = INPUT_TEXT_PATTERN.matcher(commentContent);
        return matcher.matches();
    }

    public boolean isSetNumberValid(String setNumber) {
        Matcher matcher = SET_NUMBER_PATTERN.matcher(setNumber);
        return matcher.matches();
    }

    public boolean isCostValid(String cost){
        Matcher matcher = COST_PATTERN.matcher(cost);
        return matcher.matches();
    }
}

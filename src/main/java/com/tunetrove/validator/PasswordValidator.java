package com.tunetrove.validator;

import com.tunetrove.annotation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    private Pattern pattern;
    private Matcher matcher;
    // Wzorzec dla hasła: co najmniej 8 znaków, co najmniej jedna duża i jedna mała litera, co najmniej jedna cyfra i co najmniej jeden znak specjalny
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    @Override
    public void initialize(ValidPassword constraintAnnotation) {}

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        if (password == null) {
            return false;
        }
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
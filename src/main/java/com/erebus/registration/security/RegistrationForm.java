package com.erebus.registration.security;

import com.erebus.registration.model.User;
import com.erebus.registration.validation.PasswordMatches;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@PasswordMatches
public class RegistrationForm {
    private static final String FIRST_NAME_PATTERN_REGEX = "^[A-Z][A-Za-z-][A-Za-z- ]{0,23}";
    private static final String LAST_NAME_PATTERN_REGEX = "^[A-Z][A-Za-z-][A-Za-z- ]{0,48}";
    private static final String DISPLAY_NAME_PATTERN_REGEX = "^[\\w ]{5,25}";
    private static final String EMAIL_PATTERN_REGEX = "^[\\w-.]{3,}@[\\w-.]{4,}";
    private static final String PASSWORD_PATTERN_REGEX = "^[\\S]{6,}";
    private static final String FIRST_NAME_PATTERN_MESSAGE = "Only letter, '-' and ' ' character are allowed, min length is 2 characters, max length is 25, first character must be large letter.";
    private static final String LAST_NAME_PATTERN_MESSAGE = "Only letter, '-' and ' ' character are allowed, min length is 2 characters, max length is 50, first character must be large letter.";
    private static final String DISPLAY_NAME_PATTERN_MESSAGE = "Display name should be at least 5 character long.";
    private static final String EMAIL_PATTERN_MESSAGE = "Invalid email format (account@provider).";
    private static final String PASSWORD_PATTERN_MESSAGE = "Password cannot contain any whitespace character, min length of password is 6";

    @Pattern(regexp = FIRST_NAME_PATTERN_REGEX,
            message = FIRST_NAME_PATTERN_MESSAGE)
    private String firstName;

    @Pattern(regexp = LAST_NAME_PATTERN_REGEX,
            message = LAST_NAME_PATTERN_MESSAGE)
    private String lastName;

    @Pattern(regexp = DISPLAY_NAME_PATTERN_REGEX,
            message = DISPLAY_NAME_PATTERN_MESSAGE)
    private String displayName;

    @Pattern(regexp = EMAIL_PATTERN_REGEX,
            message = EMAIL_PATTERN_MESSAGE)
    private String email;

    @Pattern(regexp = PASSWORD_PATTERN_REGEX,
            message = PASSWORD_PATTERN_MESSAGE)
    private String password;

    @Pattern(regexp = PASSWORD_PATTERN_REGEX,
            message = PASSWORD_PATTERN_MESSAGE)
    private String confirmPassword;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .displayName(displayName)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .password(passwordEncoder.encode(password))
                .build();
    }
}

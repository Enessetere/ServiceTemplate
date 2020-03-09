package com.erebus.registration.web;

import com.erebus.registration.repository.UserRepository;
import com.erebus.registration.security.RegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @ModelAttribute(name = "registrationForm")
    private RegistrationForm registrationForm() {
        return new RegistrationForm();
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String submitRegister(@Valid RegistrationForm form, Errors errors) {
        if(errors.hasErrors()) {
            if (isEmailExisting(form)) {
                emailExistError(errors);
            }
            return "registration";
        }
        if(isEmailExisting(form)) {
            emailExistError(errors);
            return "registration";
        }
        userRepository.save(form.toUser(passwordEncoder).correction());
        return "redirect:/success";
    }

    private boolean isEmailExisting(final RegistrationForm form) {
        return userRepository.findByEmail(form.getEmail()).isPresent();
    }

    private void emailExistError(final Errors errors) {
        errors.rejectValue("email", "email.exists", "E-mail address already exists.");
    }
}

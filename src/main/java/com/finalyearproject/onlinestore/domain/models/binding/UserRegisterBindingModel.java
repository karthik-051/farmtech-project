package com.mitron.onlinestore.domain.models.binding;

import static com.mitron.onlinestore.util.constants.AppConstants.*;
import static com.mitron.onlinestore.util.constants.ExceptionMessages.*;
import static com.mitron.onlinestore.util.constants.ValidationErrorMessages.*;

import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mitron.onlinestore.util.constants.AppConstants;
import com.mitron.onlinestore.util.constants.ExceptionMessages;
import com.mitron.onlinestore.validation.annotation.FieldMatch;
@Transactional

@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class UserRegisterBindingModel {
    private String username;
    private String password;
    private String confirmPassword;
    private String address;
    private String email;

    public UserRegisterBindingModel() {

    }

    @NotNull(message = INCORRECT_USERNAME_EMPTY_FIELD_ERROR_MSG)
    @NotEmpty(message = INCORRECT_USERNAME_EMPTY_FIELD_ERROR_MSG)
    @Size(min = USER_NAME_MIN_LENGTH, max = USER_NAME_MAX_LENGTH, message = INCORRECT_USERNAME)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = htmlEscape(username);
    }

    @NotNull
    @Size(min = PASSWORD_MIN_LENGTH, message = INCORRECT_PASSWORD)
//    @Pattern(regexp = AppConstants.PASSWORD_VALIDATION_REGEX)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = htmlEscape(password);
    }


    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = htmlEscape(confirmPassword);
    }

    @NotNull
    @Size(min = 5, message = INCORRECT_ADDRESS)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = htmlEscape(address);
    }

    @NotNull
    @Email(
            regexp = VALID_EMAIL_ADDRESS_REGEX,
            message = ExceptionMessages.INCORRECT_EMAIL
    )
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = htmlEscape(email);
    }

    private String htmlEscape(String input){
        input = input.replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;");

        return input;
    }
}

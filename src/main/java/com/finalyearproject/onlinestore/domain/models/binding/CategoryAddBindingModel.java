package com.mitron.onlinestore.domain.models.binding;

import org.hibernate.validator.constraints.Length;

import com.mitron.onlinestore.util.constants.ValidationErrorMessages;

import static com.mitron.onlinestore.util.constants.AppConstants.*;
import static com.mitron.onlinestore.util.constants.ValidationErrorMessages.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryAddBindingModel {

    private String name;

    public CategoryAddBindingModel() {
    }

    @NotNull(message = CATEGORY_NAME_EMPTY_FIELD_ERROR_MSG)
    @NotEmpty(message = CATEGORY_NAME_EMPTY_FIELD_ERROR_MSG)
    @Length(max = CATEGORY_NAME_MAX_LENGTH, message = CATEGORY_NAME_MAX_LENGTH_ERROR_MSG)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = htmlEscape(name);
    }

    private String htmlEscape(String input){
        input = input.replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;");

        return input;
    }
}

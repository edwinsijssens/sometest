package com.spr.jfluxpackagegenerator.ui.layout.validator;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.data.Validator;

/**
 * This validator is used for validating properties that do not allow empty values.
 */
public class NotEmptyAttributeValidator implements Validator {
    
    private static final long serialVersionUID = -4637879587344323035L;
    
    private final String errorMessage;
    
    public NotEmptyAttributeValidator(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    @Override
    public void validate(final Object value) throws EmptyValueException {
        if (value instanceof String && StringUtils.isEmpty((String) value)) {
            throw new EmptyValueException(errorMessage);
        }
    }
}

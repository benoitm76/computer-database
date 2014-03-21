package com.excilys.projet.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;

public class CheckDateConstraintValidator implements
		ConstraintValidator<CheckDate, String>, MessageSourceAware {

	private MessageSource messageSource;

	@Override
	public void initialize(CheckDate date) {
	}

	@Override
	public boolean isValid(String dateField, ConstraintValidatorContext cxt) {
		System.out.println(messageSource.getMessage("date.regex", null,
				"yyyy-MM-dd", LocaleContextHolder.getLocale()));
		if (dateField != null && !dateField.isEmpty()) {
			if (dateField.matches(messageSource.getMessage("date.regex", null,
					"yyyy-MM-dd", LocaleContextHolder.getLocale()))) {

				try {
					DateTimeFormatter formater = DateTimeFormat
							.forPattern(messageSource.getMessage(
									"date.pattern", null, "yyyy-MM-dd",
									LocaleContextHolder.getLocale()));
					formater.parseLocalDate(dateField);
					return true;
				} catch (IllegalArgumentException e) {
					return false;
				}

			} else {
				return false;
			}
		} else {
			return true;
		}

	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;

	}

}

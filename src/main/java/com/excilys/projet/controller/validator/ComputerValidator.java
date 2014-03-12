package com.excilys.projet.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.excilys.projet.model.dto.ComputerDTO;

@Component
public class ComputerValidator implements Validator {
	final Logger logger = LoggerFactory.getLogger(ComputerValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return ComputerDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors e) {
		ComputerDTO obj = (ComputerDTO) o;
		if (obj.getName() == null || obj.getName().trim().isEmpty()) {
			e.rejectValue("name", "computer.name.empty",
					"Invalid name provided");
		}

		if (obj.getCompanyId() < 0) {
			e.rejectValue("companyId", "computer.companyId.error",
					"Invalid company");
		}
	}

}

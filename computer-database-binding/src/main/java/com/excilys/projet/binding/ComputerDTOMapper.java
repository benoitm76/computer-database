package com.excilys.projet.binding;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.excilys.projet.model.Company;
import com.excilys.projet.model.Computer;

@Component
public class ComputerDTOMapper {

	@Autowired
	private ResourceBundleMessageSource messageSource;

	public ComputerDTO createDTO(Computer c) {
		ComputerDTO cDto = null;
		if (c != null) {
			cDto = new ComputerDTO();
			cDto.setId(c.getId());
			cDto.setName(c.getName());
			DateTimeFormatter formater = DateTimeFormat
					.forPattern(messageSource.getMessage("date.pattern", null,
							"yyyy-MM-dd", LocaleContextHolder.getLocale()));
			if (c.getIntroduced() != null) {
				cDto.setIntroduced(formater.print(c.getIntroduced()));
			}
			if (c.getDiscontinued() != null) {
				cDto.setDiscontinued(formater.print(c.getDiscontinued()));
			}
			cDto.setCompanyId(c.getCompany().getId());
			cDto.setCompanyName(c.getCompany().getName());
		}
		return cDto;
	}

	public Computer createEntity(ComputerDTO cDto) {
		Computer c = null;
		if (cDto != null) {
			c = new Computer();
			c.setId(cDto.getId());
			c.setName(cDto.getName());
			DateTimeFormatter formater = DateTimeFormat
					.forPattern("yyyy-MM-dd");
			c.setIntroduced(formater.parseLocalDate(cDto.getIntroduced()));
			c.setDiscontinued(formater.parseLocalDate(cDto.getIntroduced()));
			if (cDto.getCompanyId() != 0) {
				c.setCompany(new Company(cDto.getCompanyId(), cDto
						.getCompanyName()));
			}
		}
		return c;
	}
}

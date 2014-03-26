package com.excilys.projet.mapper;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.excilys.projet.domain.Company;
import com.excilys.projet.domain.Computer;
import com.excilys.projet.dto.ComputerDTO;

@Component
public class ComputerDTOMapper implements MessageSourceAware  {

	private MessageSource messageSource;

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
			if (c.getCompany() != null) {
				cDto.setCompanyId(c.getCompany().getId());
				cDto.setCompanyName(c.getCompany().getName());
			}
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
					.forPattern(messageSource.getMessage("date.pattern", null,
							"yyyy-MM-dd", LocaleContextHolder.getLocale()));
			if (cDto.getIntroduced() != null && !cDto.getIntroduced().isEmpty()) {
				c.setIntroduced(formater.parseLocalDate(cDto.getIntroduced()));
			}
			if (cDto.getDiscontinued() != null
					&& !cDto.getDiscontinued().isEmpty()) {
				c.setDiscontinued(formater.parseLocalDate(cDto
						.getDiscontinued()));
			}
			if (cDto.getCompanyId() != 0) {
				c.setCompany(new Company(cDto.getCompanyId(), cDto
						.getCompanyName()));
			}
		}
		return c;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;		
	}
}

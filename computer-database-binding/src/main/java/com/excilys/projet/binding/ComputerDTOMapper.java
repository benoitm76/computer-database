package com.excilys.projet.binding;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.excilys.projet.model.Company;
import com.excilys.projet.model.Computer;
import com.excilys.projet.model.ComputerOrder;
import com.excilys.projet.model.Page;

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

	public Page<ComputerDTO, ComputerOrder> convertPage(
			Page<Computer, ComputerOrder> pageComputer) {
		Page<ComputerDTO, ComputerOrder> pageDTO = new Page<>();
		pageDTO.setCurrentPage(pageComputer.getCurrentPage());
		pageDTO.setOrder(pageComputer.getOrder());
		pageDTO.setRecordCount(pageComputer.getRecordCount());
		pageDTO.setSearch(pageComputer.getSearch());
		pageDTO.setTotalPage(pageComputer.getTotalPage());
		if (pageComputer.getItems() != null) {
			List<ComputerDTO> computersDTO = new ArrayList<>();
			for (Computer c : pageComputer.getItems()) {
				computersDTO.add(createDTO(c));
			}
			pageDTO.setItems(computersDTO);
		}

		return pageDTO;
	}
}

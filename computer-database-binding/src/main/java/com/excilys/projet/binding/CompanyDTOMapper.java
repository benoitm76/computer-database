package com.excilys.projet.binding;

import com.excilys.projet.model.Company;

public class CompanyDTOMapper {

	public static CompanyDTO createDTO(Company c) {
		CompanyDTO cDto = null;
		if (c != null) {
			cDto = new CompanyDTO();
			cDto.setId(c.getId());
			cDto.setName(c.getName());
		}
		return cDto;
	}

	public static Company createEntity(CompanyDTO cDto) {
		Company c = null;
		if (cDto != null) {
			c = new Company();
			c.setId(cDto.getId());
			c.setName(cDto.getName());
		}
		return c;
	}

}

package com.excilys.projet.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.projet.domain.Computer;
import com.excilys.projet.dto.ComputerDTO;
import com.excilys.projet.mapper.ComputerDTOMapper;
import com.excilys.projet.service.ComputerService;

@WebService
public class ComputerWebServices {

	@Autowired
	ComputerService computerService;

	@Autowired
	ComputerDTOMapper computerDTOMapper;

	public List<ComputerDTO> findAll() {
		List<ComputerDTO> computers = new ArrayList<>();
		for (Computer c : computerService.findAll()) {
			computers.add(computerDTOMapper.createDTO(c));
		}
		return computers;
	}
}

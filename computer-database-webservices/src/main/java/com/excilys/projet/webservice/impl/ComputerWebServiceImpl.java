package com.excilys.projet.webservice.impl;

import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.projet.domain.Computer;
import com.excilys.projet.mapper.ComputerDTOMapper;
import com.excilys.projet.service.ComputerService;
import com.excilys.projet.webservice.ComputerWebService;

@Path("/webservices")
public class ComputerWebServiceImpl implements ComputerWebService{

	@Autowired
	private ComputerService computerService;

	@Autowired
	private ComputerDTOMapper computerDTOMapper;

	public List<Computer> findAll() {
		return computerService.findAll();
	}

	public Computer findComputer(int id) {
		return computerService.find(id);
	}
}

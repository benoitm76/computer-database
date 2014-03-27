package com.excilys.projet.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.projet.domain.Computer;
import com.excilys.projet.mapper.ComputerDTOMapper;
import com.excilys.projet.service.ComputerService;

@Component
@Path("/webservices")
public class ComputerWebServices {

	@Autowired
	public ComputerService computerService;

	@Autowired
	public ComputerDTOMapper computerDTOMapper;

	@GET
	@Produces("application/xml")
	public List<Computer> findAll() {
		return computerService.findAll();
	}
}

package com.excilys.projet.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.projet.domain.Computer;
import com.excilys.projet.mapper.ComputerDTOMapper;
import com.excilys.projet.service.ComputerService;

@Path("/webservices")
public class ComputerWebServices {

	@Autowired
	public ComputerService computerService;

	@Autowired
	public ComputerDTOMapper computerDTOMapper;

	@GET
	@Produces("application/json")
	public List<Computer> findAll() {
		return computerService.findAll();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Computer getComputer(@PathParam("id") int id)
	{
		return computerService.find(id);
	}
}

package com.excilys.projet.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.excilys.projet.domain.Computer;

public interface ComputerWebService {
	@GET
	@Produces("application/json")
	public List<Computer> findAll();

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Computer getComputer(@PathParam("id") int id);
}

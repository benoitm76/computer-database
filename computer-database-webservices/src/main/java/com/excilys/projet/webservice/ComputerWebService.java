package com.excilys.projet.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.excilys.projet.domain.Computer;

public interface ComputerWebService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Computer> findAll();

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Computer findComputer(@PathParam("id") int id);

	@GET
	@Path("find/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Computer> findByName(@PathParam("name") String name);
}

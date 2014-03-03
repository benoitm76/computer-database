package com.excilys.projet.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.projet.dao.DaoFactory;
import com.excilys.projet.model.Computer;
import com.excilys.projet.model.ComputerOrder;

public class ComputerService {
	private final static ComputerService _instance = new ComputerService();

	private ComputerService() {

	}

	public Computer find(long id) throws SQLException {
		return DaoFactory.getDaoComputer().find(id);
	}

	public List<Computer> findAllByCreteria(String search, ComputerOrder order,
			int startAt, int numberOfRows) throws SQLException {
		return DaoFactory.getDaoComputer().findAllByCreteria(search, order,
				startAt, numberOfRows);
	}

	public List<Computer> findAll() throws SQLException {
		return DaoFactory.getDaoComputer().findAll();
	}

	public void create(Computer c) throws SQLException {
		DaoFactory.getDaoComputer().create(c);
	}

	public void update(Computer c) throws SQLException {
		DaoFactory.getDaoComputer().update(c);
	}

	public void delete(long id) throws SQLException {
		DaoFactory.getDaoComputer().delete(id);
	}

	public int count(String search) throws SQLException {
		return DaoFactory.getDaoComputer().count(search);
	}

	public static ComputerService getInstance() {
		return _instance;
	}
}

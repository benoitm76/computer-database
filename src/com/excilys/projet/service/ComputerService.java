package com.excilys.projet.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.projet.dao.DBConnection;
import com.excilys.projet.dao.DaoFactory;
import com.excilys.projet.model.Computer;
import com.excilys.projet.model.ComputerOrder;

public class ComputerService {
	private final static ComputerService _instance = new ComputerService();

	private ComputerService() {

	}

	public Computer find(long id) throws SQLException {
		Computer computer = null;
		try {
			DBConnection.openConnection();
			computer = DaoFactory.getDaoComputer().find(id);
		} finally {
			DBConnection.closeConnection();
		}
		return computer;
	}

	public List<Computer> findAllByCreteria(String search, ComputerOrder order,
			int startAt, int numberOfRows) throws SQLException {

		List<Computer> computers = null;
		try {
			DBConnection.openConnection();
			computers = DaoFactory.getDaoComputer().findAllByCreteria(search,
					order, startAt, numberOfRows);
		} finally {
			DBConnection.closeConnection();
		}
		return computers;
	}

	public List<Computer> findAll() throws SQLException {
		List<Computer> computers = null;
		try {
			DBConnection.openConnection();
			computers = DaoFactory.getDaoComputer().findAll();
		} finally {
			DBConnection.closeConnection();
		}
		return computers;
	}

	public void create(Computer c) throws SQLException {
		try {
			DBConnection.openConnection();
			DaoFactory.getDaoComputer().create(c);
		} finally {
			DBConnection.closeConnection();
		}
	}

	public void update(Computer c) throws SQLException {
		try {
			DBConnection.openConnection();
			DaoFactory.getDaoComputer().update(c);
		} finally {
			DBConnection.closeConnection();
		}
	}

	public void delete(long id) throws SQLException {
		try {
			DBConnection.openConnection();
			DaoFactory.getDaoComputer().delete(id);
		} finally {
			DBConnection.closeConnection();
		}
	}

	public int count(String search) throws SQLException {
		int count = 0;
		try {
			DBConnection.openConnection();
			count = DaoFactory.getDaoComputer().count(search);
		} finally {
			DBConnection.closeConnection();
		}
		
		return count;
	}

	public static ComputerService getInstance() {
		return _instance;
	}
}

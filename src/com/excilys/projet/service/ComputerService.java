package com.excilys.projet.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.projet.dao.DBConnection;
import com.excilys.projet.dao.DaoComputer;
import com.excilys.projet.model.Computer;
import com.excilys.projet.model.ComputerOrder;

@Service
public class ComputerService {
	@Autowired
	private DaoComputer daoComputer;

	public Computer find(long id) throws SQLException {
		Computer computer = null;
		try {
			DBConnection.openConnection();
			computer = daoComputer.find(id);
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
			computers = daoComputer.findAllByCreteria(search,
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
			computers = daoComputer.findAll();
		} finally {
			DBConnection.closeConnection();
		}
		return computers;
	}

	public void create(Computer c) throws SQLException {
		try {
			DBConnection.openConnection();
			daoComputer.create(c);
		} finally {
			DBConnection.closeConnection();
		}
	}

	public void update(Computer c) throws SQLException {
		try {
			DBConnection.openConnection();
			daoComputer.update(c);
		} finally {
			DBConnection.closeConnection();
		}
	}

	public void delete(long id) throws SQLException {
		try {
			DBConnection.openConnection();
			daoComputer.delete(id);
		} finally {
			DBConnection.closeConnection();
		}
	}

	public int count(String search) throws SQLException {
		int count = 0;
		try {
			DBConnection.openConnection();
			count = daoComputer.count(search);
		} finally {
			DBConnection.closeConnection();
		}
		
		return count;
	}

	public DaoComputer getDaoComputer() {
		return daoComputer;
	}

	public void setDaoComputer(DaoComputer daoComputer) {
		this.daoComputer = daoComputer;
	}
}

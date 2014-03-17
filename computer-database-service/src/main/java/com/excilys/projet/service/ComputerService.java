package com.excilys.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.projet.dao.DaoComputer;
import com.excilys.projet.model.Computer;
import com.excilys.projet.model.ComputerOrder;

@Service
public class ComputerService {
	@Autowired
	private DaoComputer daoComputer;

	@Transactional(readOnly = true)
	public Computer find(long id) {
		Computer computer = null;

		computer = daoComputer.find(id);

		return computer;
	}

	@Transactional(readOnly = true)
	public List<Computer> findAllByCreteria(String search, ComputerOrder order,
			int startAt, int numberOfRows) {

		List<Computer> computers = null;

		computers = daoComputer.findAllByCreteria(search, order, startAt,
				numberOfRows);

		return computers;
	}

	@Transactional(readOnly = true)
	public List<Computer> findAll() {
		List<Computer> computers = null;

		computers = daoComputer.findAll();

		return computers;
	}

	@Transactional
	public void create(Computer c) {
		daoComputer.create(c);
	}

	@Transactional
	public void update(Computer c) {
		daoComputer.update(c);
	}

	@Transactional
	public void delete(long id) {
		daoComputer.delete(id);
	}

	@Transactional(readOnly = true)
	public int count(String search) {
		return daoComputer.count(search);
	}
}

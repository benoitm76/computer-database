package com.excilys.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.projet.dao.DaoComputer;
import com.excilys.projet.model.Computer;
import com.excilys.projet.model.ComputerOrder;
import com.excilys.projet.model.Page;

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
	public Page<Computer, ComputerOrder> findAllByCreteria(String search, ComputerOrder order,
			int startAt, int numberOfRows) {

		Page<Computer, ComputerOrder> page = new Page<>();

		int count = daoComputer.count(search);
		page.setRecordCount(count);
		page.setCurrentPage((startAt / 10) + 1);
		page.setTotalPage((count / 10) + 1);
		page.setOrder(order);
		page.setItems(daoComputer.findAllByCreteria(search, order, startAt,
				numberOfRows));

		return page;
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

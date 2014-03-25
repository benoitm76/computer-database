package com.excilys.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.excilys.projet.domain.Computer;
import com.excilys.projet.repository.ComputerRepository;

@Service
public class ComputerService {

	@Autowired
	private ComputerRepository computerRepository;

	public Computer find(long id) {
		return computerRepository.findOne(id);
	}

	public Page<Computer> findAllByName(String search, Pageable pageable) {
		return computerRepository.findByNameContainingOrCompanyNameContaining(
				search, search, pageable);
	}

	public Page<Computer> findAll(Pageable pageable) {
		return computerRepository.findAll(pageable);
	}

	public List<Computer> findAll() {
		return computerRepository.findAll();
	}

	public void create(Computer c) {
		computerRepository.save(c);
	}

	public void update(Computer c) {
		computerRepository.save(c);
	}

	public void delete(long id) {
		computerRepository.delete(id);
	}
}

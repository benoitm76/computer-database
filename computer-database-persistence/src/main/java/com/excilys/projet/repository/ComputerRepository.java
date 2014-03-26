package com.excilys.projet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.projet.domain.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Long> {

	public Page<Computer> findByNameContainingOrCompanyNameContaining(String name, String companyName, Pageable pageable);

}
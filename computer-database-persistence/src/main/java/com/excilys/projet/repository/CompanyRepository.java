package com.excilys.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.projet.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
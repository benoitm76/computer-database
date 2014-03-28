package com.excilys.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.excilys.projet.domain.Company;
import com.excilys.projet.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public Company find(long id) {
		return companyRepository.findOne(id);
	}

	public List<Company> findAll() {
		return companyRepository.findAll();
	}
	
	public List<Company> findAll(Sort sort) {
		return companyRepository.findAll(sort);
	}

	public void create(Company c) {
		companyRepository.save(c);
	}

	public void update(Company c) {
		companyRepository.save(c);
	}

	public void delete(long id) {
		companyRepository.delete(id);
	}
}

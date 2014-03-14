package com.excilys.projet.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.projet.dao.DaoCompany;
import com.excilys.projet.model.Company;

@Service
public class CompanyService {
	@Autowired
	private DaoCompany daoCompany;

	@Transactional (readOnly=true)
	public Company find(long id) throws SQLException {
		Company company = null;

		company = daoCompany.find(id);

		return company;
	}

	@Transactional (readOnly=true)
	public List<Company> findAll() throws SQLException {
		List<Company> companies = null;

		companies = daoCompany.findAll();

		return companies;
	}

	@Transactional
	public void create(Company c) throws SQLException {
		daoCompany.create(c);
	}

	@Transactional
	public void update(Company c) throws SQLException {
		daoCompany.update(c);
	}

	@Transactional
	public void delete(long id) throws SQLException {
		daoCompany.delete(id);
	}
}

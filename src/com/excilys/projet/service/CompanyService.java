package com.excilys.projet.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.projet.dao.DaoCompany;
import com.excilys.projet.model.Company;

@Service
public class CompanyService {
	@Autowired
	private DaoCompany daoCompany;

	public Company find(long id) throws SQLException {
		Company company = null;

		company = daoCompany.find(id);

		return company;
	}

	public List<Company> findAll() throws SQLException {
		List<Company> companies = null;

		companies = daoCompany.findAll();

		return companies;
	}

	public void create(Company c) throws SQLException {
		daoCompany.create(c);
	}

	public void update(Company c) throws SQLException {
		daoCompany.update(c);
	}

	public void delete(long id) throws SQLException {
		daoCompany.delete(id);
	}

	public DaoCompany getDaoCompany() {
		return daoCompany;
	}

	public void setDaoCompany(DaoCompany daoCompany) {
		this.daoCompany = daoCompany;
	}
}

package com.excilys.projet.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.projet.dao.DaoFactory;
import com.excilys.projet.model.Company;

public class CompanyService {
	private final static CompanyService _instance = new CompanyService();

	private CompanyService() {

	}

	public Company find(long id) throws SQLException {
		return DaoFactory.getDaoCompany().find(id);
	}

	public List<Company> findAll() throws SQLException {
		return DaoFactory.getDaoCompany().findAll();
	}

	public void create(Company c) throws SQLException {
		DaoFactory.getDaoCompany().create(c);
	}

	public void update(Company c) throws SQLException {
		DaoFactory.getDaoCompany().update(c);
	}

	public void delete(long id) throws SQLException {
		DaoFactory.getDaoCompany().delete(id);
	}

	public static CompanyService getInstance() {
		return _instance;
	}
}

package com.excilys.projet.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.projet.dao.DBConnection;
import com.excilys.projet.dao.DaoFactory;
import com.excilys.projet.model.Company;

public class CompanyService {
	private final static CompanyService _instance = new CompanyService();

	private CompanyService() {

	}

	public Company find(long id) throws SQLException {
		Company company = null;
		try {
			DBConnection.openConnection();
			company = DaoFactory.getDaoCompany().find(id);
		} finally {
			DBConnection.closeConnection();
		}
		return company;
	}

	public List<Company> findAll() throws SQLException {
		List<Company> companies = null;
		try {
			DBConnection.openConnection();
			companies = DaoFactory.getDaoCompany().findAll();
		} finally {
			DBConnection.closeConnection();
		}
		return companies;
	}

	public void create(Company c) throws SQLException {
		try {
			DBConnection.openConnection();
			DaoFactory.getDaoCompany().create(c);
		} finally {
			DBConnection.closeConnection();
		}
	}

	public void update(Company c) throws SQLException {
		try {
			DBConnection.openConnection();
			DaoFactory.getDaoCompany().update(c);
		} finally {
			DBConnection.closeConnection();
		}
	}

	public void delete(long id) throws SQLException {
		try {
			DBConnection.openConnection();
			DaoFactory.getDaoCompany().delete(id);
		} finally {
			DBConnection.closeConnection();
		}
	}

	public static CompanyService getInstance() {
		return _instance;
	}
}

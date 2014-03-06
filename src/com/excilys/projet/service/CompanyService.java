package com.excilys.projet.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.projet.dao.DBConnection;
import com.excilys.projet.dao.DaoCompany;
import com.excilys.projet.model.Company;

@Service
public class CompanyService {
	@Autowired
	private DaoCompany daoCompany;

	public Company find(long id) throws SQLException {
		Company company = null;
		try {
			DBConnection.openConnection();
			company = daoCompany.find(id);
		} finally {
			DBConnection.closeConnection();
		}
		return company;
	}

	public List<Company> findAll() throws SQLException {
		List<Company> companies = null;
		try {
			DBConnection.openConnection();
			companies = daoCompany.findAll();
		} finally {
			DBConnection.closeConnection();
		}
		return companies;
	}

	public void create(Company c) throws SQLException {
		try {
			DBConnection.openConnection();
			daoCompany.create(c);
		} finally {
			DBConnection.closeConnection();
		}
	}

	public void update(Company c) throws SQLException {
		try {
			DBConnection.openConnection();
			daoCompany.update(c);
		} finally {
			DBConnection.closeConnection();
		}
	}

	public void delete(long id) throws SQLException {
		try {
			DBConnection.openConnection();
			daoCompany.delete(id);
		} finally {
			DBConnection.closeConnection();
		}
	}

	public DaoCompany getDaoCompany() {
		return daoCompany;
	}

	public void setDaoCompany(DaoCompany daoCompany) {
		this.daoCompany = daoCompany;
	}
}

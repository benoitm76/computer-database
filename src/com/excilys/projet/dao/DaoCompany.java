package com.excilys.projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.projet.model.Company;

public class DaoCompany extends Dao<Company> {

	private final static DaoCompany _instance = new DaoCompany();

	private DaoCompany() {

	}

	public Company find(long id) throws SQLException {

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Company company = null;

		try {
			con = DBConnection.getConnection();
			statement = con
					.prepareStatement("SELECT id, name FROM company WHERE id = ?");

			statement.setLong(1, id);
			rs = statement.executeQuery();

			if (rs.next()) {
				company = new Company(rs.getLong(1), rs.getString(2));
			}
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {

			}

		}
		return company;
	}

	public List<Company> findAll() throws SQLException {

		Connection con = null;
		Statement statement = null;
		ResultSet rs = null;
		List<Company> companies = null;

		try {
			con = DBConnection.getConnection();
			statement = con.createStatement();
			rs = statement
					.executeQuery("SELECT id, name FROM company ORDER BY name");
			companies = new ArrayList<>();
			while (rs.next()) {
				companies.add(new Company(rs.getLong(1), rs.getString(2)));
			}

		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {

			}

		}
		return companies;
	}

	protected static DaoCompany getInstance() {
		return _instance;
	}

	@Override
	public void create(Company t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Company t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

}

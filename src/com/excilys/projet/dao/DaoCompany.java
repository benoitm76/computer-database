package com.excilys.projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.excilys.projet.model.Company;

public class DaoCompany {

	private final static DaoCompany _instance = new DaoCompany();

	private DaoCompany() {

	}

	public Company getCompany(long id) throws NamingException, SQLException {

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
			if (con != null) {
				con.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}

		}
		return company;
	}

	public List<Company> getAllCompany() throws NamingException, SQLException {

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
			if (con != null) {
				con.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}

		}
		return companies;
	}

	public static DaoCompany getInstance() {
		return _instance;
	}

}

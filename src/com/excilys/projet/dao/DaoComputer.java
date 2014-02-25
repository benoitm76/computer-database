package com.excilys.projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import com.excilys.projet.model.Computer;

public class DaoComputer {
	private final static DaoComputer _instance = new DaoComputer();

	private DaoComputer() {

	}

	public Computer getComputer(long id) throws NamingException, SQLException {

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Computer computer = null;

		try {
			con = DBConnection.getConnection();
			statement = con
					.prepareStatement("SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?");

			statement.setLong(1, id);
			rs = statement.executeQuery();

			if (rs.next()) {
				computer = new Computer(rs.getLong(1), rs.getString(2),
						new Date(rs.getLong(3)), new Date(rs.getLong(4)),
						rs.getLong(5));
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
		return computer;
	}

	public List<Computer> getAllComputer() throws NamingException, SQLException {

		Connection con = null;
		Statement statement = null;
		ResultSet rs = null;
		List<Computer> computers = null;

		try {
			con = DBConnection.getConnection();

			statement = con.createStatement();

			rs = statement
					.executeQuery("SELECT id, name, introduced, discontinued, company_id FROM computer");
			computers = new ArrayList<>();
			while (rs.next()) {
				computers.add(new Computer(rs.getLong(1), rs.getString(2),
						new Date(rs.getLong(3)), new Date(rs.getLong(4)), rs
								.getLong(5)));
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
		return computers;
	}

	public static DaoComputer getInstance() {
		return _instance;
	}
}

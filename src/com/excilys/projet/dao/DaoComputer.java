package com.excilys.projet.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.excilys.projet.model.Company;
import com.excilys.projet.model.Computer;

public class DaoComputer {
	private final static DaoComputer _instance = new DaoComputer();

	private DaoComputer() {

	}

	public void addComputer(Computer c) throws SQLException, NamingException {
		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = DBConnection.getConnection();
			statement = con.prepareStatement(
					"INSERT INTO computer VALUES(NULL, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			Date introducedDate = null;
			if (c.getIntroduced() != null) {
				introducedDate = new Date(c.getIntroduced().getTime());
			}
			Date discontinuedDate = null;
			if (c.getDiscontinued() != null) {
				discontinuedDate = new Date(c.getDiscontinued().getTime());
			}

			statement.setString(1, c.getName());
			statement.setDate(2, introducedDate);
			statement.setDate(3, discontinuedDate);
			if (c.getCompany() == null) {
				statement.setNull(4, Types.BIGINT);
			} else {
				statement.setLong(4, c.getCompany().getId());
			}

			long id = statement.executeUpdate();
			c.setId(id);

		} finally {
			if (con != null) {
				con.close();
			}
			if (statement != null) {
				statement.close();
			}

		}
	}

	public Computer getComputer(long id) throws NamingException, SQLException {

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Computer computer = null;

		try {
			con = DBConnection.getConnection();
			statement = con
					.prepareStatement("SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, company.name "
							+ "FROM computer c LEFT JOIN company ON c.company_id = company.id WHERE c.id = ?");

			statement.setLong(1, id);
			rs = statement.executeQuery();

			if (rs.next()) {
				computer = new Computer(rs.getLong(1), rs.getString(2),
						rs.getDate(3), rs.getDate(4), new Company(
								rs.getLong(5), rs.getString(6)));
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
					.executeQuery("SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, company.name "
							+ "FROM computer c LEFT JOIN company ON c.company_id = company.id");
			computers = new ArrayList<>();
			while (rs.next()) {
				computers.add(new Computer(rs.getLong(1), rs.getString(2), rs
						.getDate(3), rs.getDate(4), new Company(rs.getLong(5),
						rs.getString(6))));
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

	public void updateComputer(Computer c) throws SQLException, NamingException {
		Connection con = null;
		PreparedStatement statement = null;

		try {
			con = DBConnection.getConnection();

			statement = con
					.prepareStatement("UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?");

			Date introducedDate = null;
			if (c.getIntroduced() != null) {
				introducedDate = new Date(c.getIntroduced().getTime());
			}
			Date discontinuedDate = null;
			if (c.getDiscontinued() != null) {
				discontinuedDate = new Date(c.getDiscontinued().getTime());
			}

			statement.setString(1, c.getName());
			statement.setDate(2, introducedDate);
			statement.setDate(3, discontinuedDate);
			if (c.getCompany() == null) {
				statement.setNull(4, Types.BIGINT);
			} else {
				statement.setLong(4, c.getCompany().getId());
			}
			statement.setLong(5, c.getId());

			statement.executeUpdate();

		} finally {
			if (con != null) {
				con.close();
			}
			if (statement != null) {
				statement.close();
			}

		}
	}

	public static DaoComputer getInstance() {
		return _instance;
	}
}

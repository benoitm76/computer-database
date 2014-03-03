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

import com.excilys.projet.model.Company;
import com.excilys.projet.model.Computer;
import com.excilys.projet.model.ComputerOrder;

public class DaoComputer extends Dao<Computer> implements DaoCriteria<Computer> {
	private final static DaoComputer _instance = new DaoComputer();

	private DaoComputer() {

	}

	public void create(Computer c) {
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
		} catch (SQLException e) {

		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {

			}

		}
	}

	public Computer find(long id) throws SQLException {

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
			try {
				if (con != null) {
					con.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {

			}

		}
		return computer;
	}

	public List<Computer> findAllByCreteria(String search, ComputerOrder order,
			int startAt, int numberOfRows) throws SQLException {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Computer> computers = null;

		try {
			con = DBConnection.getConnection();
			String sql = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
					+ "FROM computer LEFT JOIN company ON computer.company_id = company.id";
			if (search != null) {
				sql += " WHERE computer.name LIKE ? OR company.name LIKE ?";
			}
			if (order != null) {
				sql += " ORDER BY " + order.getOrderStatement();
			}
			sql += " LIMIT ?, ?";

			statement = con.prepareStatement(sql);

			int i = 0;
			if (search != null) {
				statement.setString(1, "%" + search + "%");
				statement.setString(2, "%" + search + "%");
				i = 2;
			}
			statement.setInt(i + 1, startAt);
			statement.setInt(i + 2, numberOfRows);

			rs = statement.executeQuery();
			computers = new ArrayList<>();
			while (rs.next()) {
				computers.add(new Computer(rs.getLong(1), rs.getString(2), rs
						.getDate(3), rs.getDate(4), new Company(rs.getLong(5),
						rs.getString(6))));
			}
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {

			}

		}
		return computers;
	}

	public List<Computer> findAll() throws SQLException {

		Connection con = null;
		Statement statement = null;
		ResultSet rs = null;
		List<Computer> computers = null;

		try {
			con = DBConnection.getConnection();

			String sql = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
					+ "FROM computer LEFT JOIN company ON computer.company_id = company.id";

			statement = con.createStatement();

			rs = statement.executeQuery(sql);

			computers = new ArrayList<>();
			while (rs.next()) {
				computers.add(new Computer(rs.getLong(1), rs.getString(2), rs
						.getDate(3), rs.getDate(4), new Company(rs.getLong(5),
						rs.getString(6))));
			}

		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {

			}

		}
		return computers;
	}

	public int count(String search) throws SQLException {

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBConnection.getConnection();

			String sql = "SELECT COUNT(id) FROM computer";

			if (search != null) {
				sql += " WHERE name LIKE ?";
			}

			statement = con.prepareStatement(sql);

			if (search != null) {
				statement.setString(1, "%" + search + "%");
			}

			rs = statement.executeQuery();
			rs.next();
			result = rs.getInt(1);

		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {

			}

		}
		return result;
	}

	public void update(Computer c) throws SQLException {
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
			try {
				if (con != null) {
					con.close();
				}
				if (statement != null) {
					statement.close();
				}

			} catch (SQLException e) {

			}

		}
	}

	public void delete(long id) throws SQLException {
		Connection con = null;
		PreparedStatement statement = null;

		try {
			con = DBConnection.getConnection();

			statement = con
					.prepareStatement("DELETE FROM computer WHERE id = ?");

			statement.setLong(1, id);

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

	protected static DaoComputer getInstance() {
		return _instance;
	}
}

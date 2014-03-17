package com.excilys.projet.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.excilys.projet.dao.mapper.ComputerMapper;
import com.excilys.projet.model.Computer;
import com.excilys.projet.model.ComputerOrder;

public class DaoComputer extends Dao<Computer> implements DaoCriteria<Computer> {
	private JdbcTemplate jt;

	public void create(Computer c) {
		Long companyID = null;
		if (c.getCompany() != null) {
			companyID = c.getCompany().getId();
		}
		jt = getJdbcTemplate();
		Date introduced = null;
		Date discontinued = null;
		if (c.getIntroduced() != null) {
			introduced = c.getIntroduced().toDate();
		}
		if (c.getDiscontinued() != null) {
			discontinued = c.getDiscontinued().toDate();
		}
		jt.update(
				"INSERT INTO computer VALUES(NULL, ?, ?, ?, ?);",
				new Object[] { c.getName(), introduced, discontinued, companyID });
	}

	public Computer find(long id) {

		jt = getJdbcTemplate();
		return jt
				.queryForObject(
						"SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, company.name "
								+ "FROM computer c LEFT JOIN company ON c.company_id = company.id WHERE c.id = ?",
						new ComputerMapper(), id);

	}

	public List<Computer> findAllByCreteria(String search, ComputerOrder order,
			int startAt, int numberOfRows) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("startAt", startAt);
		parameters.put("numberOfRows", numberOfRows);
		String sql = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
				+ "FROM computer LEFT JOIN company ON computer.company_id = company.id";
		if (search != null) {
			sql += " WHERE computer.name LIKE :computerSearch OR company.name LIKE :companySearch";
			parameters.put("computerSearch", "%" + search + "%");
			parameters.put("companySearch", "%" + search + "%");
		}
		if (order != null) {
			sql += " ORDER BY " + order.getOrderStatement();
		}
		sql += " LIMIT :startAt, :numberOfRows";
		return getNamedParameterJdbcTemplate().query(sql, parameters,
				new ComputerMapper());

	}

	public List<Computer> findAll() {

		jt = getJdbcTemplate();

		return jt
				.query("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
						+ "FROM computer LEFT JOIN company ON computer.company_id = company.id",
						new ComputerMapper());

	}

	public int count(String search) {
		String sql = "SELECT COUNT(id) FROM computer";
		jt = getJdbcTemplate();
		if (search == null) {
			return jt.queryForObject(sql, Integer.class);
		} else {
			sql += " WHERE name LIKE ?";
			return jt.queryForObject(sql, Integer.class, "%" + search + "%");
		}

	}

	public void update(Computer c) {
		Long companyID = null;
		if (c.getCompany() != null) {
			companyID = c.getCompany().getId();
		}
		jt = getJdbcTemplate();
		Date introduced = null;
		Date discontinued = null;
		if (c.getIntroduced() != null) {
			introduced = c.getIntroduced().toDate();
		}
		if (c.getDiscontinued() != null) {
			discontinued = c.getDiscontinued().toDate();
		}
		jt.update(
				"UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?",
				new Object[] { c.getName(), introduced, discontinued,
						companyID, c.getId() });
	}

	public void delete(long id) {
		jt = getJdbcTemplate();
		jt.update("DELETE FROM computer WHERE id = ?;", new Object[] { id });
	}
}

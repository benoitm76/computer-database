package com.excilys.projet.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.excilys.projet.dao.mapper.ComputerMapper;
import com.excilys.projet.model.Company;
import com.excilys.projet.model.Computer;
import com.excilys.projet.model.ComputerOrder;
import com.excilys.projet.model.dto.ComputerDTO;

public class DaoComputer extends Dao<Computer> implements DaoCriteria<Computer> {
	private JdbcTemplate jt;

	public void create(Computer c) {
		Long companyID = null;
		if (c.getCompany() != null) {
			companyID = c.getCompany().getId();
		}
		jt = getJdbcTemplate();
		jt.update(
				"INSERT INTO computer VALUES(NULL, ?, ?, ?, ?);",
				new Object[] { c.getName(), c.getIntroduced(),
						c.getDiscontinued(), companyID });
	}

	public Computer find(long id) throws SQLException {

		jt = getJdbcTemplate();
		return jt
				.queryForObject(
						"SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, company.name "
								+ "FROM computer c LEFT JOIN company ON c.company_id = company.id WHERE c.id = ?",
						new ComputerMapper(), id);

	}

	public List<Computer> findAllByCreteria(String search, ComputerOrder order,
			int startAt, int numberOfRows) throws SQLException {
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

	public List<Computer> findAll() throws SQLException {

		jt = getJdbcTemplate();

		return jt
				.query("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
						+ "FROM computer LEFT JOIN company ON computer.company_id = company.id",
						new ComputerMapper());

	}

	public int count(String search) throws SQLException {
		String sql = "SELECT COUNT(id) FROM computer";
		jt = getJdbcTemplate();
		if (search == null) {
			return jt.queryForObject(sql, Integer.class);
		} else {
			sql += " WHERE name LIKE ?";
			return jt.queryForObject(sql, Integer.class, "%" + search + "%");
		}

	}

	public void update(Computer c) throws SQLException {
		Long companyID = null;
		if (c.getCompany() != null) {
			companyID = c.getCompany().getId();
		}
		jt = getJdbcTemplate();
		jt.update(
				"UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?",
				new Object[] { c.getName(), c.getIntroduced(),
						c.getDiscontinued(), companyID, c.getId() });
	}

	public void delete(long id) throws SQLException {
		jt = getJdbcTemplate();
		jt.update("DELETE FROM computer WHERE id = ?;", new Object[] { id });
	}

	public static ComputerDTO createDTO(Computer c) {
		ComputerDTO cDto = null;
		if (c != null) {
			cDto = new ComputerDTO();
			cDto.setId(c.getId());
			cDto.setName(c.getName());
			cDto.setIntroduced(c.getIntroduced());
			cDto.setDiscontinued(c.getDiscontinued());
			cDto.setCompanyId(c.getCompany().getId());
			cDto.setCompanyName(c.getCompany().getName());
		}
		return cDto;
	}

	public static Computer createEntity(ComputerDTO cDto) {
		Computer c = null;
		if (cDto != null) {
			c = new Computer();
			c.setId(cDto.getId());
			c.setName(cDto.getName());
			c.setIntroduced(cDto.getIntroduced());
			c.setDiscontinued(cDto.getDiscontinued());
			c.setCompany(new Company(cDto.getCompanyId(), cDto.getCompanyName()));
		}
		return c;
	}
}

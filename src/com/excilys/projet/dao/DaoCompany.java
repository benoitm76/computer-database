package com.excilys.projet.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.excilys.projet.dao.mapper.CompanyMapper;
import com.excilys.projet.model.Company;
import com.excilys.projet.model.dto.CompanyDTO;

public class DaoCompany extends Dao<Company> {

	private JdbcTemplate jdbcTemplate;

	public Company find(long id) throws SQLException {

		jdbcTemplate = getJdbcTemplate();
		return jdbcTemplate.queryForObject(
				"SELECT id, name FROM company WHERE id = ?",
				new Object[] { id }, new CompanyMapper());
	}

	public List<Company> findAll() throws SQLException {

		jdbcTemplate = getJdbcTemplate();
		return jdbcTemplate.query("SELECT id, name FROM company ORDER BY name",
				new CompanyMapper());
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

	public static CompanyDTO createDTO(Company c) {
		CompanyDTO cDto = null;
		if (c != null) {
			cDto = new CompanyDTO();
			cDto.setId(c.getId());
			cDto.setName(c.getName());
		}
		return cDto;
	}

	public static Company createEntity(CompanyDTO cDto) {
		Company c = null;
		if (cDto != null) {
			c = new Company();
			c.setId(cDto.getId());
			c.setName(cDto.getName());
		}
		return c;
	}

}

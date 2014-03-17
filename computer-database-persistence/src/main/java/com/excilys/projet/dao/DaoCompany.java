package com.excilys.projet.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.excilys.projet.dao.mapper.CompanyMapper;
import com.excilys.projet.model.Company;

public class DaoCompany extends Dao<Company> {

	private JdbcTemplate jdbcTemplate;

	public Company find(long id) {

		jdbcTemplate = getJdbcTemplate();
		return jdbcTemplate.queryForObject(
				"SELECT id, name FROM company WHERE id = ?",
				new Object[] { id }, new CompanyMapper());
	}

	public List<Company> findAll() {

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
}

package com.excilys.projet.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.projet.model.Company;

public class CompanyMapper implements RowMapper<Company> {

	@Override
	public Company mapRow(ResultSet rs, int rowNumber) throws SQLException {
		return new Company(rs.getLong(1), rs.getString(2));
	}

}

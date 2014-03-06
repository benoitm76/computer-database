package com.excilys.projet.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.projet.model.Company;
import com.excilys.projet.model.Computer;

public class ComputerMapper implements RowMapper<Computer> {

	@Override
	public Computer mapRow(ResultSet rs, int rowNumber) throws SQLException {
		return new Computer(rs.getLong(1), rs.getString(2), rs.getDate(3),
				rs.getDate(4), new Company(rs.getLong(5), rs.getString(6)));
	}

}

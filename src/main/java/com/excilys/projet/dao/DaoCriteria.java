package com.excilys.projet.dao;

import java.sql.SQLException;
import java.util.List;

import com.excilys.projet.model.ComputerOrder;

public interface DaoCriteria<T> {

	public List<T> findAllByCreteria(String search, ComputerOrder order,
			int startAt, int numberOfRows) throws SQLException;

	public int count(String search) throws SQLException;
}

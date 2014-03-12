package com.excilys.projet.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

public abstract class Dao<T> extends NamedParameterJdbcDaoSupport {

	public abstract T find(long id) throws SQLException;

	public abstract List<T> findAll() throws SQLException;

	public abstract void create(T t) throws SQLException;

	public abstract void update(T t) throws SQLException;

	public abstract void delete(long id) throws SQLException;

}

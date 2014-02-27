package com.excilys.projet.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DBConnection {

	private static BoneCP connectionPool;

	public static void initialize() throws SQLException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BoneCPConfig config = new BoneCPConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull");
		config.setUsername("jee-cdb");
		config.setPassword("password");
		connectionPool = new BoneCP(config);

	}

	public static Connection getConnection() throws
			SQLException {
		/*
		 * Context initContext = new InitialContext(); Context envContext =
		 * (Context) initContext.lookup("java:/comp/env"); DataSource ds =
		 * (DataSource) envContext.lookup("db_connection");
		 */

		if (connectionPool == null) {
			initialize();
		}

		return connectionPool.getConnection();
	}
}

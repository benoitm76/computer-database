package com.excilys.projet.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DBConnection {

	private static BoneCP connectionPool;
	private final static Logger logger = LoggerFactory
			.getLogger(DBConnection.class);

	private static ThreadLocal<Connection> currentCon = new ThreadLocal<>();

	public static void initialize() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
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

	public static Connection getConnection() throws SQLException {
		/*
		 * Context initContext = new InitialContext(); Context envContext =
		 * (Context) initContext.lookup("java:/comp/env"); DataSource ds =
		 * (DataSource) envContext.lookup("db_connection");
		 */

		return currentCon.get();
	}

	public static void openConnection() throws SQLException {
		if (connectionPool == null) {
			initialize();
			logger.debug("Initialization connection");
		}
		if (currentCon != null && currentCon.get() != null && !currentCon.get().isClosed()) {
			currentCon.get().close();
		}
		currentCon.set(connectionPool.getConnection());
	}

	public static void closeConnection() throws SQLException {

		if (currentCon != null && currentCon.get() != null
				&& !currentCon.get().isClosed()) {
			currentCon.get().close();
		}
	}
}

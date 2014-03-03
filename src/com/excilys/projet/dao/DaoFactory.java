package com.excilys.projet.dao;

public class DaoFactory {

	public static DaoCompany getDaoCompany() {
		return DaoCompany.getInstance();
	}

	public static DaoComputer getDaoComputer() {
		return DaoComputer.getInstance();
	}

}

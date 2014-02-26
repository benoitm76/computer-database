package com.excilys.projet.model;

public enum ComputerOrder {
	ORDER_BY_NAME_ASC("computer.name ASC", "orderByName=asc"), ORDER_BY_NAME_DESC(
			"computer.name DESC", "orderByName=desc"), ORDER_BY_INTRODUCED_DATE_ASC(
			"computer.introduced ASC", "orderByIntroducedDate=asc"), ORDER_BY_INTRODUCED_DATE_DESC(
			"computer.introduced DESC", "orderByIntroducedDate=desc"), ORDER_BY_DISCONTINUED_DATE_ASC(
			"computer.discontinued ASC", "orderByDiscontinuedDate=asc"), ORDER_BY_DISCONTINUED_DATE_DESC(
			"computer.discontinued DESC", "orderByDiscontinuedDate=desc"), ORDER_BY_COMPANY_NAME_ASC(
			"company.name ASC", "orderByCompanyName=asc"), ORDER_BY_COMPANY_NAME_DESC(
			"company.name DESC", "orderByCompanyName=desc");

	private final String orderStatement;
	private final String urlParameter;

	private ComputerOrder(String orderStatement, String urlParameter) {
		this.orderStatement = orderStatement;
		this.urlParameter = urlParameter;
	}

	public String getOrderStatement() {
		return orderStatement;
	}

	public String getUrlParameter() {
		return urlParameter;
	}

}

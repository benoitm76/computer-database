package com.excilys.projet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.excilys.projet.model.Computer;
import com.excilys.projet.model.ComputerOrder;
import com.excilys.projet.model.QCompany;
import com.excilys.projet.model.QComputer;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class DaoComputer extends Dao<Computer> implements DaoCriteria<Computer> {

	@PersistenceContext(unitName = "entityManagerFactory")
	private EntityManager entityManager;

	public void create(Computer c) {
		entityManager.persist(c);
	}

	public Computer find(long id) {
		return (Computer) entityManager.find(Computer.class, new Long(id));

	}

	public List<Computer> findAllByCreteria(String search, ComputerOrder order,
			int startAt, int numberOfRows) {
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		JPAQuery query = new JPAQuery(entityManager);
		query.from(computer).leftJoin(computer.company, company);
		StringBuilder querySearch = new StringBuilder("%").append(search)
				.append("%");
		if (search != null) {
			query.where(computer.name.like(querySearch.toString()).or(
					company.name.like(querySearch.toString())));
		}
		if (order != null) {
			switch (order) {
			case ORDER_BY_NAME_ASC:
				query.orderBy(computer.name.asc());
				break;
			case ORDER_BY_NAME_DESC:
				query.orderBy(computer.name.desc());
				break;
			case ORDER_BY_INTRODUCED_DATE_ASC:
				query.orderBy(computer.introduced.asc());
				break;
			case ORDER_BY_INTRODUCED_DATE_DESC:
				query.orderBy(computer.introduced.desc());
				break;
			case ORDER_BY_DISCONTINUED_DATE_ASC:
				query.orderBy(computer.discontinued.asc());
				break;
			case ORDER_BY_DISCONTINUED_DATE_DESC:
				query.orderBy(computer.discontinued.desc());
				break;
			case ORDER_BY_COMPANY_NAME_ASC:
				query.orderBy(computer.company.name.asc());
				break;
			case ORDER_BY_COMPANY_NAME_DESC:
				query.orderBy(computer.company.name.desc());
				break;
			}
		}
		return query.limit(numberOfRows).offset(startAt).list(computer);

	}

	public List<Computer> findAll() {
		QComputer computer = QComputer.computer;
		JPAQuery query = new JPAQuery(entityManager);
		return query.from(computer).list(computer);

	}

	public int count(String search) {

		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		JPAQuery query = new JPAQuery(entityManager);
		query.from(computer).leftJoin(computer.company, company);
		StringBuilder querySearch = new StringBuilder("%").append(search)
				.append("%");
		if (search != null) {
			query.where(computer.name.like(querySearch.toString()).or(
					company.name.like(querySearch.toString())));
		}

		return (int) query.count();
	}

	public void update(Computer c) {
		entityManager.merge(c);
	}

	public void delete(long id) {
		entityManager.remove(find(id));
	}
}
